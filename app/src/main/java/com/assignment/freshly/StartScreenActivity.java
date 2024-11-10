package com.assignment.freshly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.assignment.freshly.asyncTask.categoryTask.InsertCategory;
import com.assignment.freshly.entity.Category;


public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        SharedPreferences sp = getSharedPreferences("UserFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        Button customer = findViewById(R.id.customer);
        Button vendor = findViewById(R.id.vendor);

        customer.setOnClickListener(v->{
            editor.putString("user_type", "customer");
            editor.apply();
            Intent intent = new Intent(StartScreenActivity.this, CustomerLogin.class);
            startActivity(intent);

        });
        vendor.setOnClickListener(v->{
            editor.putString("user_type", "vendor");
            editor.apply();
            Intent intent = new Intent(StartScreenActivity.this, VendorLogin.class);
            startActivity(intent);
        });

        boolean isInitialized = sp.getBoolean("isInitialized", false);
        if (!isInitialized){
            new InsertCategory(getApplicationContext()).execute(new Category("Dry Fruit"));
            new InsertCategory(getApplicationContext()).execute(new Category("Vegetable"));
            new InsertCategory(getApplicationContext()).execute(new Category("Fruit"));
            editor.putBoolean("isInitialized", true).apply();
        }

    }
}