package com.assignment.freshly.database;

import android.content.Context;

import androidx.room.Room;

public class FreshlyDatabaseClient {
    public FreshlyDatabase db;
    Context c;
    public static FreshlyDatabaseClient instance;

    private FreshlyDatabaseClient(Context c){
        this.c = c;
        db = Room.databaseBuilder(this.c, FreshlyDatabase.class, "FreshlyDatabase").build();
    }

    public static synchronized FreshlyDatabaseClient getInstance(Context c){
        if (instance==null){
            instance = new FreshlyDatabaseClient(c);
        }
        return instance;
    }

}
