package com.assignment.freshly.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "products",
        foreignKeys = @ForeignKey(
        entity = Category.class,
        parentColumns = "id",
        childColumns = "category_id",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
),
        indices = {@Index(value = "category_id")}
)
public class Product {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String desc;
    private float price;
    private int category_id;
    private String vendor_username;
    private String image_path;

    public Product(String title, String desc, float price, int category_id, String vendor_username, String image_path) {
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.category_id = category_id;
        this.vendor_username =vendor_username;
        this.image_path = image_path;
    }

    public String getTitle() {
        return title;
    }

    public String getVendor_username() {
        return vendor_username;
    }

    public void setVendor_username(String vendor_username) {
        this.vendor_username = vendor_username;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
