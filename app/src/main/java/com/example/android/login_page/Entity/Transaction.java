package com.example.android.login_page.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Transaction implements Serializable {
    private int transactionId;
    private Date date;
    private int adminId;
    private int customerId;
    private HashMap<Integer,Integer> items;

    public HashMap<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<Integer, Integer> items) {
        this.items = items;
    }

    private int quantity;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
