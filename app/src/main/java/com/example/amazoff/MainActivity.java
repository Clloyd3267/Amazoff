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
        Bitmap nintendo_switch = BitmapFactory.decodeResource(getResources(), R.drawable.nintendo_switch);
        Bitmap iPhone_12 = BitmapFactory.decodeResource(getResources(), R.drawable.iphone_12);
        Bitmap samsung_galaxy_s12 = BitmapFactory.decodeResource(getResources(), R.drawable.samsung_galaxy_s20);
        Bitmap samsung_galaxy_watch_3 = BitmapFactory.decodeResource(getResources(), R.drawable.samsung_galaxy_watch_3);
        Bitmap playstation = BitmapFactory.decodeResource(getResources(), R.drawable.playstation_5);
        Bitmap gaming_computer = BitmapFactory.decodeResource(getResources(), R.drawable.gaming_computer);
        Bitmap apple_airpods = BitmapFactory.decodeResource(getResources(), R.drawable.airpods);
        Bitmap airpod_pros = BitmapFactory.decodeResource(getResources(), R.drawable.airpod_pros);
        Bitmap bose_home_300 = BitmapFactory.decodeResource(getResources(), R.drawable.bose_home_300);
        Bitmap epiPen = BitmapFactory.decodeResource(getResources(), R.drawable.epi_pen);


        // Create and store products to the database
        dbManager.storeProduct(new Product("XBox One","The newest game system from Microsoft.",2.5, 147,199.99, xbox));
        dbManager.storeProduct(new Product("Playstation 4","Sony's newest game system.",3, 246,249.99, playstation));
        dbManager.storeProduct(new Product("Nintendo Switch", "This is a product by nintendo.", 4.5, 10, 299.99, nintendo_switch));
        dbManager.storeProduct(new Product("iPhone 12", "Hot garbage. Don't waste your money idiot.", 2, 20, 1299.99, iPhone_12));
        dbManager.storeProduct(new Product("Samsung Galaxy S20", "Brand new phone by Samsung.", 4.5, 3, 999.99, samsung_galaxy_s12));
        dbManager.storeProduct(new Product("Samsung Galaxy Watch 3", "Brand new watch by Samsung. Now with better health tracking.", 4.5, 8, 429.99, samsung_galaxy_watch_3));
        dbManager.storeProduct(new Product("Gaming PC", "Powerful PC, perfect for gaming, school, and work.", 4.5, 25, 1299.99, gaming_computer));
        dbManager.storeProduct(new Product("Apple Airpods", "Wireless version of Apple's horrible headphone design.", 2.5, 345, 149.99, apple_airpods));
        dbManager.storeProduct(new Product("Apple Airpod Pros", "Slightly more comfortlable wireless headphones by Apple.", 4, 134, 249.99, airpod_pros));
        dbManager.storeProduct(new Product("Bose Home 300", "Wireless speaker for your home.", 4, 13, 89.99, bose_home_300));

        dbManager.storeProduct(new Product("Epi Pen", "This will save your life but kill your wallet", 3.5, 10, 699.99, epiPen));


        // Launch the Browse Activity
        Intent callBrowseViewActivity = new Intent(this, BrowseActivity.class);
        this.startActivity(callBrowseViewActivity);
    }
}  // End of class MainActivity