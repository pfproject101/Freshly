package com.assignment.freshly;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.freshly.asyncTask.vendorTask.GetVendors;
import com.assignment.freshly.asyncTask.vendorTask.GetVendorsTask;
import com.assignment.freshly.asyncTask.vendorTask.InsertVendorTask;
import com.assignment.freshly.entity.Vendor;

import java.util.List;

public class VendorSignup extends AppCompatActivity implements GetVendors {

    private boolean isUnique = true;
    EditText etName;
    EditText etAddress;
    EditText etPhone;
    EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vendor_signup);

        Button loginbtn = findViewById(R.id.textView4);
        loginbtn.setOnClickListener(v->{
            Intent intent = new Intent(VendorSignup.this, VendorLogin.class);
            startActivity(intent);
        });

        etName = findViewById(R.id.username);
        etAddress = findViewById(R.id.address);
        etPhone = findViewById(R.id.phone);
        etPass = findViewById(R.id.password);
        Button signupbtn = findViewById(R.id.signupbtn);

        signupbtn.setOnClickListener(v->{
            new GetVendorsTask(getApplicationContext(), this).execute();
        });

    }

    @Override
    public void onVendorsReceived(List<Vendor> vendors) {

        String address = etAddress.getText().toString();
        String password = etPass.getText().toString();

        String phoneNum = etPhone.getText().toString();
        boolean isCorrect = true;
        isCorrect =Patterns.PHONE.matcher(phoneNum).matches();

        String username = etName.getText().toString().trim();
        for (Vendor vendor : vendors) {
            if (vendor.getUsername().equalsIgnoreCase(username)) {
                isUnique = false;
                break;
            }
        }

        if (isUnique && isCorrect && username!=null)
        {
            new InsertVendorTask(getApplicationContext()).execute(new Vendor(username, address, password, phoneNum));
            etAddress.setText("");
            etName.setText("");
            etPass.setText("");
            etPhone.setText("");
            isUnique=true;

            Intent intent = new Intent(VendorSignup.this, VendorLandingPage.class);
            startActivity(intent);
        }
        else if (!isUnique)
        {
            etName.setError("Username already exists");
        }
        else if(!isCorrect){
            etPhone.setError("Incorrect phone number format");
        }
    }
}
