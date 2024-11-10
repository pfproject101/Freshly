package com.assignment.freshly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.freshly.asyncTask.categoryTask.InsertCategory;
import com.assignment.freshly.asyncTask.customerTask.GetCustomerTask;
import com.assignment.freshly.asyncTask.vendorTask.GetVendor;
import com.assignment.freshly.asyncTask.vendorTask.GetVendorTask;
import com.assignment.freshly.entity.Category;
import com.assignment.freshly.entity.Vendor;

public class VendorLogin extends AppCompatActivity implements GetVendor {

    private EditText etName;
    private EditText etPass;
    private Button loginbtn;
    private String username ;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);

        Button signupbtn = findViewById(R.id.signup);
        signupbtn.setOnClickListener(v->{
            Intent intent = new Intent(VendorLogin.this, VendorSignup.class);
            startActivity(intent);
        });

        etName = findViewById(R.id.vusername);
        etPass = findViewById(R.id.vpassword);
        loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(v->{
            username = etName.getText().toString().trim();
            password = etPass.getText().toString();
            new GetVendorTask(getApplicationContext(), this).execute(username, password);


        });

    }

    @Override
    public void onVendorReceived(Vendor vendor) {
        if (vendor!=null){
            SharedPreferences sp = getSharedPreferences("UserFile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();

            Intent intent = new Intent(VendorLogin.this, VendorLandingPage.class);
            startActivity(intent);

        }
    }
}