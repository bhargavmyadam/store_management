package com.example.android.login_page.Entity;

import java.io.Serializable;

public class AdminItem implements Serializable {
    private int adminId;
    private int itemId;
    private int quantity;
    private String date;

    public AdminItem(int adminId, int itemId, int quantity,String date) {
        this.adminId = adminId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.date = date;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
