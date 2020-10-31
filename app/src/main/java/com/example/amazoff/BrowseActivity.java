/*=============================================================================*
* Filename    : BrowseActivity.java
* Author      : Kyle Bielby, Chris Lloyd, Marc Simone, Wayne Wells
* Due Date    : 2020/11/06
* Project     : EE-408 (CU) Final Project (Amazoff Shopping App)
* Class(s)    : BrowseActivity
* Description : Browse Activity class to view a list of products.
*=============================================================================*/

// Package Definition
package com.example.amazoff;

// Imports
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Browse Activity class to view a list of products.
 */
public class BrowseActivity extends AppCompatActivity 
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
        setContentView(R.layout.activity_browse); // TODO: Needed?

        // Add toolbar to activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.browse_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        
        // Open database controller
        dbManager = new DatabaseManager(this);

        // Update layout view
        updateView();
    }

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
     * Method to dynamically update view.
     */
    public void updateView ()
    {
        // Get width and height of display
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        // Get products from database
        ArrayList<Product> products = dbManager.getProducts();

        // Get scrollview from layout
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);

        // Create grid layout for products
        GridLayout productsGrid = new GridLayout(this);
        productsGrid.setPadding(15,15,15,15);
        productsGrid.setRowCount(products.size());
        productsGrid.setColumnCount(2);

        // Create view for each product
        for (Product product : products)
        {
            LinearLayout infoLayout =  new LinearLayout(this);
            LinearLayout ratingLayout =  new LinearLayout(this);
            ratingLayout.setOrientation(LinearLayout.HORIZONTAL);
            infoLayout.setOrientation(LinearLayout.VERTICAL);
            
            // Create and modify textviews for products
            // Title
            TextView nameTextView = new TextView(this);
            nameTextView.setTextSize(16);
            nameTextView.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
            nameTextView.setHorizontallyScrolling(false);
            nameTextView.setSingleLine(false);
            nameTextView.setEllipsize(TextUtils.TruncateAt.END);
            nameTextView.setPadding(0,0,50,10);
            nameTextView.setLines(2);

            // Rating bar
            RatingBar ratingRatingBar = new RatingBar(this, null, android.R.attr.ratingBarStyleSmall);
            ratingRatingBar.setIsIndicator(true);
            ratingRatingBar.setNumStars(5);
            ratingRatingBar.setStepSize(0.5f);
            ratingRatingBar.setPadding(0,7,0,0);

            // Number of reviews
            TextView numReviewsTextView = new TextView(this);
            numReviewsTextView.setPadding(20,0,0,0);
            numReviewsTextView.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
            numReviewsTextView.setTextSize(15);

            // Price
            TextView priceTextView = new TextView(this);
            DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00");
            priceTextView.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));

            // Image
            ImageView imageImageView = new ImageView(this);

            // Shipping
            TextView shippingTextView = new TextView(this);

            // Set data to widgets
            imageImageView.setImageBitmap(product.getImage());
            nameTextView.setText(product.getName());
            ratingRatingBar.setRating((float) product.getRating());
            numReviewsTextView.setText(Integer.toString(product.getNumReviews()));
            priceTextView.setText(decimalFormat.format(product.getPrice()));
            shippingTextView.setText("FREE Delivery within two days!");

            // Create button to go to product details view
            Button viewDetailsButton = new Button(this);
            viewDetailsButton.setText("View Details");  // TODO: Remove later and click cell instead

            // Set listener with current product ID
            ViewButtonHandler newHandler = new ViewButtonHandler(product.getID());
            viewDetailsButton.setOnClickListener(newHandler);

            // Add widgets to linear layout
            infoLayout.addView(nameTextView);
            ratingLayout.addView(ratingRatingBar);
            ratingLayout.addView(numReviewsTextView);
            infoLayout.addView(ratingLayout);
            infoLayout.addView(priceTextView);
            infoLayout.addView(shippingTextView);
            infoLayout.addView(viewDetailsButton);
            productsGrid.setBackgroundColor(Color.WHITE);
            productsGrid.setPadding(0,30,0,0);
            productsGrid.addView(imageImageView, width * 2 / 5, height / 4);
            productsGrid.addView(infoLayout, width * 3 / 5, height / 4);
        }

        // Add the products to the screen
        scrollView.addView(productsGrid);
    }

    /**
     * Custom handler to handle view details button click.
     */
    private class ViewButtonHandler implements View.OnClickListener
    {
        /**
         * The productID this listener is associated with.
         */
        private int activeProductID;

        /**
         * Constructor for this class.
         *
         * @param selectedProductID the productID this listener is associated with.
         */
        public ViewButtonHandler(int selectedProductID)
        {
            activeProductID = selectedProductID;
        }

        /**
         * Handler for button press.
         *
         * @param v The current view.
         */
        public void onClick( View v)
        {
            Intent newView = new Intent(BrowseActivity.this, DetailsActivity.class);
            newView.putExtra("Product ID", String.valueOf(activeProductID));
            BrowseActivity.this.startActivity(newView);
        }
    }  // End of class ViewButtonHandler
}  // End of class BrowseActivity