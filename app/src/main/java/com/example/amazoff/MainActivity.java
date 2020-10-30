/*=============================================================================*
* Filename    : MainActivity.java
* Author      : Kyle Bielby, Chris Lloyd, Marc Simone, Wayne Wells
* Due Date    : 2020/11/06
* Project     : EE-408 (CU) Final Project (Amazoff Shopping App)
* Class(s)    : MainActivity
* Description : Main Activity class which acts as the entry point to this app.
*=============================================================================*/

// Package Definition
package com.example.amazoff;

// Imports
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Main Activity class which acts as the entry point to this app.
 */
public class MainActivity extends AppCompatActivity 
{
    /**
     * A class to access the local database. 
     */
    private DatabaseManager dbManager;

    /**
     * Initializer for activity class.
     * 
     * @param savedInstanceState Previous state data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: Should we even have a MainActivity view?
        // https://stackoverflow.com/questions/17346102/must-every-activity-have-a-layout

        // Open database controller
        dbManager = new DatabaseManager(this);
        // TODO: Delete previous database data here?

        // TODO: Move this to a load data function in DatabaseManager?
        // Convert image resources to Bitmaps
        Bitmap xbox = BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s);
        Bitmap playstation = BitmapFactory.decodeResource(getResources(), R.drawable.playstation_5);
        Bitmap gamingComp = BitmapFactory.decodeResource(getResources(), R.drawable.gaming_computer);

        // Create and store products to the database
        dbManager.storeProduct(new Product("xbox","crap",2, 2,5.00, xbox));
        dbManager.storeProduct(new Product("playstation","junk",2, 3,10.00, playstation));
        dbManager.storeProduct(new Product("computer","stuff",2, 4,12.00, gamingComp));

        // Launch the Browse Activity
        Intent callBrowseViewActivity = new Intent(this, BrowseActivity.class);
        this.startActivity(callBrowseViewActivity);
    }
}  // End of class MainActivity