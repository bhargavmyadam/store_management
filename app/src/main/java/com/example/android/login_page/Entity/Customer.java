package com.example.android.login_page.Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    private int customerId;
    private int numberOfVisits;
    private String customerName;
    private ArrayList<String> phoneNumbers;
    private String street;
    private String city;
    private String houseNumber;

    public Customer(int customerId, int numberOfVisits, String customerName, ArrayList<String> phoneNumbers, String street, String city, String houseNumber) {
        this.customerId = customerId;
        this.numberOfVisits = numberOfVisits;
        this.customerName = customerName;
        this.phoneNumbers = phoneNumbers;
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
