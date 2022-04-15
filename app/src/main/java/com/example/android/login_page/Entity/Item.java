package com.example.android.login_page.Entity;

import java.io.Serializable;

public class Item implements Serializable {
    private int itemId;
    private String itemName;
    private String brand;
    private int price;
    private String category;
    private int Quantity;
    private int size;

    public Item(int itemId, String itemName, String brand, int price, String category, int quantity, int size) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.brand = brand;
        this.price = price;
        this.category = category;
        Quantity = quantity;
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }


}
