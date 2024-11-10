package com.assignment.freshly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.assignment.freshly.asyncTask.productTask.DeleteProductTask;
import com.assignment.freshly.asyncTask.productTask.GetProductsByVendorTask;
import com.assignment.freshly.asyncTask.productTask.UpdateProductTask;
import com.assignment.freshly.asyncTask.vendorTask.GetVendor;
import com.assignment.freshly.entity.Product;
import com.assignment.freshly.entity.Vendor;

import java.util.List;

public class VendorLandingAdapter extends BaseAdapter {
    List<Product> products;
    Context context;
    Product updatedProduct;


    VendorLandingAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.vendor_list_item, container, false);
        }
        ImageView imageView = convertView.findViewById(R.id.productimagevendor);
        TextView nametv = convertView.findViewById(R.id.productnamevendor);
        TextView pricetv = convertView.findViewById(R.id.productpricevendor);
        TextView desctv = convertView.findViewById(R.id.productdescvendor);
        TextView categorytv = convertView.findViewById(R.id.productcategoryvendor);
        Button updatebtn = convertView.findViewById(R.id.updateprod);
        Button deletebtn = convertView.findViewById(R.id.deleteprod);


//        imageView.setImageResource(products.get(i).getImage_path());
        nametv.setText(products.get(i).getTitle());
        pricetv.setText("$" + String.valueOf(products.get(i).getPrice()));
        desctv.setText(products.get(i).getDesc());

        int c = products.get(i).getCategory_id();
        String cname = "";
        if (c == 1) {
            cname = "Dry Fruit";
            imageView.setImageResource(R.drawable.dry_fruits);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else if (c == 2) {
            cname = "Vegetable";
            imageView.setImageResource(R.drawable.vegetables);
        } else if (c == 3) {
            cname = "Fruit";
            imageView.setImageResource(R.drawable.fruits);
        }
        categorytv.setText(cname);

        deletebtn.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Are you sure you want to delete "+products.get(i).getTitle()+"?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        new DeleteProductTask(context).execute(products.get(i));
                        products.remove(i);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        updatebtn.setOnClickListener(v -> {
            View dialogView = LayoutInflater.from(context).inflate(R.layout.update_product, null);

            EditText editTitle = dialogView.findViewById(R.id.edit_title);
            EditText editPrice = dialogView.findViewById(R.id.edit_price);
            EditText editDesc = dialogView.findViewById(R.id.edit_desc);
            EditText editCategory = dialogView.findViewById(R.id.edit_category);

            editTitle.setText(products.get(i).getTitle());
            editPrice.setText(String.valueOf(products.get(i).getPrice()));
            editDesc.setText(products.get(i).getDesc());
//            editCategory.setText(String.valueOf(products.get(i).getCategory_id()));

            new AlertDialog.Builder(context)
                    .setView(dialogView)
                    .setPositiveButton("Update", (dialog, which) -> {
                        String updatedTitle = editTitle.getText().toString();
                        float updatedPrice = Float.parseFloat(editPrice.getText().toString());
                        String updatedDesc = editDesc.getText().toString();
                        String categoryText = editCategory.getText().toString();
                        int updatedCategory = 0;
                        if (categoryText.equalsIgnoreCase("dry fruit")) {
                            updatedCategory = 1;
                        } else if (categoryText.equalsIgnoreCase("vegetable")) {
                            updatedCategory = 2;
                        } else if (categoryText.equalsIgnoreCase("fruit")) {
                            updatedCategory = 3;
                        }

                        updatedProduct = products.get(i);
                        updatedProduct.setTitle(updatedTitle);
                        updatedProduct.setPrice(updatedPrice);
                        updatedProduct.setDesc(updatedDesc);
                        updatedProduct.setCategory_id(updatedCategory);

                        new UpdateProductTask(context).execute(updatedProduct);
                        Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();

                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });


        return convertView;
    }
}
