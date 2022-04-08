package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddWorkerActivity extends AppCompatActivity {
    FloatingActionButton mHomeButton;
    Admin admin;
    EditText mWorkerName;
    EditText mSalary;
    EditText mPhone1;
    EditText mPhone2;
    EditText mStreet;
    EditText mCity;
    EditText mHouseNumber;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);
        mHomeButton = findViewById(R.id.bt_home);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mWorkerName = findViewById(R.id.et_worker_name);
        mSalary = findViewById(R.id.et_salary);
        mPhone1 = findViewById(R.id.et_phone1);
        mPhone2 = findViewById(R.id.et_phone2);
        mStreet = findViewById(R.id.et_street);
        mCity = findViewById(R.id.et_city);
        mHouseNumber = findViewById(R.id.et_house_number);
        dbHelper = new DBHelper(this);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddWorkerActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }
}