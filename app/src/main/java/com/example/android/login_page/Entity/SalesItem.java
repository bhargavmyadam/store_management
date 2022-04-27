package com.example.android.login_page.Entity;

import java.io.Serializable;

public class SalesItem implements Serializable {
    private int itemId;
    private int quantity;

    public SalesItem(int itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
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



}
