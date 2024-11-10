package com.assignment.freshly.asyncTask.customerTask;


import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.dao.CustomerDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Customer;

public class UpdateCustomerTask extends AsyncTask<Customer, Void, Void> {
    Context context;

    public UpdateCustomerTask(Context context) {
        this.context = context;
    }
    @Override
    protected Void doInBackground(Customer... customers) {
        CustomerDao cDao = FreshlyDatabaseClient.getInstance(this.context).db.customerDao();
        return cDao.update(customers[0]);
    }
}