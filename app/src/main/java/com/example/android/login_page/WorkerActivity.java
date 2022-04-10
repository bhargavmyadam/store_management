package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Worker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WorkerActivity extends AppCompatActivity {

    EditText mName,mSalary,mPhone1,mPhone2,mStreet,mCity,mHouseNumber;
    Worker worker;
    Admin admin;
    FloatingActionButton mHomeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        mHomeButton = findViewById(R.id.bt_home);
        initFields();
        Intent intent = getIntent();
        worker = (Worker)intent.getSerializableExtra("worker");
        admin = (Admin)intent.getSerializableExtra("admin");
        populateFields();
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(WorkerActivity.this,DashBoardActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });
    }

    private void populateFields() {
        mName.setText(worker.getWorkerName());
        mSalary.setText(String.valueOf(worker.getWorkerSalary()));
        mPhone1.setText(worker.getPhoneNumbers().get(0));
        mPhone2.setText(worker.getPhoneNumbers().get(1));
        mStreet.setText(worker.getStreet());
        mCity.setText(worker.getCity());
        mHouseNumber.setText(worker.getHouseNumber());
    }

    private void initFields() {
        mName = findViewById(R.id.et_fullname);
        mSalary = findViewById(R.id.et_salary);
        mPhone1 = findViewById(R.id.et_phone1);
        mPhone2 = findViewById(R.id.et_phone2);
        mStreet = findViewById(R.id.et_street);
        mCity = findViewById(R.id.et_city);
        mHouseNumber = findViewById(R.id.et_house_number);
    }

}