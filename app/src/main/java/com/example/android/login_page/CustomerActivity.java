package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.android.login_page.DAO.AdminDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Customer;
import com.example.android.login_page.Entity.Worker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustomerActivity extends AppCompatActivity {
    EditText mName,mVisits,mPhone1,mPhone2,mStreet,mCity,mHouseNumber;
    Customer customer;
    Admin admin;
    FloatingActionButton mHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        initFields();
        populateFields();
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CustomerActivity.this,DashBoardActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });
    }

    private void populateFields() {
        mName.setText(customer.getCustomerName());
        mVisits.setText(String.valueOf(customer.getNumberOfVisits()));
        mPhone1.setText(customer.getPhoneNumbers().get(0));
        mPhone2.setText(customer.getPhoneNumbers().get(1));
        mStreet.setText(customer.getStreet());
        mCity.setText(customer.getCity());
        mHouseNumber.setText(customer.getHouseNumber());
        mName.setEnabled(false);
        mVisits.setEnabled(false);
        mPhone1.setEnabled(false);
        mPhone2.setEnabled(false);
        mStreet.setEnabled(false);
        mCity.setEnabled(false);
        mHouseNumber.setEnabled(false);
    }

    private void initFields() {
        mName = findViewById(R.id.et_fullname);
        mVisits = findViewById(R.id.et_visits);
        mPhone1 = findViewById(R.id.et_phone1);
        mPhone2 = findViewById(R.id.et_phone2);
        mStreet = findViewById(R.id.et_street);
        mCity = findViewById(R.id.et_city);
        mHouseNumber = findViewById(R.id.et_house_number);
        mHomeButton = findViewById(R.id.bt_home);
        Intent intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("customer");
        admin = (Admin)intent.getSerializableExtra("admin");
    }
}