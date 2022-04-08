package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.android.login_page.Adapters.WorkerAdapter;
import com.example.android.login_page.DAO.WorkerDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Worker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class WorkersActivity extends AppCompatActivity {

    Admin admin;
    FloatingActionButton mHomeButton;
    RecyclerView mRecyclerView;
    Worker[] workers;
    FloatingActionButton mAddButton;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mHomeButton = findViewById(R.id.bt_home);
        mAddButton = findViewById(R.id.bt_add);
        mRecyclerView = findViewById(R.id.recyclerView);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        workers = WorkerDao.getAllWorkers(db);
        WorkerAdapter adapter = new WorkerAdapter(workers);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setAdapter(adapter);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkersActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkersActivity.this,AddWorkerActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }
}