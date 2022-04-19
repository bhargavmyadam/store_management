package com.example.android.login_page.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Transaction implements Serializable {
    private int transactionId;
    private String date;
    private int adminId;
    private int customerId;
    private HashMap<Integer,Integer> items;

    public Transaction(int transactionId, String date, int adminId, int customerId, HashMap<Integer, Integer> items) {
        this.transactionId = transactionId;
        this.date = date;
        this.adminId = adminId;
        this.customerId = customerId;
        this.items = items;
    }

    public HashMap<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<Integer, Integer> items) {
        this.items = items;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
