package com.example.efraimsproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DataBase2 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "APP333.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "My_Shop";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_PRODUCT_PRICE = ("product_price");
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_BITMAP = "pic";
    private final Context context;
    private SQLiteDatabase db;
    private static final String[] allColumns = {COLUMN_ID, COLUMN_PRODUCT_NAME, COLUMN_BITMAP, COLUMN_DESCRIPTION,COLUMN_PRODUCT_PRICE};

    public DataBase2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PRODUCT_NAME + " TEXT, "
                + COLUMN_PRODUCT_PRICE + " INTEGER, "
                + COLUMN_BITMAP + " BLOB,"
                + COLUMN_DESCRIPTION + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    long addProduct(Products product){
        long id;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT_NAME, product.getName());
        cv.put(COLUMN_PRODUCT_PRICE, product.getPrice());
        cv.put(COLUMN_DESCRIPTION, product.getDescription());
        cv.put(COLUMN_BITMAP, BitmapHelper.bitmapToByteArray(product.getPic()));
        id = db.insert(TABLE_NAME, null, cv);
        db.close();
        return id;

    }

    public ArrayList<Products> getAllRecipes() {
        db = getReadableDatabase();
        ArrayList<Products> l = new ArrayList<Products>();
        Cursor cursor = db.query(
                TABLE_NAME,
                allColumns,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
                @SuppressLint("Range") byte[] bitArray = cursor.getBlob(cursor.getColumnIndex(COLUMN_BITMAP));
                @SuppressLint("Range")  String des = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE));
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitArray, 0, bitArray.length);
                Products c = new Products(id,des,bitmap,name,price);
                l.add(c);
            }
        }
        return l;
    }
}
