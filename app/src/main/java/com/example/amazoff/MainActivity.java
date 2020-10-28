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

public class MainActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        

        ImageView img = new ImageView(this);
        img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s));
//        productImages.addView(img);

        //set image
        relativeLayout = findViewById(R.id.image_view_holder);
        relativeLayout.addView(img);

        updateView();

    }

    protected void onStart() {
        super.onStart( );
//        Toast.makeText( this, "OnStart", Toast.LENGTH_SHORT ).show( );
        updateView( );
    }

    //temporary
    public void updateView() {
        relativeLayout.removeAllViewsInLayout();    //removes previous view

        //TODO test product remove later
//        Product newProd = new Product("xbox", "junk", 1, 5.00, stuff);

        //TODO temporarly create bitmap objects
        Bitmap xbox = BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s);
        Bitmap playstation = BitmapFactory.decodeResource(getResources(), R.drawable.playstation_5);
        Bitmap gamingComp = BitmapFactory.decodeResource(getResources(), R.drawable.gaming_computer);

        //create grid layout for products
        GridLayout productImages = new GridLayout(this);    //store images in grid view
        productImages.setRowCount(2);
        productImages.setColumnCount(10);    //TODO change number of columns for number of images



        ImageView xboxImage = new ImageView(this);
        ImageView playStationImage = new ImageView(this);
        ImageView computerImage = new ImageView(this);

        xboxImage.setImageBitmap(xbox);
        playStationImage.setImageBitmap(playstation);
        computerImage.setImageBitmap(gamingComp);

        productImages.addView(xboxImage);
        productImages.addView(playStationImage);
        productImages.addView(computerImage);

//        for(Product prod : products) {
//            ImageView img = new ImageView(this);
//            Bitmap bitmp = prod.getImage();    //get Bitmap Object of product image
////            img.setImageBitmap(bitmp);
//            img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s));
//            productImages.addView(img);
//        }

        relativeLayout.addView(productImages);
    }
}