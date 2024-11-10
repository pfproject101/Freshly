package com.assignment.freshly.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.freshly.entity.Customer;

@Dao
public interface CustomerDao {
    @Insert
    public long insert(Customer customer);

    @Update
    public Void update(Customer customer);

    @Query("SELECT * FROM customers WHERE email=:email AND password=:password")
    public Customer getCustomer(String email, String password);
}
