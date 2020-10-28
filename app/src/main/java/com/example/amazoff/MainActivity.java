package com.example.amazoff;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private DatabaseManager dataMan;
    private ArrayList<Product> products;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataMan = new DatabaseManager(this);    //create new database

        //create bitmap objects for product images
        Bitmap xbox = BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s);
        Bitmap playstation = BitmapFactory.decodeResource(getResources(), R.drawable.playstation_5);
        Bitmap gamingComp = BitmapFactory.decodeResource(getResources(), R.drawable.gaming_computer);

        //create Products
        products = new ArrayList<Product>();

        products.add(new Product("xbox","crap",2,5.00, xbox));
        products.add(new Product("playstation","junk",2,10.00, playstation));
        products.add(new Product("computer","stuff",2,12.00, gamingComp));

        for(Product prod : products) {
            dataMan.storeProduct(prod);
        }

        dataMan.getProducts();

        ImageView img = new ImageView(this);
        img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s));
//        productImages.addView(img);

        //set image
//        relativeLayout = findViewById(R.id.image_view_holder);
//        relativeLayout.addView(img);

        updateView();

    }

    protected void onStart() {
        super.onStart( );
//        Toast.makeText( this, "OnStart", Toast.LENGTH_SHORT ).show( );
        updateView( );
    }

    //temporary
    public void updateView() {
//        relativeLayout.removeAllViewsInLayout();    //removes previous view
        RelativeLayout thisLayout = new RelativeLayout(this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        //TODO temporarly create bitmap objects
        Bitmap xbox = BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s);
        Bitmap playstation = BitmapFactory.decodeResource(getResources(), R.drawable.playstation_5);
        Bitmap gamingComp = BitmapFactory.decodeResource(getResources(), R.drawable.gaming_computer);

        //create grid layout for products
        GridLayout productImages = new GridLayout(this);    //store images in grid view
        ScrollView scrollView = new ScrollView(this);
        productImages.setRowCount(2);
        productImages.setColumnCount(10);    //TODO change number of columns for number of images



        ImageView xboxImage = new ImageView(this);
        ImageView playStationImage = new ImageView(this);
        ImageView computerImage = new ImageView(this);

        xboxImage.setImageBitmap(xbox);
        playStationImage.setImageBitmap(playstation);
        computerImage.setImageBitmap(gamingComp);

        productImages.addView(xboxImage, width , height / 3);
        productImages.addView(playStationImage, width , 2 * (height / 3));
        productImages.addView(computerImage, width , 3 * (height / 3));

//        for(Product prod : products) {
//            ImageView img = new ImageView(this);
//            Bitmap bitmp = prod.getImage();    //get Bitmap Object of product image
////            img.setImageBitmap(bitmp);
//            img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s));
//            productImages.addView(img);
//        }

        scrollView.addView(productImages);
        thisLayout.addView(scrollView);
        setContentView(thisLayout);

    }
}