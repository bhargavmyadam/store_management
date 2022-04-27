package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.basgeekball.awesomevalidation.utility.custom.SimpleCustomValidation;
import com.example.android.login_page.Adapters.AdminItemAdapter;
import com.example.android.login_page.Adapters.SalesAdapter;
import com.example.android.login_page.DAO.AdminAddItemDao;
import com.example.android.login_page.DAO.TransactionUpdateItemDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.SalesItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalesActivity extends AppCompatActivity {
    Admin admin;
    FloatingActionButton mHomeButton;
    RecyclerView mRecyclerView;
    SalesItem[] salesItems;
    SearchView mItemSearch;
    EditText mStartDate;
    EditText mEndDate;
    DBHelper dbHelper;
    AwesomeValidation mAwesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mHomeButton = findViewById(R.id.bt_home);
        mRecyclerView = findViewById(R.id.recyclerView);
        mItemSearch = findViewById(R.id.sv_item_name);
        mStartDate = findViewById(R.id.et_start_date);
        mEndDate = findViewById(R.id.et_end_date);
        mItemSearch.setSubmitButtonEnabled(true);
        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mItemSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(validateDates()){
                    if(s.isEmpty()){
                        salesItems = TransactionUpdateItemDao.getSales(db,mStartDate.getText().toString(),mEndDate.getText().toString());
                    }
                    else{
                        salesItems = TransactionUpdateItemDao.getSalesByItemName(db,s,mStartDate.getText().toString(),mEndDate.getText().toString());
                    }
                    Toast.makeText(SalesActivity.this,String.valueOf(salesItems.length),Toast.LENGTH_LONG).show();
                    SalesAdapter adapter = new SalesAdapter(salesItems,db);
                    mRecyclerView.setAdapter(adapter);
                }
                mItemSearch.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(validateDates()){
                    if(s.isEmpty()){
                        salesItems = TransactionUpdateItemDao.getSales(db,mStartDate.getText().toString(),mEndDate.getText().toString());
                    }
                    else{
                        salesItems = TransactionUpdateItemDao.getSalesByItemName(db,s,mStartDate.getText().toString(),mEndDate.getText().toString());
                    }
                    SalesAdapter adapter = new SalesAdapter(salesItems,db);
                    mRecyclerView.setAdapter(adapter);
                }
                return true;
            }
        });
        validateDates();
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }

    private boolean validateDates() {
        mAwesomeValidation.addValidation(this, R.id.et_start_date, new SimpleCustomValidation() {
            @Override
            public boolean compare(String s) {
                return isValidDate(s);
            }
        }, R.string.invalid_date);
        if(mAwesomeValidation.validate()){
            mAwesomeValidation.addValidation(this, R.id.et_end_date, new SimpleCustomValidation() {
                @Override
                public boolean compare(String s) {
                    return isValidDate(s) && LocalDate.parse(s).isAfter(LocalDate.parse(mStartDate.getText().toString()));
                }
            }, R.string.invalid_date);
            return mAwesomeValidation.validate();
        }
        return false;
    }

    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}