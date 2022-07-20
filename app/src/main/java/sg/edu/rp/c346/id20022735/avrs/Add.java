package sg.edu.rp.c346.id20022735.avrs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Add extends AppCompatActivity {

    Button btnadd, btncel;
    EditText id, name, contact, member, license, des, sch;
//    Spinner spin;
    Connection con;
//    RadioGroup schrg;
//    RadioButton schrb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnadd = findViewById(R.id.add);
        btncel = findViewById(R.id.cancel);
        id = findViewById(R.id.staff_id);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        member = findViewById(R.id.membership);
        license = findViewById(R.id.license);
        des = findViewById(R.id.designation);
        sch = findViewById(R.id.school);
//        spin=findViewById(R.id.spinner);
//        final String[] sch = {""};
//        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 0 :
//                        sch[0] = "SAS";
//                        break;
//                    case 1 :
//                        sch[1] = "SHL";
//                        break;
//                    case 2 :
//                        sch[2] = "SMC";
//                        break;
//                    case 3 :
//                        sch[3] = "SOE";
//                        break;
//                    case 4 :
//                        sch[4] = "SOH";
//                        break;
//                    case 5 :
//                        sch[5] = "SOI";
//                        break;
//                    case 6 :
//                        sch[6] = "STA";
//                        break;
//
//                }
//            }
//
//        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1 = id.getText().toString();
                String name1 = name.getText().toString();
                String no = contact.getText().toString();
                String mem1 = member.getText().toString();
                String lp = license.getText().toString();
                String des1 = des.getText().toString();
                String sch1 = sch.getText().toString();

                if (!id1.equals("")) {
                    if (!name1.equals("")) {
                        if (no.length() != 0) {
                            if (!lp.equals("")) {
                                ConnectMySql addsql = new ConnectMySql();
                                addsql.execute("");
                                Intent intent = new Intent(Add.this, AdminPage.class);
                                startActivity(intent);
                                String msg = "Successfully added";
                                Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Add.this, "Enter owner's vehicle license plate.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Add.this, "Enter owner's contact number.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Add.this, "Enter owner's name.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Add.this, "Enter owner's id.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btncel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(Add.this);
                myBuilder.setMessage("Do you want to exit?");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("No", null);
                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Add.this, AdminPage.class);
                        startActivity(intent);
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }


    public class ConnectMySql extends AsyncTask<String, Void, String> {
        String id1 = id.getText().toString();
        String name1 = name.getText().toString();
        String no = contact.getText().toString();
        String mem1 = member.getText().toString();
        String lp = license.getText().toString();
        String des1 = des.getText().toString();
        String sch1 = sch.getText().toString();

        @Override
        protected String doInBackground(String... args) {
            String res = "";
            try {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                con = connectionHelper.conclass();
                //set sql string
                String sql = "INSERT INTO owner" + " VALUES('" + id1 + "','" + name1 + "','" + no + "','" + mem1 + "','" + lp + "','" + sch1 + "','" + des1 + "');";
                Statement st = con.createStatement();
                st.executeUpdate(sql);
                res = "Add Successful";
            } catch (Exception e) {
                e.printStackTrace();
                res = "Add Failed";
            }
            return res;
        }
        @SuppressLint("Newapi")
        public Connection ConnectionHelper(String url,String user,String pass){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection con = null;
            String conurl = null;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
            }
            catch (Exception e){
                Log.e("SQL Connection Error: ",e.getMessage());
            }
            return con;
        }
    }
//    private String getSch() {
//        String sch  = "";
//        switch (schrg.getCheckedRadioButtonId()) {
//            case R.id.soi:
//                sch = "SOI";
//                break;
//            case R.id.shl:
//                sch = "SHL";;
//                break;
//            case R.id.sas:
//                sch = "SAS";
//                break;
//            case R.id.soe:
//                sch = "SOE";
//                break;
//            case R.id.sta:
//                sch = "STA";
//                break;
//            case R.id.soh:
//                sch = "SOH";
//                break;
//            case R.id.smc:
//                sch = "SMC";
//                break;
//        }
//        return sch;
//    }
}