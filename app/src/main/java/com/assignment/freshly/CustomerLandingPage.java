package com.assignment.freshly;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.assignment.freshly.asyncTask.categoryTask.InsertCategory;
import com.assignment.freshly.asyncTask.productTask.GetProductsByCategory;
import com.assignment.freshly.asyncTask.productTask.GetProductsByCategoryTask;
import com.assignment.freshly.asyncTask.productTask.GetProductsBySearch;
import com.assignment.freshly.asyncTask.productTask.GetProductsBySearchTask;
import com.assignment.freshly.asyncTask.productTask.InsertProductTask;
import com.assignment.freshly.entity.Category;
import com.assignment.freshly.entity.Product;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class CustomerLandingPage extends AppCompatActivity implements GetProductsBySearch, GetProductsByCategory {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CustomerLandingAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_landing_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        listView = findViewById(R.id.listview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Category results
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.dryFruits){
                new GetProductsByCategoryTask(getApplicationContext(), this).execute(1);
                Toast.makeText(this, "Dry Fruits", Toast.LENGTH_SHORT).show();
            }
            else if(item.getItemId() == R.id.vegetables) {
                new GetProductsByCategoryTask(getApplicationContext(), this).execute(2);
                Toast.makeText(this, "Vegetables", Toast.LENGTH_SHORT).show();
            }
            else if(item.getItemId() == R.id.fruits) {
                new GetProductsByCategoryTask(getApplicationContext(), this).execute(3);
                Toast.makeText(this, "Fruits", Toast.LENGTH_SHORT).show();
            }

            drawerLayout.closeDrawers();
            return true;
        });


        // Search results
        Button searchbtn  = findViewById(R.id.searchbtn);
        EditText et = findViewById(R.id.searchinput);

        searchbtn.setOnClickListener(v->{
            String input = et.getText().toString();
            String searchQuery = "%"+input+"%";
            new GetProductsBySearchTask(getApplicationContext(), this).execute(searchQuery);
        });

    }

    @Override
    public void onProductsReceived(List<Product> products) {
        if (products != null && !products.isEmpty()) {
            adapter = new CustomerLandingAdapter(getApplicationContext(), products);
            listView.setAdapter(adapter);
        }
        else {
            adapter = new CustomerLandingAdapter(getApplicationContext(), products);
            listView.setAdapter(adapter);
            Toast.makeText(this, "No products found", Toast.LENGTH_SHORT).show();
        }
    }
}
