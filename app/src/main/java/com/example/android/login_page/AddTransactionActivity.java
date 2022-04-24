package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.android.login_page.DAO.CustomerContactDao;
import com.example.android.login_page.DAO.CustomerDao;
import com.example.android.login_page.DAO.ItemDao;
import com.example.android.login_page.DAO.TransactionDao;
import com.example.android.login_page.DAO.TransactionUpdateItemDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Item;
import com.example.android.login_page.Entity.Transaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class AddTransactionActivity extends AppCompatActivity {
    FloatingActionButton mHomeButton;
    Admin admin;
    RadioButton mOldCustomer;
    RadioButton mNewCustomer;
    EditText mCustomerName;
    EditText mCustomerMobile;
    EditText mPhone1;
    EditText mPhone2;
    EditText mStreet;
    EditText mCity;
    EditText mHouseNumber;
    EditText mSelectedItems;
    Button mAddOrRemoveItems;
    Button mAddButton;
    AwesomeValidation mAwesomeValidation;
    int transactionId;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);mHomeButton = findViewById(R.id.bt_home);
        initFields();
        populateFields();
        mOldCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewCustomer.setChecked(false);
                disableCustomerDetails();
            }
        });
        mNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOldCustomer.setChecked(false);
                enableCustomerDetails();
            }
        });
        mAddOrRemoveItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransactionDao.addDummyTransaction(transactionId,admin.getAdminID());
                Bundle bundle = getTransactionBundle();
                Intent intent = new Intent(AddTransactionActivity.this,AddOrRemoveItemsActivity.class);
                intent.putExtra("admin",admin);
                intent.putExtra("transactionBundle",bundle);
                startActivity(intent);
            }
        });
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransactionUpdateItemDao.deleteTransaction(dbHelper.getWritableDatabase(),transactionId);
                TransactionDao.deleteDummyTransaction(dbHelper.getWritableDatabase(),transactionId);
                Intent intent = new Intent(AddTransactionActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields()){
                    // TODO: add case for new customer here
                    int customerId = CustomerContactDao.getCustomerId(dbHelper.getReadableDatabase(),mCustomerMobile.getText().toString());
                    TransactionDao.updateDummyTransaction(dbHelper.getWritableDatabase(),transactionId,customerId);
                    ItemDao.updateQuantity(dbHelper.getWritableDatabase(),transactionId);
                    Intent intent = new Intent(AddTransactionActivity.this,TransactionsActivity.class);
                    intent.putExtra("admin",admin);
                    startActivity(intent);
                }
            }
        });
    }

    private Bundle getTransactionBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isChecked",mOldCustomer.isChecked());
        bundle.putString("customerName",mCustomerName.getText().toString());
        bundle.putString("phone1",mPhone1.getText().toString());
        bundle.putString("phone2",mPhone2.getText().toString());
        bundle.putString("street",mStreet.getText().toString());
        bundle.putString("city",mCity.getText().toString());
        bundle.putString("houseNumber",mHouseNumber.getText().toString());
        bundle.putString("customerMobile",mCustomerMobile.getText().toString());
        return bundle;
    }

    private boolean validateFields() {
        return true;
    }

    private void populateFields() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("transactionBundle");
        if(bundle != null){
            boolean oldCustomer = bundle.getBoolean("isChecked",true);
            if(oldCustomer){
                mOldCustomer.setChecked(true);
                mNewCustomer.setChecked(false);
                disableCustomerDetails();
            }
            else{
                mOldCustomer.setChecked(false);
                mNewCustomer.setChecked(true);
                enableCustomerDetails();
            }
            String customerMobile = bundle.getString("customerMobile","");
            String customerName = bundle.getString("customerName","");
            String phone1 = bundle.getString("phone1","");
            String phone2 = bundle.getString("phone2","");
            String street = bundle.getString("street","");
            String city = bundle.getString("city","");
            String houseNumber = bundle.getString("houseNumber","");
            mCustomerMobile.setText(customerMobile);
            mCustomerName.setText(customerName);
            mPhone1.setText(phone1);
            mPhone2.setText(phone2);
            mStreet.setText(street);
            mCity.setText(city);
            mHouseNumber.setText(houseNumber);
            populateSelectedItems();
        }

    }

    private void initFields() {
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mOldCustomer = findViewById(R.id.rb_old);
        mNewCustomer = findViewById(R.id.rb_new);
        mCustomerName = findViewById(R.id.et_customer_name);
        mCustomerMobile = findViewById(R.id.et_customer_mobile);
        mPhone1 = findViewById(R.id.et_phone1);
        mPhone2 = findViewById(R.id.et_phone2);
        mStreet = findViewById(R.id.et_street);
        mCity = findViewById(R.id.et_city);
        mHouseNumber = findViewById(R.id.et_house_number);
        mSelectedItems = findViewById(R.id.et_items);
        mAddOrRemoveItems = findViewById(R.id.bt_add_or_remove_items);
        mAddButton = findViewById(R.id.bt_add);
        mHomeButton = findViewById(R.id.bt_home);
        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        dbHelper = new DBHelper(this);
        transactionId = generateNewTid(dbHelper.getReadableDatabase());
        mSelectedItems.setEnabled(false);
        disableCustomerDetails();
    }

    private int generateNewTid(SQLiteDatabase readableDatabase) {
        Bundle bundle= getIntent().getBundleExtra("transactionBundle");
        if(bundle != null){
            return bundle.getInt("tid");
        }
        else{
            return TransactionDao.generateNewTid(readableDatabase);
        }
    }

    private void populateSelectedItems() {
        HashMap<Integer,Integer> items = TransactionUpdateItemDao.getItems(dbHelper.getReadableDatabase(),transactionId);
        if(items.isEmpty()){
            mSelectedItems.setText(getString(R.string.no_items_selected));
        }
        else{
            mSelectedItems.setText("ITEM    ->  QUANTITY X PRICE = AMOUNT\n");
            int total = 0;
            for(int itemId: items.keySet()){
                Item item = ItemDao.getItemById(dbHelper.getReadableDatabase(),itemId);
                mSelectedItems.append(item.getItemName() + " -> " + items.get(itemId) + " X " + item.getPrice() + " = " + items.get(itemId) * item.getPrice()+"\n");
                total += (items.get(itemId) * item.getPrice());
            }
            mSelectedItems.append("TOTAL    =    " + total);
        }
    }

    private void disableCustomerDetails() {
        mCustomerName.setEnabled(false);
        mPhone1.setEnabled(false);
        mPhone2.setEnabled(false);
        mStreet.setEnabled(false);
        mCity.setEnabled(false);
        mHouseNumber.setEnabled(false);
    }

    private void enableCustomerDetails() {
        mCustomerName.setEnabled(true);
        mPhone1.setEnabled(true);
        mPhone2.setEnabled(true);
        mStreet.setEnabled(true);
        mCity.setEnabled(true);
        mHouseNumber.setEnabled(true);
    }
}