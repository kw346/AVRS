package sg.edu.rp.c346.id20022735.avrs;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;


public class ConnectionHelper {
    Connection con;
    String url, user, pass;
    @SuppressLint("NewApi")
    public Connection conclass() {

        url = "jdbc:mysql://192.168.110.1:3306/fyp";
        user = "root";
        pass = "123";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, pass);
            Log.e("Error", "Database connection success");

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return con;
    }
}
