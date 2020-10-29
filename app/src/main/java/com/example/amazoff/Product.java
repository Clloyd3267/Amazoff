/*=============================================================================*
* Filename    : Product.java
* Author      : Kyle Bielby, Chris Lloyd, Marc Simone, Wayne Wells
* Due Date    : 2020/11/06
* Project     : EE-408 (CU) Final Project (Amazoff Shopping App)
* Class(s)    : Product
* Description : Model class to store data for a single product.
*=============================================================================*/

// Package Definition
package com.example.amazoff;

// Imports
import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Model class to store data for a single product.
 */
public class Product implements Serializable
{
    /**
     * The product image.
     */
    private int productID;

    /**
     * The product name.
     */
    private String name;

    /**
     * The product description.
     */
    private String description;

    /**
     * The product rating.
     */
    private int rating;

    /**
     * The product number of reviews.
     */
    private int numReviews;    

    /**
     * The product price.
     */
    private double price;

    /**
     * The product image.
     */
    private Bitmap image;

    /**
     * Empty Constructor for class Product.
     */
    public Product()
    {
        name = "";
        description = "";
        rating = 0;
        numReviews = 0;
        price = 0.0;
        image = null;
    }

    /**
     * Constructor for class Product.
     * 
     * @param name The product name to set.
     * @param description The product description to set.
     * @param rating The product rating to set.
     * @param numReviews The product number of reviews to set.
     * @param price The product price to set.
     * @param image The product image to set.
     */
    public Product(String name, String description, int rating, int numReviews, double price, Bitmap image) 
    {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.numReviews = numReviews;
        this.price = price;
        this.image = image;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Getter for product name.
     * 
     * @return (String): The name of this product.
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Setter for product name.
     * 
     * @param name The product name to set.
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Getter for product description.
     * 
     * @return (String): The description of this product.
     */
    public String getDescription() 
    {
        return description;
    }

    /**
     * Setter for product description.
     * 
     * @param description The product description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Getter for product rating.
     * 
     * @return (int): The rating of this product.
     */    
    public int getRating()
    {
        return rating;
    }

    /**
     * Setter for product rating.
     * 
     * @param rating The product rating to set.
     */    
    public void setRating(int rating)
    {
        this.rating = rating;
    }

    /**
     * Getter for product numReviews.
     * 
     * @return (int): The numReviews of this product.
     */    
    public int getNumReviews()
    {
        return numReviews;
    }

    /**
     * Setter for product numReviews.
     * 
     * @param numReviews The product numReviews to set.
     */    
    public void setNumReviews(int numReviews)
    {
        this.numReviews = numReviews;
    }

    /**
     * Getter for product price.
     * 
     * @return (String): The price of this product.
     */    
    public double getPrice()
    {
        return price;
    }

    /**
     * Setter for product price.
     * 
     * @param price The product price to set.
     */    
    public void setPrice(double price)
    {
        this.price = price;
    }    

    /**
     * Getter for product image.
     * 
     * @return (Bitmap): The image of this product.
     */    
    public Bitmap getImage()
    {
        return image;
    }

    /**
     * Setter for product image.
     * 
     * @param image The product image to set.
     */    
    public void setImage(Bitmap image)
    {
        this.image = image;
    } 
}  // End of class Product