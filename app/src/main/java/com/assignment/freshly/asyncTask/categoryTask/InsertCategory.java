package com.assignment.freshly.asyncTask.categoryTask;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.assignment.freshly.dao.CategoryDao;
import com.assignment.freshly.database.FreshlyDatabase;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Category;

public class InsertCategory extends AsyncTask<Category, Void, Long> {
    Context context;

    public InsertCategory(Context context) {
        this.context = context;
    }
    @Override
    protected Long doInBackground(Category... categories) {
        CategoryDao cDao = FreshlyDatabaseClient.getInstance(this.context).db.categoryDao();
        return cDao.insert(categories[0]);
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