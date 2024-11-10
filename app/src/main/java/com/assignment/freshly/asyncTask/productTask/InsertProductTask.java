package com.assignment.freshly.asyncTask.productTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.freshly.dao.ProductDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Product;

public class InsertProductTask extends AsyncTask<Product, Void, Long> {
    Context context;

    public InsertProductTask(Context context) {
        this.context = context;
    }
    @Override
    protected Long doInBackground(Product... products) {
        ProductDao pDao = FreshlyDatabaseClient.getInstance(this.context).db.productDao();
        return pDao.insert(products[0]);
    }

    @Override
    protected void onPostExecute(Long along) {
        super.onPostExecute(along);
        if (along!=-1){
            Toast.makeText(this.context,"Inserted with id "+along, Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this.context,"Failed to Insert ", Toast.LENGTH_LONG).show();
        }
    }
}