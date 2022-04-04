package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.login_page.Entity.Admin;

public class AdminActivity extends AppCompatActivity {
    TextView mAdminDetails;
    Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mAdminDetails = findViewById(R.id.tv_admin_details);
        mAdminDetails.setText("");
        mAdminDetails.append(admin.getAdminName() + "\n");
        mAdminDetails.append(admin.getEmail());
    }
}