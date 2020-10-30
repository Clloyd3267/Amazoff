package com.example.amazoff;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity {
    private DatabaseManager dataMan;
    private ArrayList<Product> products;
    private Context thisContext;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        dataMan = new DatabaseManager(this);    //create new database

        Intent parentIntent = getIntent();

        products = dataMan.getProducts();

        thisContext = this;

        updateView();
    }

    public void updateView () {
        RelativeLayout thisLayout = findViewById(R.id.mainBrowseLayout);  // create relative layout to hold image views in
//        Button goToCart = findViewById(R.id.goToCartButton);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        //create grid layout for products
        GridLayout productImages = new GridLayout(this);  // store images in grid view
        ScrollView scrollView = new ScrollView(this);
        productImages.setRowCount(products.size());
        productImages.setColumnCount(2);    //TODO change number of columns for number of images

        // store view details buttons
        ArrayList<Button> productDetails = new ArrayList<>();

        for(Product prod : products) {
            Button viewDetails = new Button(this);
            ImageView img = new ImageView(this);  // create new image view to store product image
            LinearLayout info =  new LinearLayout(this);  // create new linear layout for product info
            info.setOrientation(LinearLayout.VERTICAL);

            // Get Product
            Bitmap bitmp = prod.getImage();  // get Bitmap Object of product image
            String productName = prod.getName();
            String productDescription = prod.getDescription();
            double productPrice = prod.getPrice();
            int productRating = prod.getRating();

            // Create Text Views for each product parameter
            TextView name = new TextView(this);
            TextView Description = new TextView(this);
            TextView Price = new TextView(this);
            TextView Rating = new TextView(this);

            // set text views to product parameters
            name.setText(productName);
            Description.setText(productDescription);
            Price.setText(String.valueOf(productPrice));
            Rating.setText(String.valueOf(productRating));

            viewDetails.setText("view details");

            ViewButtonHandler newHandler = new ViewButtonHandler(prod);
            viewDetails.setOnClickListener(newHandler);

            // add text views to linear layout
            info.addView(name);
            info.addView(Description);
            info.addView(Price);
            info.addView(Rating);
            info.addView(viewDetails);

            img.setImageBitmap(bitmp);  // store bitmap of resource in image view
//            img.setImageResource(prod.getImageID());

            productImages.addView(img, width / 2, height / 3 );
            productImages.addView(info, width / 2, height / 3 );
        }

        scrollView.addView(productImages);
//        thisLayout.addView(goToCart);
        thisLayout.addView(scrollView);
        setContentView(thisLayout);
    }

    private class ViewButtonHandler implements View.OnClickListener{
        public ViewButtonHandler(Product selectedProduct){
            activeProduct = selectedProduct;
        }

        public void onClick( View v) {
//            openDetailsView(v, activeProduct);
            Intent newView = new Intent(thisContext, DetailsActivity.class);
            newView.putExtra("Product ID", String.valueOf(activeProduct.getID()));
            thisContext.startActivity(newView);

        }

        private Product activeProduct;
    }

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
//    private void openDetailsView (View v, Product selectedProduct) {
//        // call details view with item
////        Intent newView = new Intent(this, DetailsActivity.class);
//        Intent newView = new Intent(this, DetailsActivity.class);
//        newView.putExtra("Product Name", selectedProduct.getName());
//        newView.putExtra("Product Description", selectedProduct.getDescription());
//        newView.putExtra("Product Price", String.valueOf(selectedProduct.getPrice()));
//        newView.putExtra("Product Rating", String.valueOf(selectedProduct.getRating()));
//        newView.putExtra("Product Image", BitMapToString(selectedProduct.getImage()));  // TODO fix so that converts id integer to String
//        selectedProduct.setImageID(R.drawable.gaming_computer);
//        newView.putExtra("Product Image", String.valueOf(selectedProduct.getImageID()));
//        newView.putExtra("Product Image", String.valueOf(R.drawable.gaming_computer));
//        newView.putExtra("Product Image", selectedProduct.getImage());
//        newView.putExtra("Product Image", selectedProduct);
//        newView.putExtra("Product Image", (String)selectedProduct.getImage());

//        Bundle b = new Bundle();
//        b.putSerializable("serialzable",selectedProduct);


//        SentObject newObject = new SentObject(selectedProduct);
//        newView.putExtra("Product Image", selectedProduct);

//        String xbox = "xbox";
//        String playstation = "playstation";
//        String computer = "computer";
//
//        if(xbox.equals(selectedProduct.getName())){
//            newView.putExtra("Product Image", String.valueOf(R.drawable.xbox_one_s));
//        }
//        else if(playstation.equals(selectedProduct.getName())){
//            newView.putExtra("Product Image", String.valueOf(R.drawable.playstation_5) );
//        }
//        else if(computer.equals(selectedProduct.getName())){
//            newView.putExtra("Product Image", String.valueOf(R.drawable.gaming_computer) );
//        }


//        final Bitmap objSent = selectedProduct.getImage();
//        final Bundle bundle = new Bundle();
//        bundle.putBinder("object_value", new ObjectWrapperForBinder(objSent));
////        startActivity(new Intent(this, newView.class).putExtras(bundle));
//        newView.putExtras(bundle);

//        this.startActivity(newView);
//    }

    public byte[] BitMapToByteArray (Bitmap bitmap){
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);
        byte [] b = baos.toByteArray();
//
        return b;
    }


}