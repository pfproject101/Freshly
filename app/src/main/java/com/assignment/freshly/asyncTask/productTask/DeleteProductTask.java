package com.assignment.freshly.asyncTask.productTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.freshly.dao.ProductDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Product;

public class DeleteProductTask extends AsyncTask<Product, Void, Void> {
    Context context;

    public DeleteProductTask(Context context) {
        this.context = context;
    }
    @Override
    protected Void doInBackground(Product... products) {
        ProductDao pDao = FreshlyDatabaseClient.getInstance(this.context).db.productDao();
        return pDao.delete(products[0]);
    }
}