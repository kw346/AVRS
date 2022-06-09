package sg.edu.rp.c346.id20022735.avrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminPage extends AppCompatActivity {
    private static final String url = "jdbc:mysql://ChangeThisToYourIPWhenTesting:3306/fyp";
    private static final String user = "root";
    private static final String pass = "123";
    Button btn1;
    ListView lv;
    ArrayList<License> licenseList;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        lv = findViewById(R.id.lv);

        btn1 = findViewById(R.id.add);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this, Add.class);
                startActivity(intent);
            }
        });
    }
//    private class ConnectMySql extends AsyncTask<String, Void, String> {
//        //define variables
//        String res = "";
//        String username = String.valueOf(textUsername.getText().toString());
//        String password = String.valueOf(textPassword.getText().toString());


    //main part where it process
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection con = DriverManager.getConnection(url, user, pass);
//                System.out.println("Database connection success");
//                //set sql string
//                String sql = "SELECT type FROM user WHERE username='"+username+"' AND password='"+password+"'";
//                String result = "Database Connection Successful\n";
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

//        @Override
//        protected void onPostExecute(String result) {
//            //check if status is login or not (if not means invalid account or wrong password)
//            if (result.contains("admin")) {
//                Intent intent = new Intent(MainActivity.this, AdminPage.class);
//                startActivity(intent);
//            }
//            else if (result.contains("guard")) {
//                Intent intent = new Intent(MainActivity.this, HomePage.class);
//                startActivity(intent);
//            }
//            //if account invalid
//            else {
//                Toast.makeText(getApplicationContext(),"Invalid account",Toast.LENGTH_SHORT).show();
//            }
}

