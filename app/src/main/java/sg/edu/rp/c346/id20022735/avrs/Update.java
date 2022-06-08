package sg.edu.rp.c346.id20022735.avrs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;

public class Update extends AppCompatActivity {
    private static final String url = "jdbc:mysql://116.89.52.49:3306/fyp";
    private static final String user = "root";
    private static final String pass = "123";

    TextView tvStaffId;
    EditText etName, etContact, etMember, etLicense1, etLicense2, etSchool, etDesignation;
    RadioGroup rgSchool;
    Button btnUpdate, btnDelete, btnCancel;
//    EditText id, name, contact, member, license, des, sch;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        lv = findViewById(R.id.listView);
        tvStaffId = findViewById(R.id.staff_id);
        etName = findViewById(R.id.name);
        etContact = findViewById(R.id.contact);
        etMember = findViewById(R.id.membership);
        etLicense1 = findViewById(R.id.license1);
        //etLicense2 = findViewById(R.id.license2);
        etSchool = findViewById(R.id.school);
        //rgSchool = findViewById(R.id.school);
        etDesignation = findViewById(R.id.designation);

        btnUpdate = findViewById(R.id.update);
        btnDelete = findViewById(R.id.delete);
        btnCancel = findViewById(R.id.cancel);

        // Update Button
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id1 = tvStaffId.getText().toString();
                String name1 = etName.getText().toString();
                String no = etContact.getText().toString();
                String mem1 = etMember.getText().toString();
                String lp = etLicense1.getText().toString();
                String sch1 = etSchool.getText().toString();
                String des1 = etDesignation.getText().toString();


                if (!id1.equals("")) {
                    if (!name1.equals("")) {
                        if (no.length() != 0) {
                            if (!lp.equals("")) {
                                ConnectMySql updatesql = new ConnectMySql();
                                updatesql.execute("");
                                Intent intent = new Intent(Update.this, AdminPage.class);
                                startActivity(intent);
                                String msg = "Successfully updated";
                                Toast.makeText(Update.this, msg, Toast.LENGTH_SHORT).show();
                            } else {
                                String msg = "Enter owner's vehicle license plate.";
                                Toast.makeText(Update.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            String msg = "Enter owner's contact number.";
                            Toast.makeText(Update.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        String msg = "Enter owner's name.";
                        Toast.makeText(Update.this, msg, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String msg = "Enter owner's id.";
                    Toast.makeText(Update.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Cancel Button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(Update.this);
                myBuilder.setMessage("Do you want to exit?");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("No", null);
                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Update.this, AdminPage.class);
                        startActivity(intent);
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        // Delete Button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(Update.this);
                myBuilder.setMessage("Do you want to Delete?");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("No", null);
                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Update.this, AdminPage.class);
                        startActivity(intent);
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }

    //if (rgSchool.getCheckedRadioButtonId() == R.id.soi) {


//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //grab string from TextInputEditText
//                String username, password;
//                username = String.valueOf(textUsername.getText().toString());
//                password = String.valueOf(textPassword.getText().toString());
//                //Check if all fields are entered
//                if (!username.equals("") && !password.equals("")) {
//                    //execute sql code (see below at ConnectMySql private class)
//                    MainActivity.ConnectMySql connectMySql = new MainActivity.ConnectMySql();
//                    connectMySql.execute("");
//                }
//                //run if one of the fields are empty
//                else {
//                    Toast.makeText(getApplicationContext(),"All fields required",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    //}

    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String id = tvStaffId.getText().toString();
        String name = etName.getText().toString();
        String no = etContact.getText().toString();
        String mem = etMember.getText().toString();
        String lp = etLicense1.getText().toString();
        String sch = etSchool.getText().toString();
        String des = etDesignation.getText().toString();

        //
        @Override
        protected String doInBackground(String... args) {
            String res = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection success");
                //set sql string
                String sql = "UPDATE owner SET id = id, name = name, contact = no, license plate = lp, school = sch, designation = des" +
                        "WHERE staff_id = tvStaffId ";
                String result = "Database Connection Successful\n";

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                while (rs.next()) {
                    result = rs.getString(1).toString();
                }
                res = result;
            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }
    }
}