package com.example.amazoff;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {
    private DatabaseManager dataMan;
    private EditText addressInfo;
    private EditText cityInfo;
    private EditText stateInfo;
    private EditText billingZipCodeInfo;
    private EditText countryInfo;

    private EditText cardName;
    private EditText cardNumber;
    private EditText expirationDate;
    private EditText cvcNumber;
    private EditText cardZipNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        dataMan = new DatabaseManager(this);

        addressInfo = findViewById(R.id.addressEditText);
        stateInfo = findViewById(R.id.stateEditText);
        cityInfo = findViewById(R.id.cityEditText);
        billingZipCodeInfo = findViewById(R.id.zipEditText);
        countryInfo = findViewById(R.id.countryEditText);

        cardName = findViewById(R.id.cardNameEditText);
        cardNumber = findViewById(R.id.cardNunberEditText);
        expirationDate = findViewById(R.id.expirationDateEditText);
        cvcNumber = findViewById(R.id.securityCodeEditText);
        cardZipNumber = findViewById(R.id.zipCodeEditText);


//         addressInfo;
//         cityInfo;
//         stateInfo;
//         billingZipCodeInfo;
//         countryInfo;
//
//         cardName;
//         cardNumber;
//         expirationDate;
//         cvcNumber;
//         cardZipNumber;

        updateView();
    }

    public void updateView() {


        Button payButton = new Button(this);

        payButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Add to cart model

            }
        });

    }

    private class zipCodeChanged implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}