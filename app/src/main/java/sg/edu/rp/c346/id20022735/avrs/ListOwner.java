package sg.edu.rp.c346.id20022735.avrs;

import com.mysql.jdbc.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOwner {
    Connection connect;
    String connectionres="";
    Boolean isSuccess=false;

    public List<Map<String,String>> getList(){
        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String,String>>();

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.conclass();
            if(connect !=null){
                String all = "select * from owner";
                Statement statement = connect.createStatement();
                ResultSet resSet= statement.executeQuery(all);

                while(resSet.next()){
                    Map<String,String> dt = new HashMap<String, String>();
                    dt.put("name",resSet.getString("name"));
                    dt.put("license",resSet.getString("license_no"));
                    dt.put("school",resSet.getString("school"));
                    dt.put("designation",resSet.getString("designation"));
                    dt.put("contact",resSet.getString("mobile"));
                    data.add(dt);
                }
                connectionres="Success";
                isSuccess= true;
                connect.close();
            }
            else{
                connectionres="Failed";
            }

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return data;
    }
}
