package sg.edu.rp.c346.id20022735.avrs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Update extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText etStaffId, etName, etLicense1, etLicense2, etDesignation, etContact;
    RadioGroup rd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnUpdate = findViewById(R.id.update);
        //btnDelete = findViewById(R.id.delete);
        btnCancel = findViewById(R.id.cancel);
        etStaffId = findViewById(R.id.staff_id);
        etName = findViewById(R.id.name);
        etLicense1 = findViewById(R.id.license1);
        etLicense2 = findViewById(R.id.license2);
        etDesignation = findViewById(R.id.designation);
        etContact = findViewById(R.id.contact);
        rd1 = findViewById(R.id.);

        if (rd1.getCheckedRadioButtonId() == R.id.) {

        }

    }
}