package sg.edu.rp.c346.id20022735.avrs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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
    Connection con;

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
                                String msg = "Enter owner's vehicle license plate.";
                                Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            String msg = "Enter owner's contact number.";
                            Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String msg = "Enter owner's name.";
                        Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String msg = "Enter owner's id.";
                    Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
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

    private class ConnectMySql extends AsyncTask<String, Void, String> {
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
                String sql = "INSERT INTO owner" + " VALUES('" + id1 + "','" + name1 + "','" + no + "','" + mem1 + "','" + lp + "','" + sch1 + "','" + des1 + "')";
                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
                st.executeUpdate(sql);
                res = "Add Successful";
            } catch (Exception e) {
                e.printStackTrace();
                res = "Add Failed";
            }
            return res;
        }

    }
    
}