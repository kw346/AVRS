package sg.edu.rp.c346.id20022735.avrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Add extends AppCompatActivity {
    private static final String url = "jdbc:mysql://116.89.52.49:3306/fyp";
    private static final String user = "root";
    private static final String pass = "123";
    Button btnadd,btncel;
    EditText id,name,contact,member,license,des;
    RadioGroup schrg;
    RadioButton soi,shl,smc,soe,sta,soh,sas;

    String res = "";
    int id1 = Integer.getInteger(id.getText().toString());
    String name1 = String.valueOf(name.getText().toString());
    int no = Integer.getInteger(contact.getText().toString());
    String mem1 = String.valueOf(member.getText().toString());
    String lp = String.valueOf(license.getText().toString());
    String des1 = String.valueOf(des.getText().toString());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnadd = findViewById(R.id.add);
        btncel=findViewById(R.id.cancel);
        id =findViewById(R.id.staff_id);
        name=findViewById(R.id.name);
        contact=findViewById(R.id.contact);
        member =findViewById(R.id.membership);
        license=findViewById(R.id.license);
        schrg=findViewById(R.id.schgrp);
        soi=findViewById(R.id.soi);
        shl=findViewById(R.id.shl);
        smc=findViewById(R.id.smc);
        soe=findViewById(R.id.soe);
        sta=findViewById(R.id.sta);
        soh=findViewById(R.id.soh);
        sas=findViewById(R.id.sas);
        des=findViewById(R.id.designation);



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id1 != 0){
                    if (name1.trim().length() != 0){
                        if (no != 0){
                            if (lp.trim().length() != 0){
                                Add.ConnectMySql connectMySql = new Add.ConnectMySql();
                                connectMySql.execute(res);
                            }
                            else{
                                String msg = "Enter owner's vehicle license plate.";
                                Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            String msg = "Enter owner's contact number.";
                            Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        String msg = "Enter owner's name.";
                        Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    String msg = "Enter owner's id.";
                    Toast.makeText(Add.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private class ConnectMySql extends AsyncTask<String, Void, String> {
        //main part where it process
        @Override
        protected String doInBackground(String... params) {
            String sch = "";
            switch (schrg.getCheckedRadioButtonId()) {
                case R.id.soi:
                    sch = "SOI";
                    break;
                case R.id.shl:
                    sch = "SHL";
                    break;
                case R.id.smc:
                    sch = "SMC";
                    break;
                case R.id.soe:
                    sch = "SOE";
                    break;
                case R.id.sta:
                    sch = "STA";
                    break;
                case R.id.soh:
                    sch = "SOH";
                    break;
                case R.id.sas:
                    sch = "SAS";
                    break;
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection success");
                //set sql string
                String sql = "INSERT INTO owner(owner_id,name,mobile,membership,license_no,school, designation)" +
                        "VALUES("+id1+",'"+name1+"',"+no+",'"+mem1+"','"+lp+"','"+sch+"','"+des1+"');";
                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();

            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }
}
}