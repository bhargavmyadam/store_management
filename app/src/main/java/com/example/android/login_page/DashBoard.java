package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.login_page.Entity.Admin;

public class DashBoard extends AppCompatActivity {

    TextView mUserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mUserDetails = findViewById(R.id.tv_user_datails);
        Intent intent = getIntent();
        Admin admin = (Admin)intent.getSerializableExtra("admin");
//        String username = intent.getStringExtra("username");
//        String phone = intent.getStringExtra("phone");
//        String email = intent.getStringExtra("email");
//        String location = intent.getStringExtra("location");
        mUserDetails.setText("");
        mUserDetails.append(admin.getAdminName()+"\n"+admin.getEmail() + "\n");
    }
}