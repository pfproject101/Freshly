package com.assignment.freshly.asyncTask.productTask;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.dao.ProductDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Product;

import java.util.List;

public class GetProductsBySearchTask extends AsyncTask<String, Void, List<Product>> {
    Context context;
    GetProductsBySearch listener;

    public GetProductsBySearchTask(Context context, GetProductsBySearch listener) {
        this.context = context;
        this.listener = listener;
    }
    @Override
    protected List<Product> doInBackground(String... strings) {
        ProductDao pDao = FreshlyDatabaseClient.getInstance(this.context).db.productDao();
        return pDao.getProductsBySearch(strings[0]); //search query
    }

    @Override
    protected void onPostExecute(List<Product> products) {
        super.onPostExecute(products);
        if(products != null){
            listener.onProductsReceived(products);
        }
    }

}

