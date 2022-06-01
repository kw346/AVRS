package sg.edu.rp.c346.id20022735.avrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {
//    private static final String url = "jdbc:mysql://116.89.52.49:3306/fyp";
//    private static final String user = "root";
//    private static final String pass = "123";
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

}