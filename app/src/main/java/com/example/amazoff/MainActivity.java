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
        dbManager.resetDatabase();  // TODO: How do we init database and reset?

        // TODO: Move this to a load data function in DatabaseManager?
        // Convert image resources to Bitmaps
        Bitmap xbox = BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s);
        Bitmap playstation = BitmapFactory.decodeResource(getResources(), R.drawable.playstation_5);
        Bitmap gamingComp = BitmapFactory.decodeResource(getResources(), R.drawable.gaming_computer);

        // Create and store products to the database
        dbManager.storeProduct(new Product("XBOX 360 1x plus plus. The best gaming console!","A really good piece of junk. Like really, you should buy this. At this price it is a steal or a waste of money, you decide! Or don't, I don't care. No really. Is this more than 4 lines yet? I'm not sure.",2.5, 2,5.00, xbox));
        dbManager.storeProduct(new Product("playstation. Why do we need a single playstation?","junk",2, 3,10.00, playstation));
        dbManager.storeProduct(new Product("Time is money and I am wasting my money","stuff",3.5, 4,12.00, gamingComp));
        dbManager.storeProduct(new Product("WHY am I here rn. This is taking way longer","junk",2, 3,10.00, playstation));
        dbManager.storeProduct(new Product("I will never be a web developer or front end designer","stuff",3.5, 4,12.00, gamingComp));
        dbManager.storeProduct(new Product("This shit is hard. Das a fact!!!!!!","junk",2, 3,10.00, playstation));
        dbManager.storeProduct(new Product("I can't think of anymore cool titles so: purple Unicorns? ","stuff",3.5, 4,12.00, gamingComp));

        // Launch the Browse Activity
        Intent callBrowseViewActivity = new Intent(this, BrowseActivity.class);
        this.startActivity(callBrowseViewActivity);
    }
}  // End of class MainActivity