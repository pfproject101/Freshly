package com.assignment.freshly.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="customers")
public class Customer {
    @PrimaryKey
    @NonNull
    private String email;
    private String name;
    private String password;
    private String image_path;
    private char gender;

    public Customer( @NonNull String email, String name, String password, String image_path, char gender) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.image_path = image_path;
        this.gender = gender;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail( @NonNull String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

}
