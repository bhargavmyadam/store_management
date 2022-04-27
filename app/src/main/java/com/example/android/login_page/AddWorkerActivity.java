package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.android.login_page.DAO.WorkerContactDao;
import com.example.android.login_page.DAO.WorkerDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

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
    Button mAddButton;
    TextView mErrorMsg;
    AwesomeValidation mAwesomeValidation;
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
        mAddButton = findViewById(R.id.bt_add);
        mErrorMsg = findViewById(R.id.tv_error_message);
        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields()){
                    ContentValues values = new ContentValues();
                    values.put(WorkerDao.COLUMN_NAME_WORKER_NAME,mWorkerName.getText().toString().toLowerCase());
                    values.put(WorkerDao.COLUMN_NAME_WORKER_SALARY, Integer.parseInt(mSalary.getText().toString()));
                    values.put(WorkerDao.COLUMN_NAME_STREET,mStreet.getText().toString());
                    values.put(WorkerDao.COLUMN_NAME_CITY,mCity.getText().toString());
                    values.put(WorkerDao.COLUMN_NAME_HOUSE_NO,Integer.parseInt(mHouseNumber.getText().toString()));
                    values.put(WorkerDao.COLUMN_NAME_ADMIN_ID,admin.getAdminID());
                    int workerId = WorkerDao.insertValues(db,values);
                    List<String> phoneNumbers = new ArrayList<>();
                    phoneNumbers.add(mPhone1.getText().toString());
                    phoneNumbers.add(mPhone2.getText().toString());
                    WorkerContactDao.insertValues(db,workerId,phoneNumbers);
                    Intent intent = new Intent(AddWorkerActivity.this,WorkersActivity.class);
                    intent.putExtra("admin",admin);
                    startActivity(intent);
                }
            }
        });
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddWorkerActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }

    public boolean validateFields(){
        mAwesomeValidation.addValidation(this,R.id.et_worker_name, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_salary,RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_phone1,RegexTemplate.TELEPHONE,R.string.invalid_mobile);
        mAwesomeValidation.addValidation(this,R.id.et_phone2,RegexTemplate.TELEPHONE,R.string.invalid_mobile);
        mAwesomeValidation.addValidation(this,R.id.et_street,RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_city,RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_house_number,RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        return mAwesomeValidation.validate();
    }
}