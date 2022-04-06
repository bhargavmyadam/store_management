package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.login_page.Entity.Admin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class AdminActivity extends AppCompatActivity {
    TextView mAdminName;
    TextView mAdminEmail;
    Admin admin;
    FloatingActionButton mHomeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mAdminName = findViewById(R.id.tv_admin_name_value);
        mAdminEmail = findViewById(R.id.tv_admin_email_value);
        mHomeButton = findViewById(R.id.bt_home);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AdminActivity.this,DashBoardActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });
        mAdminName.setText(admin.getAdminName());
        mAdminEmail.setText(admin.getEmail());
    }
}