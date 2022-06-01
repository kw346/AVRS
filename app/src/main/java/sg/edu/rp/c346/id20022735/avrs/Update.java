package sg.edu.rp.c346.id20022735.avrs;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Update extends AppCompatActivity {
    private static final String url = "jdbc:mysql://116.89.52.49:3306/fyp";
    private static final String user = "root";
    private static final String pass = "123";

    TextView tvStaffId;
    EditText etName, etLicense1, etLicense2, etDesignation, etContact;
    RadioGroup rgSchool;
    Button btnUpdate, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tvStaffId = findViewById(R.id.staff_id);
        etName = findViewById(R.id.name);
        etLicense1 = findViewById(R.id.license1);
        //etLicense2 = findViewById(R.id.license2);
        etDesignation = findViewById(R.id.designation);
        etContact = findViewById(R.id.contact);
        rgSchool = findViewById(R.id.school);
        btnUpdate = findViewById(R.id.update);
        //btnDelete = findViewById(R.id.delete);
        btnCancel = findViewById(R.id.cancel);


        if (rgSchool.getCheckedRadioButtonId() == R.id.soi) {

        }
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

    }

//    private class ConnectMySql extends AsyncTask<String, Void, String> {
//        //define variables
//        String res = "";
//        String name = String.valueOf(etName.getText().toString());
//        String license1 = String.valueOf(etLicense1.getText().toString());
//        String designation = String.valueOf(etDesignation.getText().toString());
//        String contact = String.valueOf(etContact.getText().toString());
//        String school = String.valueOf(rgSchool.getText().toString());
//        //main part where it process
//        protected String doInBackground(String... params) {
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection con = DriverManager.getConnection(url, user, pass);
//                System.out.println("Database connection success");
//                //set sql string
//                String sql = "UPDATE owner SET name = etName, license1 = etLicense1, license2 = etLicense2, designation = etDesignation, mobile= etContact, school = rgSchool " +
//                        "WHERE staff_id = tvStaffId ";
//                String result = "Database Connection Successful\n";
//
//                Statement st = con.createStatement();
//                ResultSet rs = st.executeQuery(sql);
//                ResultSetMetaData rsmd = rs.getMetaData();
//                while (rs.next()) {
//                    result = rs.getString(1).toString();
//                }
//                res = result;
//            } catch (Exception e) {
//                e.printStackTrace();
//                res = e.toString();
//            }
//            return res;
//        }
//    }
}