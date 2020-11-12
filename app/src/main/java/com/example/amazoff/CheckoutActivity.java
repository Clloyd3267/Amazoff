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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        // First name
        firstNameEditText = (EditText) findViewById(R.id.first_name_text);
        firstNameEditText.addTextChangedListener(new TextValidator(firstNameEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0) ||
                    (!isAlpha(text)))
                {
                    textView.setError("Invalid First Name!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // Last name
        lastNameEditText = (EditText) findViewById(R.id.last_name_text);
        lastNameEditText.addTextChangedListener(new TextValidator(lastNameEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0) ||
                    (!isAlpha(text)))
                {
                    textView.setError("Invalid Last Name!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // Phone number
        phoneNumberEditText = (EditText) findViewById(R.id.phone_number_text);
        phoneNumberEditText.addTextChangedListener(new TextValidator(phoneNumberEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0) ||
                    (!isPhoneNumber(text)))
                {
                    textView.setError("Invalid Phone Number!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // Email address
        emailEditText = (EditText) findViewById(R.id.email_address_text);
        emailEditText.addTextChangedListener(new TextValidator(emailEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0) ||
                    (!isEmailAddress(text)))
                {
                    textView.setError("Invalid Email Address!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // Address
        addressEditText = (EditText) findViewById(R.id.address_text);
        addressEditText.addTextChangedListener(new TextValidator(addressEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0))
                {
                    textView.setError("Invalid Address!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // City
        cityEditText = (EditText) findViewById(R.id.city_text);
        cityEditText.addTextChangedListener(new TextValidator(cityEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0) ||
                    (!isAlpha(text)))
                {
                    textView.setError("Invalid City!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // State
        stateEditText = (EditText) findViewById(R.id.state_text);
        stateEditText.addTextChangedListener(new TextValidator(stateEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0) ||
                    (!isAlpha(text)))
                {
                    textView.setError("Invalid State!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // Zipcode
        zipCodeEditText = (EditText) findViewById(R.id.zip_code_text);
        zipCodeEditText.addTextChangedListener(new TextValidator(zipCodeEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0) ||
                    (!isZipCode(text)))
                {
                    textView.setError("Invalid Zipcode!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // Country
        countryEditText = (EditText) findViewById(R.id.country_text);
        countryEditText.addTextChangedListener(new TextValidator(countryEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0) ||
                    (!isAlpha(text)))
                {
                    textView.setError("Invalid Country!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // Card number
        cardNumberEditText = (EditText) findViewById(R.id.card_number_text);
        cardNumberEditText.addTextChangedListener(new TextValidator(cardNumberEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() < 13) ||
                    (text.length() > 19) ||
                    (!isNumber(text)))
                {
                    textView.setError("Invalid Card Number!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // Expiration date
        expirationDateEditText = (EditText) findViewById(R.id.expiration_date_text);
        expirationDateEditText.addTextChangedListener(new TextValidator(expirationDateEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0) ||
                    (!isExpDate(text)))
                {
                    textView.setError("Invalid Expiration Date!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });

        // CVC code
        securityCodeEditText = (EditText) findViewById(R.id.security_code_text);
        securityCodeEditText.addTextChangedListener(new TextValidator(securityCodeEditText)
        {
            @Override public void validate(TextView textView, String text)
            {
                if ((text == null) ||
                    (text.length() == 0) ||
                    (!isCVC(text)))
                {
                    textView.setError("Invalid Security Code!");
                }
                else
                {
                    textView.setError(null);
                }
            }
        });
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
            // Trigger onTextChanged
            firstNameEditText.setText(firstNameEditText.getText());
            lastNameEditText.setText(lastNameEditText.getText());
            phoneNumberEditText.setText(phoneNumberEditText.getText());
            emailEditText.setText(emailEditText.getText());
            addressEditText.setText(addressEditText.getText());
            cityEditText.setText(cityEditText.getText());
            stateEditText.setText(stateEditText.getText());
            zipCodeEditText.setText(zipCodeEditText.getText());
            countryEditText.setText(countryEditText.getText());
            cardNumberEditText.setText(cardNumberEditText.getText());
            expirationDateEditText.setText(expirationDateEditText.getText());
            securityCodeEditText.setText(securityCodeEditText.getText());

            // Ensure all fields are valid
            boolean invalidInput = ((firstNameEditText.getError() != null) ||
                                    (lastNameEditText.getError() != null) ||
                                    (phoneNumberEditText.getError() != null) ||
                                    (emailEditText.getError() != null) ||
                                    (addressEditText.getError() != null) ||
                                    (cityEditText.getError() != null) ||
                                    (stateEditText.getError() != null) ||
                                    (zipCodeEditText.getError() != null) ||
                                    (countryEditText.getError() != null) ||
                                    (cardNumberEditText.getError() != null) ||
                                    (expirationDateEditText.getError() != null) ||
                                    (securityCodeEditText.getError() != null));

            if (!invalidInput)
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
                .setMessage("One or more fields contains errors")
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

    public abstract class TextValidator implements TextWatcher {
        private final TextView textView;

        public TextValidator(TextView textView) {
            this.textView = textView;
        }

        public abstract void validate(TextView textView, String text);

        @Override
        final public void afterTextChanged(Editable s) {
            String text = textView.getText().toString();
            validate(textView, text);
        }

        @Override
        final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

        @Override
        final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
    }

    public boolean isAlpha(String name)
    {
        return name.matches("[a-zA-Z]+");
    }

    public boolean isNumber(String number)
    {
        return number.matches("\\d+(?:\\.\\d+)?");
    }

    public boolean isEmailAddress(String email)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPhoneNumber(String num)
    {
        return Patterns.PHONE.matcher(num).matches();
    }

    public boolean isZipCode(String zipCode)
    {
        return zipCode.matches("^\\d{5}(?:[-\\s]\\d{4})?$");
    }

    public boolean isExpDate(String date)
    {
        return date.matches("(0[1-9]|10|11|12)/20[0-9]{2}$");
    }

    public boolean isCVC(String cvc)
    {
        return cvc.matches("^[0-9]{3,4}$");
    }
}  // End of class CheckoutActivity
