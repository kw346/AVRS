package sg.edu.rp.c346.id20022735.avrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;


public class HomePage extends AppCompatActivity {
    //db connection info
    Connection con;
    //declare imageview button textview
    private ImageView imageView;
    private Button scan, check, read;
    private TextView textView;
    private EditText editText;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //find view by id
        scan = findViewById(R.id.scan);
        check = findViewById(R.id.check);
        read = findViewById(R.id.read);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectTextFromImage();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                ConnectMySql connectMySql = new ConnectMySql();
                connectMySql.execute("");
            }
        });
    }
    //take photo with camera app
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }
    //get image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
    // as name suggest, detect text from image
    private void detectTextFromImage() {
        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap((imageBitmap));
        FirebaseVisionTextDetector firebaseVisionTextDetector = FirebaseVision.getInstance().getVisionTextDetector();
        firebaseVisionTextDetector.detectInImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                displayTextFromImage(firebaseVisionText);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomePage.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // as name suggest, display text from image
    private void displayTextFromImage(FirebaseVisionText firebaseVisionText) {
        List<FirebaseVisionText.Block> blockList = firebaseVisionText.getBlocks();
        if (blockList.size() == 0) {
            Toast.makeText(this, "No text found in image", Toast.LENGTH_SHORT).show();
        }
        else {
            for (FirebaseVisionText.Block block : firebaseVisionText.getBlocks()) {
                editText.setText("");
                String text = block.getText();
                editText.setText(text);
            }
        }
    }

    private class ConnectMySql extends AsyncTask<String, Void, String> {
        //define variables
        String res = "";
        String licensePlate = "";

        //runs before executing sql
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            licensePlate = String.valueOf(editText.getText().toString());
            licensePlate = licensePlate.replaceAll("\\s", "");
            licensePlate = licensePlate.toUpperCase();
        }

        //main part where it process
        @Override
        protected String doInBackground(String... params) {
            try {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                con = connectionHelper.conclass();
                //set sql string
                String sql = "SELECT name, designation, mobile FROM owner WHERE license_no='" + licensePlate + "'";
                String result = "BLANK";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    result = "Name: " + rs.getString(1).toString() + "\n";
                    result += "Designation: " + rs.getString(2).toString() + "\n";
                    result += "Mobile: " + rs.getString(3).toString();
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
            //check if blank (meaning no owner found)
            if (result.contains("BLANK")) {
                Toast.makeText(getApplicationContext(), "Owner not Found", Toast.LENGTH_SHORT).show();
            }
            //if owner found
            else {
                textView.setText(result);
            }
        }
    }
}