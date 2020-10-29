package com.example.amazoff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dataMan;
    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        this.deleteDatabase(dataMan.getDatabaseName());
        dataMan = new DatabaseManager(this);    //create new database

        //create bitmap objects for product images
        Bitmap xbox = BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s);
        Bitmap playstation = BitmapFactory.decodeResource(getResources(), R.drawable.playstation_5);
        Bitmap gamingComp = BitmapFactory.decodeResource(getResources(), R.drawable.gaming_computer);

        //create Products
        products = new ArrayList<Product>();

        products.add(new Product("xbox","crap",2, 2,5.00, xbox));//, R.drawable.xbox_one_s));
        products.add(new Product("playstation","junk",2, 3,10.00, playstation));//, (int)R.drawable.playstation_5));
        products.add(new Product("computer","stuff",2, 4,12.00, gamingComp));//, (int)R.drawable.gaming_computer));

        for(Product prod : products) {
            dataMan.storeProduct(prod);
        }

//        dataMan.storeProduct(new Product("xbox","crap",2, 2,5.00, xbox));
//        dataMan.storeProduct(new Product("playstation","junk",2, 3,10.00, playstation));//, (int)R.drawable.playstation_5));
//        dataMan.storeProduct(new Product("computer","stuff",2, 4,12.00, gamingComp));

        Intent callBrowseView = new Intent(this, BrowseActivity.class);
        this.startActivity(callBrowseView);

    }



}