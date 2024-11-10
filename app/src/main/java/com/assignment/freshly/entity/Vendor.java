package com.assignment.freshly.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vendors")
public class Vendor {
    @PrimaryKey
    @NonNull
    private String username;
    private String address;
    private String password;
    private String phone;

    public Vendor( @NonNull String username, String address, String password, String phone) {
        this.username = username;
        this.address = address;
        this.password = password;
        this.phone = phone;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername( @NonNull String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
