package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android.login_page.Entity.Admin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WorkersActivity extends AppCompatActivity {

    Admin admin;
    FloatingActionButton mHomeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mHomeButton = findViewById(R.id.bt_home);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkersActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }
}