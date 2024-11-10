package com.assignment.freshly.asyncTask.productTask;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.dao.ProductDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Product;

import java.util.List;

public class GetProductsByVendorTask extends AsyncTask<String, Void, List<Product>> {
    Context context;
    GetProductsByVendor listener;

    public GetProductsByVendorTask(Context context, GetProductsByVendor listener) {
        this.context = context;
        this.listener = listener;
    }
    @Override
    protected List<Product> doInBackground(String... strings) {
        ProductDao pDao = FreshlyDatabaseClient.getInstance(this.context).db.productDao();
        return pDao.getProductsByVendor(strings[0]); // vendor username
    }

    @Override
    protected void onPostExecute(List<Product> products) {
        super.onPostExecute(products);
        if(products != null){
            listener.onProductsReceived(products);
        }
    }
}
