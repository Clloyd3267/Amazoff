package com.example.amazoff;

import android.graphics.Bitmap;

public class Product
{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    private String name;
    private String description;
    private int rating;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;
    private Bitmap image;

    // Default Constructor (generates test data with variance)
    public Product (int n)
    {
        name = "Test" + n;
        description = "This is description #" + n;
        rating = 2;
        price = 0.5;
        image = null;
    }

    // Default Constructor (generates test data)
    public Product ()
    {
        name = "Test";
        description = "This is a description";
        rating = 2;
        price = 0.5;
        image = null;
    }

    // Final Constructor
    public Product(String name, String description, int rating, double price, Bitmap image) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }
}