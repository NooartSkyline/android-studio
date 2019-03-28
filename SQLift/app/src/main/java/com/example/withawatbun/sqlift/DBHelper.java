package com.example.withawatbun.sqlift;

import android.content.Context;
//import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import com.example.withawatbun.sqlift.Model.Friend;
//
//import java.util.ArrayList;
//import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.example.withawatbun.sqlift.Constants.CONTENT;
import static com.example.withawatbun.sqlift.Constants.TABLE_NAME;
import static com.example.withawatbun.sqlift.Constants.TIME;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "my_note.db";
    private static final int DATABASE_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TIME + " INTEGER, " + CONTENT + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}