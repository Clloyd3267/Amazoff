/*=============================================================================*
* Filename    : Order.java
* Author      : Kyle Bielby, Chris Lloyd, Marc Simone, Wayne Wells
* Due Date    : 2020/11/06
* Project     : EE-408 (CU) Final Project (Amazoff Shopping App)
* Class(s)    : Order
* Description : Model class to store data for a single order.
*=============================================================================*/

// Package Definition
package com.example.amazoff;

// Imports
import java.util.Hashtable;

/**
 * Model class to store data for a single order.
 */
public class Order
{
    /**
     * The first name of the person who ordered.
     */
    private String firstName;

    /**
     * The last name of the person who ordered.
     */
    private String lastName;

    /**
     * The phone number of the person who ordered.
     */
    private String phoneNumber;

    /**
     * The email address of the person who ordered.
     */
    private String email;

    /**
     * The address of the person who ordered.
     */
    private String address;

    /**
     * The city of the person who ordered.
     */
    private String city;

    /**
     * The state of the person who ordered.
     */
    private String state;

    /**
     * The zip code of the person who ordered.
     */
    private int zipCode;

    /**
     * The country of the person who ordered.
     */
    private String country;

    /**
     * The card number of the person who ordered.
     */
    private String cardNumber;

    /**
     * The expiration date on the card of the person who ordered.
     */
    private String expirationDate;

    /**
     * The expiration date on the card of the person who ordered.
     */
    private int securityCode;

    /**
     * The items ordered.
     */
    private Hashtable<Integer, Integer> cartItems;

    /**
     * Constructor for class Order.
     *
     * @param firstName The first name to set.
     * @param lastName The last name to set.
     * @param phoneNumber The phone number to set.
     * @param email The email address to set.
     * @param address The address to set.
     * @param city The city to set.
     * @param state The state to set.
     * @param zipCode The zip code to set.
     * @param country The country to set.
     * @param cardNumber The card number to be set.
     * @param expirationDate The card expiration date to be set.
     * @param securityCode The card security code to be set.
     * @param cartItems The ordered items to be set.
     */
    public Order(String firstName,
                 String lastName,
                 String phoneNumber,
                 String email,
                 String address,
                 String city,
                 String state,
                 int zipCode,
                 String country,
                 String cardNumber,
                 String expirationDate,
                 int securityCode,
                 Hashtable<Integer, Integer> cartItems)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.cartItems = cartItems;
    }

    /**
     * Getter for first name.
     *
     * @return (String): The first name.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Setter for first name.
     *
     * @param firstName The first name.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Getter for last name.
     *
     * @return (String): The last name.
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Setter for last name.
     *
     * @param lastName The last name.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Getter for phone number.
     *
     * @return (String): The phone number.
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Setter for phone number.
     *
     * @param phoneNumber The phone number.
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for email address.
     *
     * @return (String): The email address.
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Setter for email address.
     *
     * @param email The email address.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Getter for address.
     *
     * @return (String): The address.
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * Setter for address.
     *
     * @param address The address.
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Getter for city.
     *
     * @return (String): The city.
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Setter for city.
     *
     * @param city The city.
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Getter for state.
     *
     * @return (String): The state.
     */
    public String getState()
    {
        return state;
    }

    /**
     * Setter for state.
     *
     * @param state The state.
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * Getter for zip code.
     *
     * @return (int): The zip code.
     */
    public int getZipCode()
    {
        return zipCode;
    }

    /**
     * Setter for zip code.
     *
     * @param zipCode The zip code.
     */
    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }

    /**
     * Getter for country.
     *
     * @return (String): The country.
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * Setter for country.
     *
     * @param country The country.
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     * Getter for card number.
     *
     * @return (String): The card number.
     */
    public String getCardNumber()
    {
        return cardNumber;
    }

    /**
     * Setter for card number.
     *
     * @param cardNumber The card number.
     */
    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    /**
     * Getter for card expiration date.
     *
     * @return (String): The card expiration date.
     */
    public String getExpirationDate()
    {
        return expirationDate;
    }

    /**
     * Setter for card expiration date.
     *
     * @param expirationDate The card expiration date.
     */
    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    /**
     * Getter for card security code.
     *
     * @return (int): The card security code.
     */
    public int getSecurityCode()
    {
        return securityCode;
    }

    /**
     * Setter for card security code.
     *
     * @param securityCode The card security code.
     */
    public void setSecurityCode(int securityCode)
    {
        this.securityCode = securityCode;
    }

    /**
     * Getter for cart items.
     *
     * @return (Hashtable<Integer, Integer>): The cart items.
     */
    public Hashtable<Integer, Integer> getCartItems()
    {
        return cartItems;
    }

    /**
     * Setter for cart items.
     *
     * @param cartItems The cart items.
     */
    public void setCartItems(Hashtable<Integer, Integer> cartItems)
    {
        this.cartItems = cartItems;
    }
}  // End of class Order
