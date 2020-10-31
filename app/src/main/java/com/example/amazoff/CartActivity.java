package com.example.amazoff;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

public class CartActivity extends AppCompatActivity {
    private DatabaseManager dataMan;
    private Context thisContext;
    private Set<Integer> keys;
    private Hashtable<Integer, Integer> cartProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dataMan = new DatabaseManager(this);

        thisContext = this;

//                   Intent goToCheckout = new Intent(thisContext, CheckoutActivity.class);
//                   thisContext.startActivity(goToCheckout);
        updateView();
    }



    public void updateView () {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        Hashtable<Integer, Integer> cartProducts = dataMan.getCartItems();

        keys = cartProducts.keySet();

        ScrollView scrollView = new ScrollView(this);
        GridLayout grid = new GridLayout(this);
//        GridLayout grid = findViewById(R.id.grid);
        RelativeLayout parentLayout = new RelativeLayout(this);
        Button clearCartButton = new Button(this);
        Button checkoutButton = new Button(this);
//        RelativeLayout parentLayout = findViewById(R.id.cartActivity);
        parentLayout.addView(scrollView);
        scrollView.addView(grid);

        grid.setColumnCount(2);
        grid.setRowCount(3);

        // TODO use this for loop when database functionality is created
        for(Integer key : keys) {
            PurchasedProductView newPurchasedProduct = new PurchasedProductView(this, key, cartProducts.get(key));
            grid.addView(newPurchasedProduct.getParentLayout(), width / 2 , height / 3);
            grid.addView(newPurchasedProduct.getInfoLayout(), width / 2 , height / 3);
        }

//        setContentView(R.layout.activity_cart);
        setContentView(parentLayout);
    }

    private class DeleteButtonOnClickListener implements View.OnClickListener {
        private int deletedProduct = 0;
        public DeleteButtonOnClickListener(int ID) {
            this.deletedProduct = ID;
        }

        @Override
        public void onClick(View v) {
            // delete product from grid view of selected ID
            dataMan.removeFromCart(deletedProduct);

            // update view without product
            updateView();
        }
    }

    private class ClearCartButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO remove all products from cart
            for(Integer key : keys){
                for(int i = 0; i < cartProducts.get(key); i++){
                    dataMan.removeFromCart(key);
                }
            }

        }
    }

    private class CheckoutButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent goToCheckout = new Intent(thisContext, CheckoutActivity.class);
            thisContext.startActivity(goToCheckout);

        }
    }

    private class PurchasedProductView extends View {
        private LinearLayout parentLayout;
        private LinearLayout parameters;
        private DisplayMetrics metrics;
        private int width;
        private int height;
        private TextView productTitle;
        private TextView productPrice;
        private  TextView productQuantity;
        private TextView productTotal;
        private ImageView productImage;
        private Button addButton;
        private Button subtractButton;
        private Button deleteButton;
        private LinearLayout quantityLayout;
        private int purchasedQuantity = 0;  // TODO should be either replaced with database call or taken from database call
        private int selectedProduct = 0;


        public PurchasedProductView(Context context, int ID, int quantity) {
            super(context);
            parentLayout = new LinearLayout(context);

            selectedProduct = ID;
            purchasedQuantity = quantity;

            // get display parameters
            metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            width = metrics.widthPixels;
            height = metrics.heightPixels;

            parameters = new LinearLayout(context);
            productTitle = new TextView(context);
            productPrice = new TextView(context);
            productQuantity = new TextView(context);
            productTotal = new TextView(context);
            productImage = new ImageView(context);

            addButton = new Button(context);
            subtractButton = new Button(context);
            deleteButton = new Button(context);

            quantityLayout = new LinearLayout(context);

            setInfoLayout(context);
            setParentLayout();
        }

        private void setInfoLayout(Context context){
            addButton.setText("+");
            subtractButton.setText("-");
            deleteButton.setText("Delete");

            // set on click listeners for buttons
            addButton.setOnClickListener(new addButtonOnClickListener());
            subtractButton.setOnClickListener(new subtractButtonOnClickListener());
            deleteButton.setOnClickListener(new DeleteButtonOnClickListener(selectedProduct));

            // TODO use these when database functionality is complete
            productTitle.setText(dataMan.getProductByID(selectedProduct).getName());
            productPrice.setText(String.valueOf(dataMan.getProductByID(selectedProduct).getPrice()));
            productTotal.setText(String.valueOf(dataMan.getProductByID(selectedProduct).getPrice() * purchasedQuantity));

            quantityLayout.setOrientation(LinearLayout.HORIZONTAL);
            quantityLayout.addView(subtractButton);
            productQuantity.setText(String.valueOf(purchasedQuantity));
            quantityLayout.addView(productQuantity);
            quantityLayout.addView(addButton);

            parameters.setOrientation(LinearLayout.VERTICAL);
            parameters.addView(productTitle);
            parameters.addView(productPrice);
            parameters.addView(quantityLayout);
            parameters.addView(productTotal);
            parameters.addView(deleteButton);

       }

       public LinearLayout getInfoLayout() {
            return parameters;
       }

        public void updateView() {

        }

        private class addButtonOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                // increase purchased quantity
                purchasedQuantity++;

                // increase quantity in database
                dataMan.addToCart(selectedProduct);

                productQuantity.setText(String.valueOf(purchasedQuantity));
                productTotal.setText((String)"$" + String.valueOf(dataMan.getProductByID(selectedProduct).getPrice() * purchasedQuantity) );

            }
        }

        private class subtractButtonOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                // increase purchased quantity
                if(purchasedQuantity > 0){
                    purchasedQuantity--;
                }

                productQuantity.setText(String.valueOf(purchasedQuantity));
                productTotal.setText((String)"$" + String.valueOf(dataMan.getProductByID(selectedProduct).getPrice() * purchasedQuantity));

            }
        }

        private void setParentLayout() {
            parentLayout.setOrientation(LinearLayout.HORIZONTAL);

            productImage.setImageBitmap(dataMan.getProductByID(selectedProduct).getImage());
            productImage.setMaxWidth(width / 2);
            productImage.setMaxHeight(height / 3);
            productImage.setMinimumWidth(width / 2);

            parentLayout.addView(productImage);
//            parentLayout.addView(parameters);
        }

        public LinearLayout getParentLayout() {
            return parentLayout;
        }

    }




}