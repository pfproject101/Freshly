package com.assignment.freshly.asyncTask.customerTask;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.freshly.dao.CategoryDao;
import com.assignment.freshly.dao.CustomerDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Category;
import com.assignment.freshly.entity.Customer;

public class InsertCustomerTask extends AsyncTask<Customer, Void, Long> {
    Context context;

    public InsertCustomerTask(Context context) {
        this.context = context;
    }
    @Override
    protected Long doInBackground(Customer... customers) {
        CustomerDao cDao = FreshlyDatabaseClient.getInstance(this.context).db.customerDao();
        return cDao.insert(customers[0]);
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