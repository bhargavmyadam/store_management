package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.android.login_page.Entity.Admin;

public class DashBoardActivity extends AppCompatActivity {
    Admin admin;
    LinearLayout mAdminDetails;
    LinearLayout mWorkers;
    LinearLayout mCustomers;
    LinearLayout mItems;
    Button mLogoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mAdminDetails = findViewById(R.id.ll_admin_details);
        mWorkers = findViewById(R.id.ll_workers);
        mCustomers = findViewById(R.id.ll_customers);
        mItems = findViewById(R.id.ll_items);
        mLogoutButton = findViewById(R.id.bt_logout);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DashBoardActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        mAdminDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this,AdminActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
        mWorkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this,WorkersActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
        mCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DashBoardActivity.this,CustomersActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });
        mItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DashBoardActivity.this,ItemsActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });

    }
}