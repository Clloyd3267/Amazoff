/*=============================================================================*
* Filename    : CartActivity.java
* Author      : Kyle Bielby, Chris Lloyd, Marc Simone, Wayne Wells
* Due Date    : 2020/11/06
* Project     : EE-408 (CU) Final Project (Amazoff Shopping App)
* Class(s)    : CartActivity
* Description : Cart Activity class to show a the current cart.
*=============================================================================*/

// Package Definition
package com.example.amazoff;

// Imports
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Set;

/**
 * Cart Activity class to show a the current cart.
 */
public class CartActivity extends AppCompatActivity
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
        setContentView(R.layout.activity_cart);

        // Add toolbar to activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        // Open database controller
        dbManager = new DatabaseManager(this);

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

        // Get productIDs in cart
        Hashtable<Integer, Integer> cartProducts = dbManager.getCartItems();

        // Get set of productIDs in cart
        Set<Integer> productIDs = cartProducts.keySet();

        // Get scrollview from layout
        ScrollView scrollView = (ScrollView) findViewById(R.id.cart_scroll_view);

        // Remove old child views if needed
        scrollView.removeAllViewsInLayout();

        // Create grid layout for cart items
        GridLayout productsGrid = new GridLayout(this);
        productsGrid.setPadding(15,15,15,15);
        productsGrid.setRowCount(productIDs.size());
        productsGrid.setColumnCount(2);
        productsGrid.setBackgroundColor(Color.WHITE);

        updateSubtotal();

        // Get button to go to Checkout
        Button checkoutButton = (Button) findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(new CheckoutButtonHandler());

        for (Integer productID : productIDs)
        {
            // Set local data
            Product product = dbManager.getProductByID(productID);
            int productQuantity = cartProducts.get(productID);

            LinearLayout infoLayout =  new LinearLayout(this);
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

            // Price
            TextView priceTextView = new TextView(this);
            DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00");
            priceTextView.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));

            // Image
            ImageView imageImageView = new ImageView(this);

            // Shipping
            TextView shippingTextView = new TextView(this);

            // Quantity
            LinearLayout quantityLayout = new LinearLayout(this);
            quantityLayout.setOrientation(LinearLayout.HORIZONTAL);
            TextView quantityTextView = new TextView(this);
            quantityTextView.setPadding(10,0,10,0);
            Button incrementQuantityButton = new Button(this);
            final float scale = this.getResources().getDisplayMetrics().density;
            int pixels = (int) (40 * scale + 0.5f);
            incrementQuantityButton.setLayoutParams(new ViewGroup.LayoutParams(pixels, pixels));
            Button decrementQuantityButton = new Button(this);
            decrementQuantityButton.setLayoutParams(new ViewGroup.LayoutParams(pixels, pixels));
            addMinusButtonOnClickListener addMinusHandler = new addMinusButtonOnClickListener(quantityTextView, productID);
            incrementQuantityButton.setOnClickListener(addMinusHandler);
            decrementQuantityButton.setOnClickListener(addMinusHandler);
            Button deleteButton = new Button(this);
            deleteButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, pixels));
            deleteButton.setOnClickListener(new DeleteButtonOnClickListener(productID));

            // Set data to widgets
            imageImageView.setImageBitmap(product.getImage());
            nameTextView.setText(product.getName());
            priceTextView.setText(decimalFormat.format(product.getPrice()));
            shippingTextView.setText("FREE Delivery within two days!");
            quantityTextView.setText(Integer.toString(productQuantity));
            incrementQuantityButton.setText("+");
            decrementQuantityButton.setText("-");
            deleteButton.setText("Delete");

            // Create button to go to product details view
            Button viewDetailsButton = new Button(this);
            viewDetailsButton.setText("View Details");  // TODO: Remove later and click cell instead

            // Set listener with current product ID
            ViewButtonHandler newHandler = new ViewButtonHandler(productID);
            viewDetailsButton.setOnClickListener(newHandler);

            // Add widgets to linear layout
            infoLayout.addView(nameTextView);
            infoLayout.addView(priceTextView);
            infoLayout.addView(shippingTextView);
            quantityLayout.addView(decrementQuantityButton);
            quantityLayout.addView(quantityTextView);
            quantityLayout.addView(incrementQuantityButton);
            quantityLayout.addView(deleteButton);
            infoLayout.addView(quantityLayout);
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
     * A method to update the total price/quantity views.
     */
    private void updateSubtotal()
    {
        // Get productIDs in cart
        Hashtable<Integer, Integer> cartProducts = dbManager.getCartItems();

        // Get set of productIDs in cart
        Set<Integer> productIDs = cartProducts.keySet();

        // Calculate total quantity and total price
        double subtotal = 0;
        int totalQuantity = 0;
        for (Integer productID : productIDs)
        {
            Product product = dbManager.getProductByID(productID);
            int productQuantity = cartProducts.get(productID);

            subtotal += (product.getPrice() * productQuantity);
            totalQuantity += productQuantity;
        }

        // Set the total quantity
        TextView totalQuantityTextView = (TextView) findViewById(R.id.subtotal_text);
        totalQuantityTextView.setText("Subtotal (" + Integer.toString(totalQuantity) + " items):");

        // Set the total price
        TextView subtotalPriceTextView = (TextView) findViewById(R.id.subtotal_price);
        DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00");
        subtotalPriceTextView.setText(decimalFormat.format(subtotal));

        // Set the taxes and shipping
        TextView taxesTextView = (TextView) findViewById(R.id.tax_shipping_price);
        double taxes = (subtotal * 0.04);
        taxesTextView.setText(decimalFormat.format(taxes));

        // Set the order total
        TextView totalPriceTextView = (TextView) findViewById(R.id.total_price);
        double orderTotal = (subtotal + taxes);
        totalPriceTextView.setText(decimalFormat.format(orderTotal));
    }

    /**
     * Custom handler to handle increment and decrement quantity button click.
     */
    private class addMinusButtonOnClickListener implements View.OnClickListener
    {
        /**
         * The textview storing the quantity value.
         */
        private TextView quantityTextView;

        /**
         * The current ProductID.
         */
        private int productID;

        /**
         * Constructor for this class.
         *
         * @param quantityTextView The textview storing the quantity value.
         * @param productID the productID this listener is associated with.
         */
        addMinusButtonOnClickListener(TextView quantityTextView, int productID)
        {
            this.quantityTextView = quantityTextView;
            this.productID = productID;
        }

        /**
         * Handler for button press.
         *
         * @param v The current view.
         */
        @Override
        public void onClick(View v)
        {
            int quantity = Integer.parseInt(quantityTextView.getText().toString());
            Button button = (Button) v;

            if (button.getText() == "+")
            {
                quantity++;
                dbManager.addToCart(productID);
            }
            else
            {
                if (quantity > 1)
                {
                    quantity--;
                    dbManager.removeFromCart(productID);
                }
            }

            updateSubtotal();

            quantityTextView.setText(Integer.toString(quantity));
        }
    }  // End of class addMinusButtonOnClickListener

    private class DeleteButtonOnClickListener implements View.OnClickListener
    {
        /**
         * The current ProductID.
         */
        private int productID;

        /**
         * Constructor for this class.
         *
         * @param productID the productID this listener is associated with.
         */
        DeleteButtonOnClickListener(int productID)
        {
            this.productID = productID;
        }

        /**
         * Handler for button press.
         *
         * @param v The current view.
         */
        @Override
        public void onClick(View v)
        {
            // delete product from grid view of selected ID
            while (dbManager.removeFromCart(productID) != -1) {}

            // Update view to remove product
            updateView();
        }
    }  // End of class DeleteButtonOnClickListener

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
            Intent newView = new Intent(CartActivity.this, DetailsActivity.class);
            newView.putExtra("Product ID", String.valueOf(activeProductID));
            CartActivity.this.startActivity(newView);
        }
    }  // End of class ViewButtonHandler

    /**
     * Custom handler to handle checkout button click.
     */
    private class CheckoutButtonHandler implements View.OnClickListener
    {
        /**
         * Handler for button press.
         *
         * @param v The current view.
         */
        public void onClick(View v)
        {
            if (!dbManager.getCartItems().isEmpty())
            {
                Intent checkoutIntent = new Intent(CartActivity.this, CheckoutActivity.class);
                CartActivity.this.startActivity(checkoutIntent);
            }
        }
    }  // End of class CheckoutButtonHandler

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
}  // End of class CartActivity
