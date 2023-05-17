package com.example.efraimsproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBasePM extends SQLiteOpenHelper {

    private static final String DATABASE_NAME1 = "APP777.db";
    private static final int DATABASE_VERSION1 = 1;

    private static final String TABLE_NAME1 = "userspm";
    private static final String COLUMN_ID1 = "idpm";
    private static final String COLUMN_NAME1 = "namepm";
    private static final String COLUMN_PASS1 = "passwordpm";

    private final Context context;


    public DataBasePM(Context context) {
        super(context, DATABASE_NAME1, null, DATABASE_VERSION1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUserTablePm =  "CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 +
                " (" + COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME1 + " TEXT, " +
                COLUMN_PASS1 + " TEXT);";
        db.execSQL(queryUserTablePm);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }
    public void insertUser1(String name, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(COLUMN_NAME1, name);
        cv1.put(COLUMN_PASS1, password);
        long result = db.insert(TABLE_NAME1, null, cv1);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    public int checkForUser1(String name, String userPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT idpm FROM " + TABLE_NAME1 + " WHERE " + COLUMN_NAME1+ " = '" + name + "' AND " + COLUMN_PASS1 + " = '" + userPassword + "'";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor.getCount();
    }
    public int doesExist1(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT idpm FROM " + TABLE_NAME1 +" WHERE " + COLUMN_NAME1 + " = '"+name+"'";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor.getCount();
    }
    @SuppressLint("Range")
    public String getId1(String name, String userPassword)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT idpm FROM " + TABLE_NAME1 +" WHERE " + COLUMN_NAME1 + " = '"+name+"' AND "+ COLUMN_PASS1 + " = '"+userPassword+"'";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }


        cursor.moveToFirst();
        int a = cursor.getInt(cursor.getColumnIndex(COLUMN_ID1));

        return String.valueOf(a);
    }
    Cursor readAllData1(){
        String query = "SELECT * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
}
