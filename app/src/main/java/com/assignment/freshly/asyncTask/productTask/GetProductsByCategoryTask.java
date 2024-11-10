package com.assignment.freshly.asyncTask.productTask;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.dao.ProductDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Product;

import java.util.List;

public class GetProductsByCategoryTask extends AsyncTask<Integer, Void, List<Product>> {
    Context context;
    GetProductsByCategory listener;

    public GetProductsByCategoryTask(Context context, GetProductsByCategory l) {
        this.context = context;
        this.listener = l;
    }
    @Override
    protected List<Product> doInBackground(Integer... integers) {
        ProductDao pDao = FreshlyDatabaseClient.getInstance(this.context).db.productDao();
        return pDao.getProductsByCategory(integers[0]); // category id
    }

    @Override
    protected void onPostExecute(List<Product> products) {
        super.onPostExecute(products);
        if(products != null){
            listener.onProductsReceived(products);
        }
    }
}

