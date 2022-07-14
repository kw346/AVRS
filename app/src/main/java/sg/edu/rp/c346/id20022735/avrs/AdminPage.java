package sg.edu.rp.c346.id20022735.avrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminPage extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);


        btn1 = findViewById(R.id.add);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this, Add.class);
                startActivity(intent);
            }
        });

    }
    SimpleAdapter adap;
    public void GetList(View v){
        ListView lvlic = (ListView) findViewById(R.id.lv);

        List<Map<String,String>> datalist = null;
        ListOwners alldata= new ListOwners();
        datalist = alldata.getList();

        String[] cols = {"name","license","school","designation","contact"};
        int[] show= {R.id.name,R.id.license,R.id.school,R.id.designation,R.id.contact};
        adap = new SimpleAdapter(AdminPage.this,datalist,R.layout.activity_admin_list,cols,show);
        lvlic.setAdapter(adap);
    }

}

