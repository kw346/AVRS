package sg.edu.rp.c346.id20022735.avrs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Update extends AppCompatActivity {

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
        etLicense2 = findViewById(R.id.license2);
        etDesignation = findViewById(R.id.designation);
        etContact = findViewById(R.id.contact);
        rgSchool = findViewById(R.id.school);
        btnUpdate = findViewById(R.id.update);
        //btnDelete = findViewById(R.id.delete);
        btnCancel = findViewById(R.id.cancel);


        if (rgSchool.getCheckedRadioButtonId() == R.id.soi) {

        }

        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(staff_id, name, license1, license2, designation, contact, school);
                System.out.println("Database connection success");
                //set sql string
                String sql = "UPDATE (table name) SET name = etName, license1 = etLicense1, license2 = etLicense2, designation = etDesignation, contact= etContact, school = rgSchool " +
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