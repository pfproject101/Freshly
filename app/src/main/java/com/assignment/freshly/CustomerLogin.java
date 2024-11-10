package com.assignment.freshly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.freshly.asyncTask.customerTask.GetCustomer;
import com.assignment.freshly.asyncTask.customerTask.GetCustomerTask;
import com.assignment.freshly.entity.Customer;

public class CustomerLogin extends AppCompatActivity implements GetCustomer {

    private EditText etName;
    private EditText etPass;
    private Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_login);

        Button signupbtn = findViewById(R.id.signup2);
        signupbtn.setOnClickListener(v->{
            Intent intent = new Intent(CustomerLogin.this, CustomerSignup.class);
            startActivity(intent);
        });

        etName = findViewById(R.id.clemail);
        etPass = findViewById(R.id.clpassword);
        loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(v->{
            String email = etName.getText().toString().trim();
            String password = etPass.getText().toString();
            new GetCustomerTask(getApplicationContext(), this).execute(email, password);

//            SharedPreferences sp = getSharedPreferences("UserFile", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("username", email);
//            editor.putString("password", password);
//            editor.apply();

        });

    }

    @Override
    public void onCustomerReceived(Customer customer) {
        if (customer!=null){
            Intent intent = new Intent(CustomerLogin.this, CustomerLandingPage.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Customer Not Found", Toast.LENGTH_SHORT).show();
        }
    }
}