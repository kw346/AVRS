package sg.edu.rp.c346.id20022735.avrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    //define variables
    private static final String url = "jdbc:mysql://116.89.52.49:3306/fyp";
    private static final String user = "root";
    private static final String pass = "123";
    Button btnLogin;
    TextInputEditText textUsername;
    TextInputEditText textPassword;
    public String status = "fail";
    public String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set id
        btnLogin = findViewById(R.id.login);
        textUsername = findViewById(R.id.textuser);
        textPassword = findViewById(R.id.textpass);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //grab string from TextInputEditText

                String username, password;
                username = String.valueOf(textUsername.getText().toString());
                password = String.valueOf(textPassword.getText().toString());
                //Check if all fields are entered
                if (!username.equals("") && !password.equals("")) {
                    //execute sql code (see below at ConnectMySql private class)
                    ConnectMySql connectMySql = new ConnectMySql();
                    connectMySql.execute("");
                }
                //run if one of the fields are empty
                else {
                    Toast.makeText(getApplicationContext(),"All fields required",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class ConnectMySql extends AsyncTask<String, Void, String> {
        //define variables
        String res = "";
        String username = String.valueOf(textUsername.getText().toString());
        String password = String.valueOf(textPassword.getText().toString());

        //runs before executing sql (NOT A MUST TO USE I THINK)
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Please wait...", Toast.LENGTH_SHORT)
                    .show();

        }
        //main part where it process
        @Override
        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection success");
                //set sql string
                String sql = "SELECT type FROM user WHERE username='"+username+"' AND password='"+password+"'";
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

        @Override
        protected void onPostExecute(String result) {
            //check if status is login or not (if not means invalid account or wrong password)
            if (result.contains("admin")) {
                Intent intent = new Intent(MainActivity.this, AdminPage.class);
                   startActivity(intent);
            }
            else if (result.contains("guard")) {
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                startActivity(intent);
            }
            //if account invalid
            else {
                Toast.makeText(getApplicationContext(),"Invalid account",Toast.LENGTH_SHORT).show();
            }
        }
    }
}