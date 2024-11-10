package com.assignment.freshly.asyncTask.vendorTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.freshly.dao.VendorDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Vendor;

public class InsertVendorTask extends AsyncTask<Vendor, Void, Long> {
    Context context;

    public InsertVendorTask(Context context) {
        this.context = context;
    }
    @Override
    protected Long doInBackground(Vendor... vendors) {
        VendorDao vDao = FreshlyDatabaseClient.getInstance(this.context).db.vendorDao();
        return vDao.insert(vendors[0]);
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