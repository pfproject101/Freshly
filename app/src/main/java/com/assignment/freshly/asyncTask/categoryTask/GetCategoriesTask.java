package com.assignment.freshly.asyncTask.categoryTask;


import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.dao.CategoryDao;
import com.assignment.freshly.database.FreshlyDatabaseClient;
import com.assignment.freshly.entity.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class GetCategoriesTask extends AsyncTask<Void, Void, List<Category>>{
    Context context;
    GetCategoryNames listener;

    public GetCategoriesTask(Context context, GetCategoryNames l) {
        this.context = context;
        this.listener = l;
    }

    @Override
    protected List<Category> doInBackground(Void... voids) {
        CategoryDao cDao = FreshlyDatabaseClient.getInstance(this.context).db.categoryDao();
        return cDao.getCategories();
    }

    @Override
    protected void onPostExecute(List<Category> categories) {
        super.onPostExecute(categories);
        if (categories != null && !categories.isEmpty()) {

            List<String> categoryNames = new ArrayList<>();
            for (Category category : categories) {
                categoryNames.add(category.getCategory_name());
            }

            listener.onCategoriesReceived(categoryNames);
        }
    }
}

