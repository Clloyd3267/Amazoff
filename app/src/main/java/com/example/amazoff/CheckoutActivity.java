/*=============================================================================*
* Filename    : CheckoutActivity.java
* Author      : Kyle Bielby, Chris Lloyd, Marc Simone, Wayne Wells
* Due Date    : 2020/11/06
* Project     : EE-408 (CU) Final Project (Amazoff Shopping App)
* Class(s)    : CheckoutActivity
* Description : Checkout Activity class to show a checkout form.
*=============================================================================*/

// Package Definition
package com.example.amazoff;

// Imports
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Hashtable;

/**
 * Checkout Activity class to show a checkout form.
 */
public class CheckoutActivity extends AppCompatActivity
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
        setContentView(R.layout.activity_checkout);

        // Add toolbar to activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.checkout_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        // Open database controller
        dbManager = new DatabaseManager(this);

        // Add place order button handler
        Button placeOrderButton = findViewById(R.id.place_order_button);
        placeOrderButton.setOnClickListener(new CheckoutButtonHandler());
    }

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
            // TODO: Add error checking and store order to database

            // Get order data widgets
            EditText firstNameEditText = (EditText) findViewById(R.id.first_name_text);
            EditText lastNameEditText = (EditText) findViewById(R.id.last_name_text);
            EditText phoneNumberEditText = (EditText) findViewById(R.id.phone_number_text);
            EditText emailEditText = (EditText) findViewById(R.id.email_address_text);
            EditText addressEditText = (EditText) findViewById(R.id.address_text);
            EditText cityEditText = (EditText) findViewById(R.id.city_text);
            EditText stateEditText = (EditText) findViewById(R.id.state_text);
            EditText zipCodeEditText = (EditText) findViewById(R.id.zip_code_text);
            EditText countryEditText = (EditText) findViewById(R.id.country_text);
            EditText cardNumberEditText = (EditText) findViewById(R.id.card_number_text);
            EditText expirationDateEditText = (EditText) findViewById(R.id.expiration_date_text);
            EditText securityCodeEditText = (EditText) findViewById(R.id.security_code_text);

            // Get order data
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String phoneNumber = phoneNumberEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String address = addressEditText.getText().toString();
            String city = cityEditText.getText().toString();
            String state = stateEditText.getText().toString();
            int zipCode = Integer.parseInt(zipCodeEditText.getText().toString());
            String country = countryEditText.getText().toString();
            String cardNumber = cardNumberEditText.getText().toString();
            String expirationDate = expirationDateEditText.getText().toString();
            int securityCode = Integer.parseInt(securityCodeEditText.getText().toString());

            // Get cart items
            Hashtable<Integer, Integer> cartItems = dbManager.getCartItems();

            // Create new order
            Order order = new Order(firstName,
                                    lastName,
                                    phoneNumber,
                                    email,
                                    address,
                                    city,
                                    state,
                                    zipCode,
                                    country,
                                    cardNumber,
                                    expirationDate,
                                    securityCode,
                                    cartItems);

            // Submit order
            dbManager.processOrder(order);

            // TODO: Add confirmation toast
            // Go to Browse page
            Intent browseIntent = new Intent(CheckoutActivity.this, BrowseActivity.class);
            CheckoutActivity.this.startActivity(browseIntent);
        }
    }  // End of class CheckoutButtonHandler


    // private class zipCodeChanged implements TextWatcher { TODO: @Kyle Check this over

    //     @Override
    //     public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    //     }

    //     @Override
    //     public void onTextChanged(CharSequence s, int start, int before, int count) {

    //     }

    //     @Override
    //     public void afterTextChanged(Editable s) {

    //     }
    // }
}  // End of class CheckoutActivity