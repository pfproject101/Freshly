package com.assignment.freshly.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.assignment.freshly.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    public long insert(Category category);

    @Query("SELECT * FROM categories")
    public List<Category> getCategories();

    @Query("SELECT * FROM categories WHERE category_name=:categoryName")
    public List<Category> getCategoriesByName(String categoryName);
}
