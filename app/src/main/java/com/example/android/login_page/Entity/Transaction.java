package com.example.android.login_page.Entity;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private int transactionId;
    private Date date;

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
}
