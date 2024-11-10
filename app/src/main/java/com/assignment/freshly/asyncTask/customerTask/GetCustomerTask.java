package com.assignment.freshly.asyncTask.customerTask;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.freshly.dao.CustomerDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Customer;

import java.util.List;

public class GetCustomerTask extends AsyncTask<String, Void, Customer> {
    Context context;
    GetCustomer listener;

    public GetCustomerTask(Context context, GetCustomer listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Customer doInBackground(String... strings) {
        CustomerDao cDao = FreshlyDatabaseClient.getInstance(this.context).db.customerDao();
        return cDao.getCustomer(strings[0], strings[1]);
    }

    @Override
    protected void onPostExecute(Customer customer) {
        super.onPostExecute(customer);
        if(customer != null){
            listener.onCustomerReceived(customer);
        }
    }
}

