package com.example.amazoff;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        dataMan.delete();

        //create bitmap objects for product images
        Bitmap xbox = BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s);
        Bitmap playstation = BitmapFactory.decodeResource(getResources(), R.drawable.playstation_5);
        Bitmap gamingComp = BitmapFactory.decodeResource(getResources(), R.drawable.gaming_computer);

        //create Products
        products = new ArrayList<Product>();

        products.add(new Product("xbox","crap",2, 2,5.00, xbox));
        products.add(new Product("playstation","junk",2, 3,10.00, playstation));
        products.add(new Product("computer","stuff",2, 4,12.00, gamingComp));

        for(Product prod : products) {
            dataMan.storeProduct(prod);
        }

        products = dataMan.getProducts();

//        ImageView img = new ImageView(this);
//        img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s));
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
        RelativeLayout thisLayout = new RelativeLayout(this);  // create relative layout to hold image views in

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        //create grid layout for products
        GridLayout productImages = new GridLayout(this);  // store images in grid view
        ScrollView scrollView = new ScrollView(this);
        productImages.setRowCount(2);
        productImages.setColumnCount(10);    //TODO change number of columns for number of images

        // store view details buttons
        ArrayList<Button> productDetails = new ArrayList<>();

        for(Product prod : products) {
            Button viewDetails = new Button(this);
            ImageView img = new ImageView(this);  // create new image view to store product image
            LinearLayout info =  new LinearLayout(this);  // create new linear layout for product info
            info.setOrientation(LinearLayout.VERTICAL);

            // Get Product
            Bitmap bitmp = prod.getImage();  // get Bitmap Object of product image
            String productName = prod.getName();
            String productDescription = prod.getDescription();
            double productPrice = prod.getPrice();
            int productRating = prod.getRating();

            // Create Text Views for each product parameter
            TextView name = new TextView(this);
            TextView Description = new TextView(this);
            TextView Price = new TextView(this);
            TextView Rating = new TextView(this);

            // set text views to product parameters
            name.setText(productName);
            Description.setText(productDescription);
            Price.setText(String.valueOf(productPrice));
            Rating.setText(String.valueOf(productRating));


            viewDetails.setText("view details");

            // add text views to linear layout
            info.addView(name);
            info.addView(Description);
            info.addView(Price);
            info.addView(Rating);
            info.addView(viewDetails);

            img.setImageBitmap(bitmp);  // store bitmap of resource in image view

//            img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.xbox_one_s));

            productImages.addView(img, width / 2, height / 3 );
            productImages.addView(info, width / 2, height / 3 );
//            productImages.addView(img);
        }

        scrollView.addView(productImages);
        thisLayout.addView(scrollView);
        setContentView(thisLayout);

    }
}