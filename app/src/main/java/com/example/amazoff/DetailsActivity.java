package com.example.amazoff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private DatabaseManager dataMan;
    private String productTitleString;
    private String productDescriptionString;
    private String productPriceString;
    private String productRatingString;
    private String productImageString;
    private Product activeProduct;
//    private Bitmap productImageString;
    private Intent parentIntent;

    private Context thisContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_details);

        dataMan = new DatabaseManager(this);    //create new database

        parentIntent = getIntent();

        int activeProductID = Integer.parseInt(parentIntent.getStringExtra("Product ID")) ;

        activeProduct = dataMan.getProductByID(activeProductID);
        if(activeProduct == null){
//            throw new Exception() ;  // TODO add exception
        }

//        productTitleString = parentIntent.getStringExtra("Product Name");
//        productDescriptionString = parentIntent.getStringExtra("Product Description");
//        productPriceString = parentIntent.getStringExtra("Product Price");
//        productRatingString = parentIntent.getStringExtra("Product Rating");
//        productImageString = parentIntent.getStringExtra("Product Image");
//        productImageString = parentIntent.getExtras().getParcelable("Product Image");

        thisContext = this;

        updateView();
    }

    private void updateView() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ImageView mainImage = findViewById(R.id.DetailsImage);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        //resize image view
        mainImage.setMinimumWidth(width);
        mainImage.setMaxHeight(height / 2);

        // display product details
        TextView productTitle = findViewById(R.id.productTitle);
        TextView productDescription = findViewById(R.id.productDescription);
        TextView productRating = findViewById(R.id.productRating);
        TextView productPrice = findViewById(R.id.productPrice);

        productTitle.setText(activeProduct.getName());
        productDescription.setText(activeProduct.getDescription());
        productPrice.setText(String.valueOf(activeProduct.getPrice()));
        productRating.setText(String.valueOf(activeProduct.getRating()));

        mainImage.setImageBitmap(activeProduct.getImage());

        Button addItemButton = findViewById(R.id.add_to_cart);

        // add on click listener for add item to cart push button
        addItemButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Add to cart model
                    // TODO remove intent only go to cart button in every view should accomplish this
                    Intent goToCart = new Intent(thisContext, CartActivity.class);
                    thisContext.startActivity(goToCart);
                }
        });

    }


}