package com.assignment.freshly.asyncTask.vendorTask;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.dao.VendorDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Vendor;

public class GetVendorTask extends AsyncTask<String, Void, Vendor> {
    Context context;
    GetVendor listener;

    public GetVendorTask(Context context, GetVendor l) {
        this.context = context;
        this.listener = l;
    }

    @Override
    protected Vendor doInBackground(String... strings) {
        VendorDao vDao = FreshlyDatabaseClient.getInstance(this.context).db.vendorDao();
        return vDao.getVendor(strings[0], strings[1]);
    }

    @Override
    protected void onPostExecute(Vendor vendor) {
        super.onPostExecute(vendor);
        if(vendor != null){
            listener.onVendorReceived(vendor);
        }
    }
}

