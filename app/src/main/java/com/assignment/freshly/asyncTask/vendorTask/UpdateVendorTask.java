package com.assignment.freshly.asyncTask.vendorTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.freshly.dao.VendorDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Vendor;

public class UpdateVendorTask extends AsyncTask<Vendor, Void, Void> {
    Context context;

    public UpdateVendorTask(Context context) {
        this.context = context;
    }
    @Override
    protected Void doInBackground(Vendor... vendors) {
        VendorDao vDao = FreshlyDatabaseClient.getInstance(this.context).db.vendorDao();
        return vDao.update(vendors[0]);
    }
}