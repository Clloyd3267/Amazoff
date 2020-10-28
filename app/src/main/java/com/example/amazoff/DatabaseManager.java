/*=============================================================================*
* Filename    : DatabaseManager.java
* Author      : Kyle Bielby, Chris Lloyd, Marc Simone, Wayne Wells
* Due Date    : 2020/11/06
* Project     : EE-408 (CU) Final Project (Amazoff Shopping App)
* Class(s)    : DatabaseManager
* Description : Model class to manage and control the database for this 
*               project.
*=============================================================================*/

// Package Definition
package com.example.amazoff;

// Imports
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Model class to manage and control the database for this project.
 * 
 * Manages the database and provides user functionality to access it.
 */
public class DatabaseManager extends SQLiteOpenHelper
{
    /**
     * The name of the database to be created.
     */
    private static final String DATABASE_NAME = "amazoffDB";

    /**
     * The version number of the database to use.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * A contract class describing the database tables and table columns.
     */
    private static final class DatabaseContract
    {
        /**
         * A class to describe the products table.
         */
        private static class Product
        {
            /**
             * The name of the table.
             */
            private static final String TABLE_NAME = "products";

            /**
             * The name of the product unique identifier column.
             */
            private static final String COLUMN_NAME_ID = "product_id";

            /**
             * The name of the product name column.
             */
            private static final String COLUMN_NAME_NAME = "name";
            
            /**
             * The name of the product description column.
             */
            private static final String COLUMN_NAME_DESCRIPTION = "description";
            
            /**
             * The name of the product rating column.
             */
            private static final String COLUMN_NAME_RATING = "rating";

            /**
             * The name of the product number of reviews column.
             */
            private static final String COLUMN_NAME_NUM_REVIEWS = "number_reviews";
            
            /**
             * The name of the product price column.
             */
            private static final String COLUMN_NAME_PRICE = "price";
            
            /**
             * The name of the product image column.
             */
            private static final String COLUMN_NAME_IMAGE = "image";
        }  // End of class Product
    }  // End of class DatabaseContract

    /**
     * SQL Statement to create the products table.
     */
    private static final String SQL_CREATE_PRODUCTS = "CREATE TABLE " +
            DatabaseContract.Product.TABLE_NAME + " (" +
            DatabaseContract.Product.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
            DatabaseContract.Product.COLUMN_NAME_NAME + " TEXT," +
            DatabaseContract.Product.COLUMN_NAME_DESCRIPTION + " TEXT," +
            DatabaseContract.Product.COLUMN_NAME_RATING + " INT," +
            DatabaseContract.Product.COLUMN_NAME_NUM_REVIEWS + " INT," +
            DatabaseContract.Product.COLUMN_NAME_PRICE + " REAL," +
            DatabaseContract.Product.COLUMN_NAME_IMAGE + " BLOB " + ")";

    /**
     * SQL Statement to delete the products table.
     */
    private static final String SQL_DELETE_PRODUCTS = "DROP TABLE IF EXISTS " +
            DatabaseContract.Product.TABLE_NAME;


    /**
     * Default constructor to initialize class and database with. 
     * 
     * @param context The Activity context to create the database with.
     */
    public DatabaseManager(Context context)
    {
        // Create the database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Override method run after database has been created.
     * 
     * @param db The current SQLite database.
     */
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_PRODUCTS);  // Create products table
    }

    /**
     * Override method run when database upgrade is invoked.
     * 
     * Deletes all tables and data and recreates database.
     * 
     * @param db The current SQLite database.
     * @param oldVersion The old version of the database. (unused)
     * @param newVersion The current SQLite database. (unused)
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_PRODUCTS);  // Remove products table
        onCreate(db);                     // Re-create tables
    }

    /* ====================================================================== */
    /* ==================== Data Manipulator Functions ====================== */
    /* ====================================================================== */

    /**
     * A function to store a @{link Product} to the database.
     * 
     * @param product a Product to store.
     * @return (long): The primary key of the product or -1 if an error occurred.
     */
    public long storeProduct(Product product)
    {
        // Get the current database with write privileges
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
        values.put(DatabaseContract.Product.COLUMN_NAME_NUM_REVIEWS, product.getNumReviews());
        values.put(DatabaseContract.Product.COLUMN_NAME_PRICE, product.getPrice());
        values.put(DatabaseContract.Product.COLUMN_NAME_IMAGE, imageByteArray);

        db.close();

        // Insert the new row, returning the primary key value of the new row
        return db.insert(DatabaseContract.Product.TABLE_NAME, null, values);
    }

    /**
     * A function to return all of the current products from the database.
     * 
     * @return (ArrayList<Product>): The returned products.
     */
    public ArrayList<Product> getProducts()
    {
        // Get the current database with read privileges
        SQLiteDatabase db = this.getReadableDatabase();

        // Create query to get all products
        String sqlQuery = "SELECT * FROM " + DatabaseContract.Product.TABLE_NAME;

        // Run query and create cursor to access data
        Cursor cursor = db.rawQuery(sqlQuery, null);

        // Get product data for each product and store in a new Product object
        ArrayList<Product> products = new ArrayList<Product>( );
        while( cursor.moveToNext())
        {
            Product product = new Product();
            product.setName(cursor.getString(1));
            product.setDescription(cursor.getString(2));
            product.setRating(cursor.getInt(3));
            product.setNumReviews(cursor.getInt(4));
            product.setPrice(cursor.getDouble(5));
            
            // Decompress and get bitmap image from byte stream
            byte[] imageByteArray = cursor.getBlob(6);
            Bitmap image = BitmapFactory.decodeByteArray(imageByteArray, 
                                                         0, 
                                                         imageByteArray.length);
            product.setImage(image);

            products.add(product);
        }
        cursor.close();
        db.close();
        return products;
    }
}  // End of class DatabaseManager
