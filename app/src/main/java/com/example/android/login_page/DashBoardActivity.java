package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.android.login_page.Entity.Admin;

public class DashBoardActivity extends AppCompatActivity {
    Admin admin;
    LinearLayout mAdminDetails;
    LinearLayout mWorkers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mAdminDetails = findViewById(R.id.ll_admin_details);
        mWorkers = findViewById(R.id.ll_workers);
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
    }
}