package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.login_page.Entity.Admin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MiscellaneousFunctionsActivity extends AppCompatActivity {
    Button mItemLog;
    Button mSales;
    FloatingActionButton mHomeButton;
    Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscellaneous_functions);
        mItemLog = findViewById(R.id.bt_item_log);
        mSales = findViewById(R.id.bt_sales);
        admin = (Admin)getIntent().getSerializableExtra("admin");
        mHomeButton = findViewById(R.id.bt_home);
        mItemLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MiscellaneousFunctionsActivity.this,AdminItemLogActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
        mSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MiscellaneousFunctionsActivity.this,DashBoardActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });

    }
}