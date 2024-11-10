package com.assignment.freshly.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.freshly.entity.Vendor;

import java.util.List;

@Dao
public interface VendorDao {
    @Insert
    public long insert(Vendor vendor);

    @Update
    public Void update(Vendor vendor);

    @Query("SELECT * FROM vendors WHERE username=:username AND password=:password")
    public Vendor getVendor(String username, String password);

    @Query("SELECT* FROM vendors")
    public List<Vendor> getVendors();
}
