package com.assignment.freshly;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.freshly.entity.Product;

import org.w3c.dom.Text;

import java.util.List;

public class CustomerLandingAdapter extends BaseAdapter {
    List<Product> products;
    Context context;

    CustomerLandingAdapter(Context context, List<Product> products){
        this.context = context;
        this.products=products;
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
        if (convertView==null){
            convertView = LayoutInflater.from(this.context).inflate(R.layout.customer_list_item, container, false);
        }
        ImageView imageView = convertView.findViewById(R.id.productImage);
        TextView nametv = convertView.findViewById(R.id.productName);
        TextView pricetv = convertView.findViewById(R.id.productPrice);
        TextView desctv = convertView.findViewById(R.id.productDesc);
        TextView categorytv = convertView.findViewById(R.id.productCategory);
        Button addToCartbtn = convertView.findViewById(R.id.addtocart);

//        imageView.setImageResource(products.get(i).getImage_path());
        nametv.setText(products.get(i).getTitle());
        pricetv.setText("$"+String.valueOf(products.get(i).getPrice()));
        desctv.setText(products.get(i).getDesc());

        int c = products.get(i).getCategory_id();
        String cname="";
        if (c==1){
            cname = "Dry Fruit";
            imageView.setImageResource(R.drawable.dry_fruits);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else if(c==2){
            cname="Vegetable";
            imageView.setImageResource(R.drawable.vegetables);
        }
        else if(c==3){
            cname="Fruit";
            imageView.setImageResource(R.drawable.fruits);
        }
        categorytv.setText(cname);

        addToCartbtn.setOnClickListener(v->{
            SharedPreferences sp = context.getSharedPreferences("UserFile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(String.valueOf(products.get(i).getId()),products.get(i).getTitle());
            editor.apply();
            Toast.makeText(context, "Added!", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
