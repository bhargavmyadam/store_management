package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.login_page.Entity.Admin;

public class DashBoard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Intent intent = getIntent();
        Admin admin = (Admin)intent.getSerializableExtra("admin");
    }
}