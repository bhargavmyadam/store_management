package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.android.login_page.Adapters.AdminItemAdapter;
import com.example.android.login_page.Adapters.CustomerAdapter;
import com.example.android.login_page.DAO.AdminAddItemDao;
import com.example.android.login_page.DAO.CustomerDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.AdminItem;
import com.example.android.login_page.Entity.Customer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdminItemLogActivity extends AppCompatActivity {
    Admin admin;
    FloatingActionButton mHomeButton;
    RecyclerView mRecyclerView;
    AdminItem[] itemLog;
    SearchView mDateSearch;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_item_log);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mHomeButton = findViewById(R.id.bt_home);
        mRecyclerView = findViewById(R.id.recyclerView);
        mDateSearch = findViewById(R.id.sv_date);
        mDateSearch.setSubmitButtonEnabled(true);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDateSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s.isEmpty()){
                    itemLog = AdminAddItemDao.getFullLog(db);
                }
                else{
                    itemLog = AdminAddItemDao.getLogByDate(db,s);
                }
                AdminItemAdapter adapter = new AdminItemAdapter(itemLog,db);
                mRecyclerView.setAdapter(adapter);
                mDateSearch.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty()){
                    itemLog = AdminAddItemDao.getFullLog(db);
                }
                else{
                    itemLog = AdminAddItemDao.getLogByDate(db,s);
                }
                AdminItemAdapter adapter = new AdminItemAdapter(itemLog,db);
                mRecyclerView.setAdapter(adapter);
                return true;
            }
        });
        itemLog = AdminAddItemDao.getFullLog(db);
        AdminItemAdapter adapter = new AdminItemAdapter(itemLog,db);
        mRecyclerView.setAdapter(adapter);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminItemLogActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }
}