/*=============================================================================*
* Filename    : DetailsActivity.java
* Author      : Kyle Bielby, Chris Lloyd, Marc Simone, Wayne Wells
* Due Date    : 2020/11/06
* Project     : EE-408 (CU) Final Project (Amazoff Shopping App)
* Class(s)    : DetailsActivity
* Description : Details Activity class to show a detailed view of a product.
*=============================================================================*/

// Package Definition
package com.example.amazoff;

// Imports
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.text.DecimalFormat;

/**
 * Details Activity class to show a detailed view of a product.
 */
public class DetailsActivity extends AppCompatActivity 
{
    /**
     * A class to access the local database. 
     */
    private DatabaseManager dbManager;

    /**
     * The quantity of products selected.
     */
    private int productQuantity;

    /**
     * The textview for quantity.
     */
    private TextView productQuantityTextView;

    /**
     * The current product.
     */
    private Product activeProduct;

    /**
     * Initializer for activity class.
     * 
     * @param savedInstanceState Previous state data.
     */    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        
        // Add toolbar to activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        
        // Open database controller
        dbManager = new DatabaseManager(this);

        // Get bundled ProductID
        Intent parentIntent = getIntent();
        int activeProductID = Integer.parseInt(parentIntent.getStringExtra("Product ID"));

        // Get product data from ID
        activeProduct = dbManager.getProductByID(activeProductID);

        // Update layout view
        updateView();
    }

    /**
     * Method to dynamically update view.
     */
    public void updateView()
    {
        // Get width and height of display
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        
        // Get details widgets from layout 
        ImageView productImage = findViewById(R.id.product_image);
        TextView productTitle = findViewById(R.id.product_title);
        TextView productDescription = findViewById(R.id.product_description);    
        RatingBar productRating = findViewById(R.id.product_rating);
        TextView productNumReviews = findViewById(R.id.product_num_reviews);
        TextView productPrice = findViewById(R.id.product_price);
        productQuantityTextView = findViewById(R.id.product_quantity);

        // Setup quantity
        productQuantity = 1;
        productQuantityTextView.setText(Integer.toString(productQuantity));
        Button incrementQuantityButton = findViewById(R.id.quantity_plus);
        incrementQuantityButton.setOnClickListener(new addButtonOnClickListener());
        Button decrementQuantityButton = findViewById(R.id.quantity_minus);
        decrementQuantityButton.setOnClickListener(new subtractButtonOnClickListener());

        // Resize image view
        productImage.setMinimumWidth(width);
        productImage.setMaxHeight(height / 2);

        // Display product details
        productTitle.setText(activeProduct.getName());
        productImage.setImageBitmap(activeProduct.getImage());
        productDescription.setText(activeProduct.getDescription());
        productRating.setRating((float) activeProduct.getRating());
        productNumReviews.setText(Integer.toString(activeProduct.getNumReviews()));
        DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00");
        productPrice.setText(String.valueOf(decimalFormat.format(activeProduct.getPrice())));

        // Add on click listener for add item to cart push button
        Button addItemButton = findViewById(R.id.add_to_cart);
        addItemButton.setOnClickListener(new ViewButtonHandler());
    }

    /**
     * Custom handler to handle increment quantity button click.
     */
    private class addButtonOnClickListener implements View.OnClickListener
    {
        /**
         * Handler for button press.
         *
         * @param v The current view.
         */        
        @Override
        public void onClick(View v)
        {
            // Increase purchased quantity
            productQuantityTextView.setText(Integer.toString(++productQuantity));
        }
    }  // End of class addButtonOnClickListener

    /**
     * Custom handler to handle increment quantity button click.
     */
    private class subtractButtonOnClickListener implements View.OnClickListener 
    {
        /**
         * Handler for button press.
         *
         * @param v The current view.
         */   
        @Override
        public void onClick(View v) 
        {
            // Decrease purchased quantity
            if (productQuantity > 1)
            {
                productQuantityTextView.setText(Integer.toString(--productQuantity));
            }
        }
    }  // End of class subtractButtonOnClickListener

    /**
     * Custom handler to handle add to cart button click.
     */
    private class ViewButtonHandler implements View.OnClickListener
    {
        /**
         * Handler for button press.
         *
         * @param v The current view.
         */
        public void onClick( View v)
        {
            // Add to cart model
            for (int i = 0; i < productQuantity; i++)
            {
                dbManager.addToCart(activeProduct.getID());
            }
        }
    }  // End of class ViewButtonHandler

    /**
     * Method to add options menu.
     * 
     * @param menu Menu object to add.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    /**
     * Method to go to cart when toolbar button is pressed.
     * 
     * @param item The selected menu item.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.go_to_cart:
                Intent goToCart = new Intent(this, CartActivity.class);
                this.startActivity(goToCart);
                break;
            default:
        }
        return true;
    }     
}  // End of class DetailsActivity