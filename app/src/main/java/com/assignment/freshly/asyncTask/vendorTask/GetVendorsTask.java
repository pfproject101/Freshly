package com.assignment.freshly.asyncTask.vendorTask;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.dao.VendorDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Vendor;

import java.util.List;

public class GetVendorsTask extends AsyncTask<Void, Void, List<Vendor>> {
    Context context;
    GetVendors listener;

    public GetVendorsTask(Context context, GetVendors l) {
        this.context = context;
        this.listener = l;
    }

    @Override
    protected List<Vendor> doInBackground(Void...voids) {
        VendorDao vDao = FreshlyDatabaseClient.getInstance(this.context).db.vendorDao();
        return vDao.getVendors();
    }

    @Override
    protected void onPostExecute(List<Vendor> vendors) {
        super.onPostExecute(vendors);
        if(vendors != null){
            listener.onVendorsReceived(vendors);
        }
    }
}

