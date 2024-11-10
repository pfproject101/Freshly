package com.assignment.freshly.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.freshly.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    public long insert(Product product);

    @Update
    public Void update(Product product);

    @Delete
    public Void delete(Product product);

    @Query("SELECT * FROM products WHERE title LIKE :searchquery OR `desc` LIKE :searchquery")
    public List<Product> getProductsBySearch(String searchquery); // % + searchquery + %

    @Query("SELECT * FROM products WHERE category_id=:categoryId")
    public List<Product> getProductsByCategory(int categoryId);

    @Query("SELECT * FROM products WHERE vendor_username=:vendorusername")
    public List<Product> getProductsByVendor(String vendorusername);
}
