package com.assignment.freshly.database;

import android.database.sqlite.SQLiteDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.assignment.freshly.dao.CategoryDao;
import com.assignment.freshly.dao.CustomerDao;
import com.assignment.freshly.dao.ProductDao;
import com.assignment.freshly.dao.VendorDao;
import com.assignment.freshly.entity.Category;
import com.assignment.freshly.entity.Customer;
import com.assignment.freshly.entity.Product;
import com.assignment.freshly.entity.Vendor;

@Database(entities={Product.class, Category.class, Customer.class, Vendor.class}, version=1)
public abstract class FreshlyDatabase extends RoomDatabase {

    public abstract ProductDao productDao();
    public abstract CategoryDao categoryDao();
    public abstract CustomerDao customerDao();
    public abstract VendorDao vendorDao();
}
