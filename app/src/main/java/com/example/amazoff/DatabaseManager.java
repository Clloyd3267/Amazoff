package com.example.amazoff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class DatabaseManager extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "amazoffDB";
    private static final int DATABASE_VERSION = 1;

    public static final class DatabaseContract
    {
        // Inner class that defines product table names
        public static class Product
        {
            public static final String TABLE_NAME = "products";
            public static final String COLUMN_NAME_ID = "product_id";
            public static final String COLUMN_NAME_NAME = "name";
            public static final String COLUMN_NAME_DESCRIPTION = "description";
            public static final String COLUMN_NAME_RATING = "rating";
            public static final String COLUMN_NAME_PRICE = "price";
            public static final String COLUMN_NAME_IMAGE = "image";
        }
    }

    private static final String SQL_CREATE_PRODUCTS = "CREATE TABLE " +
            DatabaseContract.Product.TABLE_NAME + " (" +
            DatabaseContract.Product.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
            DatabaseContract.Product.COLUMN_NAME_NAME + " TEXT," +
            DatabaseContract.Product.COLUMN_NAME_DESCRIPTION + " TEXT," +
            DatabaseContract.Product.COLUMN_NAME_RATING + " INT," +
            DatabaseContract.Product.COLUMN_NAME_PRICE + " REAL," +
            DatabaseContract.Product.COLUMN_NAME_IMAGE + " BLOB " + ")";

    private static final String SQL_DELETE_PRODUCTS = "DROP TABLE IF EXISTS " +
            DatabaseContract.Product.TABLE_NAME;


    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL(SQL_CREATE_PRODUCTS);
    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        // Remove tables
        db.execSQL(SQL_DELETE_PRODUCTS);

        // Re-create tables
        onCreate(db);
    }

    public void storeProduct(Product product)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Compress and get byte array of image
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        product.getImage().compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] imageByteArray = bos.toByteArray();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Product.COLUMN_NAME_NAME, product.getName());
        values.put(DatabaseContract.Product.COLUMN_NAME_DESCRIPTION, product.getDescription());
        values.put(DatabaseContract.Product.COLUMN_NAME_RATING, product.getRating());
        values.put(DatabaseContract.Product.COLUMN_NAME_PRICE, product.getPrice());
        values.put(DatabaseContract.Product.COLUMN_NAME_IMAGE, imageByteArray);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseContract.Product.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Product> getProducts()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlQuery = "SELECT * FROM " + DatabaseContract.Product.TABLE_NAME;

        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Product> products = new ArrayList<Product>( );
        while( cursor.moveToNext())
        {
            Product product = new Product();
            product.setName(cursor.getString(1));
            product.setDescription(cursor.getString(2));
            product.setRating(cursor.getInt(3));
            product.setPrice(cursor.getDouble(3));
            byte[] imageByteArray = cursor.getBlob(5);
            Bitmap image = BitmapFactory.decodeByteArray(imageByteArray,0, imageByteArray.length);
            product.setImage(image);

            products.add(product);
        }
        cursor.close();
        db.close();
        return products;
    }
}
