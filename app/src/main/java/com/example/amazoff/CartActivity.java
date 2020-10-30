package com.example.amazoff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {
    private DatabaseManager dataMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        updateView();
    }

    public void updateView () {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        ScrollView scrollView = new ScrollView(this);
        GridLayout grid = new GridLayout(this);
        RelativeLayout parentLayout = new RelativeLayout(this);

        parentLayout.addView(scrollView);
        scrollView.addView(grid);

        // TODO change to actual layout
        LinearLayout parameters = new LinearLayout(this);
        TextView productTitle = new TextView(this);
        TextView productPrice = new TextView(this);
        TextView productQuantity = new TextView(this);
        TextView productTotal = new TextView(this);
        ImageView productImage = new ImageView(this);

        Button addButton = new Button(this);
        Button subtractButton = new Button(this);
        Button deleteButton = new Button(this);

        addButton.setText("+");
        subtractButton.setText("-");
        deleteButton.setText("Delete");

        productTitle.setText("xbox");
        productPrice.setText("$5.00");
        productTotal.setText("$5.00");

        LinearLayout quantityLayout = new LinearLayout(this);
        quantityLayout.setOrientation(LinearLayout.HORIZONTAL);
        quantityLayout.addView(addButton);
        productQuantity.setText("1");
        quantityLayout.addView(productQuantity);
        quantityLayout.addView(subtractButton);

        parameters.setOrientation(LinearLayout.VERTICAL);
        parameters.addView(productTitle);
        parameters.addView(productPrice);
        parameters.addView(quantityLayout);
        parameters.addView(productTotal);
        parameters.addView(deleteButton);

        productImage.setImageResource(R.drawable.playstation_5);

        grid.setColumnCount(2);
        grid.setRowCount(3);
        grid.addView(productImage, width / 2, height / 3);
        grid.addView(parameters, width / 2, height / 3);


        // TODO end of what is to be modified later




        setContentView(parentLayout);
    }


}