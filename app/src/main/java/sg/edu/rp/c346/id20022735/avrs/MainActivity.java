package sg.edu.rp.c346.id20022735.avrs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    //define variables
    Button btnLogin;
    TextInputEditText textUsername;
    TextInputEditText textPassword;
    public String status = "fail";
    public String type = "";

    Connection con;

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
                    new MainActivity.checklogin().execute("");
                }
                //run if one of the fields are empty
                else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class checklogin extends AsyncTask<String, Void, String> {
        //define variables
        String username = String.valueOf(textUsername.getText().toString());
        String password = String.valueOf(textPassword.getText().toString());
        String res="";
        Boolean isSuccess=false;

        //main part where it process
        @Override
        protected String doInBackground(String... params) {
            try {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                con = connectionHelper.conclass();
                if(con !=null){
                String sql = "SELECT type FROM user WHERE username='" + username + "' AND password='" + password + "'";
                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    result = rs.getString(1).toString();

                    if (result.contains("admin")) {
                        Intent intent = new Intent(MainActivity.this, AdminPage.class);
                        startActivity(intent);
                    } else if (result.contains("guard")) {
                        Intent intent = new Intent(MainActivity.this, HomePage.class);
                        startActivity(intent);
                    }
                    else {
                        username = "";
                        password = "";
                        Toast.makeText(getApplicationContext(), "Invalid account", Toast.LENGTH_SHORT).show();
                    }
                }

                    res="Success";
                    isSuccess= true;
                    con.close();
            }
                else{
                    res="Failed";
                }}
                catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error: ", e.getMessage());
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
}