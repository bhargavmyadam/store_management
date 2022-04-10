package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Worker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WorkerActivity extends AppCompatActivity {

    EditText mName,mSalary,mPhone1,mPhone2,mStreet,mCity,mHouseNumber;
    Worker worker;
    Admin admin;
    FloatingActionButton mHomeButton;
    Button mRemove;
    Button mEdit;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        initFields();
        populateFields();
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(WorkerActivity.this,DashBoardActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdit.getText().toString().equals(getString(R.string.edit))){
                    mEdit.setText(R.string.done);
                    mRemove.setText(R.string.cancel);
                    enableFields();
                }
                else{
                    mEdit.setText(R.string.edit);
                    mRemove.setText(R.string.remove);
                    populateFields();
                }
            }
        });
    }

    private void enableFields() {
        mName.setEnabled(true);
        mSalary.setEnabled(true);
        mPhone1.setEnabled(true);
        mPhone2.setEnabled(true);
        mStreet.setEnabled(true);
        mCity.setEnabled(true);
        mHouseNumber.setEnabled(true);
    }

    private void populateFields() {
        mName.setText(worker.getWorkerName());
        mSalary.setText(String.valueOf(worker.getWorkerSalary()));
        mPhone1.setText(worker.getPhoneNumbers().get(0));
        mPhone2.setText(worker.getPhoneNumbers().get(1));
        mStreet.setText(worker.getStreet());
        mCity.setText(worker.getCity());
        mHouseNumber.setText(worker.getHouseNumber());
        mName.setEnabled(false);
        mSalary.setEnabled(false);
        mPhone1.setEnabled(false);
        mPhone2.setEnabled(false);
        mStreet.setEnabled(false);
        mCity.setEnabled(false);
        mHouseNumber.setEnabled(false);

    }

    private void initFields() {
        mName = findViewById(R.id.et_fullname);
        mSalary = findViewById(R.id.et_salary);
        mPhone1 = findViewById(R.id.et_phone1);
        mPhone2 = findViewById(R.id.et_phone2);
        mStreet = findViewById(R.id.et_street);
        mCity = findViewById(R.id.et_city);
        mHouseNumber = findViewById(R.id.et_house_number);
        mRemove = findViewById(R.id.bt_remove);
        mEdit = findViewById(R.id.bt_edit);
        mHomeButton = findViewById(R.id.bt_home);
        Intent intent = getIntent();
        worker = (Worker)intent.getSerializableExtra("worker");
        admin = (Admin)intent.getSerializableExtra("admin");
        dbHelper = new DBHelper(this);
    }

}