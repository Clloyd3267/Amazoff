package com.example.amazoff;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BrowseView extends AppCompatActivity {
//    public BrowseView(Context context) {
//        super(context);
//        //load images
//        String XboxImageUri = "@drawable/xbox_one_s";
//        int imageResource = getResources().getIdentifier(XboxImageUri, null, this.getPackageName());
//
//        ImageView imageView = (ImageView)findViewById(R.id.imageView);
//
//
//    }

    private ArrayList<Product> products;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

//    public void updateView() {
//        //create grid layout for products
//        GridLayout productImages = new GridLayout(this);    //store images in grid view
//        productImages.setRowCount(2);
//        productImages.setColumnCount(10);    //TODO change number of columns for number of images
//
//        //get display characteristics
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//        for(Product prod : products) {
//            ImageView img = new ImageView(this);
//            Bitmap bitmp = prod.getImage();    //get Bitmap Object of product image
////            img.setImageBitmap(bitmp);
//            img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s));
//            productImages.addView(img);
//        }








//    }

//    private
//        int XboxImage = getResources().getIdentifier();
}

//    String uri = "@drawable/myresource";  // where myresource (without the extension) is the file
//
//    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//
//imageview= (ImageView)findViewById(R.id.imageView);
//        Drawable res = getResources().getDrawable(imageResource);
//        imageView.setImageDrawable(res);