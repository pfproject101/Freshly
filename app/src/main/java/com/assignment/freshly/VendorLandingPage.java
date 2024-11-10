package com.assignment.freshly;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.freshly.asyncTask.categoryTask.GetCategoriesTask;
import com.assignment.freshly.asyncTask.categoryTask.GetCategoryNames;
import com.assignment.freshly.asyncTask.productTask.GetProductsByVendor;
import com.assignment.freshly.asyncTask.productTask.GetProductsByVendorTask;
import com.assignment.freshly.asyncTask.productTask.InsertProductTask;
import com.assignment.freshly.asyncTask.vendorTask.GetVendor;
import com.assignment.freshly.asyncTask.vendorTask.GetVendorTask;
import com.assignment.freshly.entity.Product;
import com.assignment.freshly.entity.Vendor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VendorLandingPage extends AppCompatActivity implements GetProductsByVendor, GetVendor {

    ListView listview;
    FloatingActionButton addprod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_landing_page);

        //get curr vendor
        SharedPreferences sp = getApplicationContext().getSharedPreferences("UserFile", MODE_PRIVATE);
        String username = sp.getString("username", "");
        String password = sp.getString("password", "");
        new GetVendorTask(getApplicationContext(), this).execute(username, password);

        addprod = findViewById(R.id.addprod);
        addprod.setOnClickListener(v->{AddProduct("v1");});
        listview = findViewById(R.id.listviewvendor);

    }

    @Override
    public void onVendorReceived(Vendor vendor) {
        String currVendor = vendor.getUsername();
        new GetProductsByVendorTask(getApplicationContext(), this).execute(currVendor);
        addprod.setOnClickListener(v->{AddProduct(currVendor);});

    }

    @Override
    public void onProductsReceived(List<Product> products) {
        VendorLandingAdapter adapter = new VendorLandingAdapter(this, products);
        listview.setAdapter(adapter);
    }

    public void AddProduct(String username){
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.update_product, null);

        TextView tv = dialogView.findViewById(R.id.heading);
        tv.setText("Add New Product");
        EditText productNameEditText = dialogView.findViewById(R.id.edit_title);
        EditText productDescriptionEditText = dialogView.findViewById(R.id.edit_desc);
        EditText productPriceEditText = dialogView.findViewById(R.id.edit_price);
        EditText productCategoryEditText = dialogView.findViewById(R.id.edit_category);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = productNameEditText.getText().toString();
                    String description = productDescriptionEditText.getText().toString();
                    String priceString = productPriceEditText.getText().toString();
                    float price = Float.parseFloat(priceString);
                    int category = 0;
                    String categoryText = productCategoryEditText.getText().toString().trim(); //fruit, vegetable, dry fruit
                    if (categoryText.equalsIgnoreCase("dry fruit")){
                        category=1;
                    }
                    else if(categoryText.equalsIgnoreCase("vegetable")){
                        category=2;
                    }
                    else if (categoryText.equalsIgnoreCase("fruit")){
                        category=3;
                    }

                    if (name.isEmpty() || description.isEmpty() || priceString.isEmpty() || category==0)  {
                        Toast.makeText(VendorLandingPage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Product newProduct = new Product(name, description, price, category, username, "imagePath");
                    new InsertProductTask(getApplicationContext()).execute(newProduct);
                    Toast.makeText(VendorLandingPage.this, newProduct.getTitle()+" added!", Toast.LENGTH_SHORT).show();
                    new GetProductsByVendorTask(getApplicationContext(), this).execute(username);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();

    }

}


