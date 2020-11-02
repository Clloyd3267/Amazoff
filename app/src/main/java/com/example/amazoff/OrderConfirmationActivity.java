package com.example.amazoff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderConfirmationActivity extends AppCompatActivity {
    private DatabaseManager dataMan;
    private TextView streetInfo;
    private TextView zipCodeInfo;
    private TextView cardNumber;
    private TextView cardName;
    private TextView expirationDate;
    private TextView cvcNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
    }
}