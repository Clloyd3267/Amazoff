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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.DecimalFormat;
import java.util.Hashtable;

/**
 * Checkout Activity class to show a checkout form.
 */
public class CheckoutActivity extends AppCompatActivity
{

    /**
     * Order total from Cart activity.
     */
    private double orderTotal;

    /**
     * A class to access the local database.
     */
    private DatabaseManager dbManager;

    /**
     * A reference to the first name EditText widget.
     */
    private EditText firstNameEditText;

    /**
     * A reference to the last name EditText widget.
     */
    private EditText lastNameEditText;

    /**
     * A reference to the phone number EditText widget.
     */
    private EditText phoneNumberEditText;

    /**
     * A reference to the email EditText widget.
     */
    private EditText emailEditText;

    /**
     * A reference to the address EditText widget.
     */
    private EditText addressEditText;

    /**
     * A reference to the city EditText widget.
     */
    private EditText cityEditText;

    /**
     * A reference to the state EditText widget.
     */
    private EditText stateEditText;

    /**
     * A reference to the zip code EditText widget.
     */
    private EditText zipCodeEditText;

    /**
     * A reference to the country EditText widget.
     */
    private EditText countryEditText;

    /**
     * A reference to the card number EditText widget.
     */
    private EditText cardNumberEditText;

    /**
     * A reference to the expiration date EditText widget.
     */
    private EditText expirationDateEditText;

    /**
     * A reference to the security code EditText widget.
     */
    private EditText securityCodeEditText;

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

        // Get passed order total
        orderTotal = Double.parseDouble(getIntent().getStringExtra("Order Total"));

        // Get order data widgets
        firstNameEditText = (EditText) findViewById(R.id.first_name_text);
        lastNameEditText = (EditText) findViewById(R.id.last_name_text);
        phoneNumberEditText = (EditText) findViewById(R.id.phone_number_text);
        emailEditText = (EditText) findViewById(R.id.email_address_text);
        addressEditText = (EditText) findViewById(R.id.address_text);
        cityEditText = (EditText) findViewById(R.id.city_text);
        stateEditText = (EditText) findViewById(R.id.state_text);
        zipCodeEditText = (EditText) findViewById(R.id.zip_code_text);
        countryEditText = (EditText) findViewById(R.id.country_text);
        cardNumberEditText = (EditText) findViewById(R.id.card_number_text);
        expirationDateEditText = (EditText) findViewById(R.id.expiration_date_text);
        securityCodeEditText = (EditText) findViewById(R.id.security_code_text);
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
            // Ensure all fields are not empty
            boolean emptyInput = firstNameEditText.getText().toString().isEmpty() |
                    lastNameEditText.getText().toString().isEmpty() |
                    phoneNumberEditText.getText().toString().isEmpty() |
                    emailEditText.getText().toString().isEmpty() |
                    addressEditText.getText().toString().isEmpty() |
                    cityEditText.getText().toString().isEmpty() |
                    stateEditText.getText().toString().isEmpty() |
                    zipCodeEditText.getText().toString().isEmpty() |
                    countryEditText.getText().toString().isEmpty() |
                    cardNumberEditText.getText().toString().isEmpty() |
                    expirationDateEditText.getText().toString().isEmpty() |
                    securityCodeEditText.getText().toString().isEmpty();

            if (!emptyInput)
            {
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
                DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00");
                showOrderConfirmationDialog("Order Total: " + decimalFormat.format(orderTotal));
            }
            else
            {
                displayErrorDialog();
            }
        }
    }  // End of class CheckoutButtonHandler

    /**
     * A method to create a confirmation order dialog.
     */
    public void showOrderConfirmationDialog(String message)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder( CheckoutActivity.this );
        alert.setTitle( "Confirm Purchase" );
        alert.setMessage( message );
        confirmationDialogListener continuePurchase = new confirmationDialogListener( );
        alert.setPositiveButton( "YES", continuePurchase );
        alert.setNegativeButton( "NO", continuePurchase );
        alert.show();
    }

    /**
     * A method to display a error dialog.
     */
    private void displayErrorDialog()
    {
        new AlertDialog.Builder(CheckoutActivity.this)
                .setTitle("Invalid Input")
                .setMessage("One or more fields contains null input")
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Dialog listener.
     */
    private class confirmationDialogListener implements DialogInterface.OnClickListener
    {
        /**
         * Listener function
         *
         * @param dialog The dialog this is associated.
         * @param id The result of the dialog.
         */
        public void onClick(DialogInterface dialog, int id)
        {
            if (id == -1)  // If positive result (yes button)
            {
                // Go to Browse page
                Intent browseIntent = new Intent(CheckoutActivity.this, BrowseActivity.class);
                finishAffinity();
                CheckoutActivity.this.startActivity(browseIntent);
            }
            else if( id == -2 )  // If negative result (no button)
            {
                dialog.dismiss();
            }
        }
    }  // End of class confirmationDialogListener
}  // End of class CheckoutActivity