package com.assignment.freshly;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.freshly.asyncTask.customerTask.InsertCustomerTask;
import com.assignment.freshly.asyncTask.vendorTask.GetVendorsTask;
import com.assignment.freshly.entity.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerSignup extends AppCompatActivity {

    private boolean isUnique = true;
    EditText etName;
    EditText etEmail;
    EditText etPass;
    RadioGroup genderChoices;
    RadioButton male;
    RadioButton female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup);

        Button loginbtn = findViewById(R.id.customerLogin);
        loginbtn.setOnClickListener(v->{
            Intent intent = new Intent(CustomerSignup.this, CustomerLogin.class);
            startActivity(intent);
        });

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.cemail);
        etPass = findViewById(R.id.cpassword);
        genderChoices = findViewById(R.id.gender);
        male = findViewById(R.id.radio_male);
        female = findViewById(R.id.radio_female);
        Button signupbtn = findViewById(R.id.signupbtn);

        signupbtn.setOnClickListener(v->{
            String email = etEmail.getText().toString().trim();
            String name = etName.getText().toString();
            String password = etPass.getText().toString();
            String imagePath = "imagePath";
            char gender='y';
            int selectedId = -1;
            selectedId = genderChoices.getCheckedRadioButtonId();
            if (selectedId == R.id.radio_male) {
               gender = 'M';
            } else if (selectedId == R.id.radio_female) {
                gender = 'F';
            }

            if(isValidEmail(email) && selectedId!=-1 && name!=null && password!=null){
                new InsertCustomerTask(getApplicationContext()).execute(new Customer(email, name, password, imagePath, gender));
                etPass.setText("");
                etEmail.setText("");
                etName.setText("");

                Intent intent = new Intent(CustomerSignup.this, CustomerLandingPage.class);
                startActivity(intent);
            }

        });
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}