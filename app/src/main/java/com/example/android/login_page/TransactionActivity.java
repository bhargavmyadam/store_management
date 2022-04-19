package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.android.login_page.DAO.AdminDao;
import com.example.android.login_page.DAO.CustomerDao;
import com.example.android.login_page.DAO.ItemDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Customer;
import com.example.android.login_page.Entity.Item;
import com.example.android.login_page.Entity.Transaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TransactionActivity extends AppCompatActivity {
    EditText mTId,mTDate,mAdminName,mCustomerName,mCustomerMobile,mItems;
    Transaction transaction;
    Admin admin;
    FloatingActionButton mHomeButton;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        initFields();
        populateFields();
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(TransactionActivity.this,DashBoardActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });
    }

    private void populateFields() {
        mTId.setText(String.valueOf(transaction.getTransactionId()));
        mTDate.setText(transaction.getDate());
        mAdminName.setText(AdminDao.getAdminName(dbHelper.getReadableDatabase(),transaction.getAdminId()));
        Customer customer = CustomerDao.getCustomerById(dbHelper.getReadableDatabase(),transaction.getCustomerId());
        mCustomerName.setText(customer.getCustomerName());
        mCustomerMobile.setText(customer.getPhoneNumbers().get(0));
        mItems.setText("ITEM    ->  QUANTITY X PRICE = AMOUNT\n");
        int total = 0;
        for(int itemId: transaction.getItems().keySet()){
            Item item = ItemDao.getItemById(dbHelper.getReadableDatabase(),itemId);
            mItems.append(item.getItemName() + " -> " + transaction.getItems().get(itemId) + " X " + item.getPrice() + " = " + transaction.getItems().get(itemId) * item.getPrice()+"\n");
            total += (transaction.getItems().get(itemId) * item.getPrice());
        }
        mItems.append("TOTAL    =    " + total);
        mTId.setEnabled(false);
        mTDate.setEnabled(false);
        mAdminName.setEnabled(false);
        mCustomerMobile.setEnabled(false);
        mCustomerName.setEnabled(false);
        mItems.setEnabled(false);
    }

    private void initFields() {
        mTId = findViewById(R.id.et_tid);
        mTDate = findViewById(R.id.et_date);
        mAdminName = findViewById(R.id.et_admin_name);
        mCustomerName = findViewById(R.id.et_customer_name);
        mCustomerMobile = findViewById(R.id.et_customer_mobile);
        mItems = findViewById(R.id.et_items);
        mHomeButton = findViewById(R.id.bt_home);
        Intent intent = getIntent();
        transaction = (Transaction) intent.getSerializableExtra("transaction");
        admin = (Admin)intent.getSerializableExtra("admin");
        dbHelper = new DBHelper(this);
    }
}