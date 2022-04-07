package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android.login_page.Adapters.WorkerAdapter;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Worker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class WorkersActivity extends AppCompatActivity {

    Admin admin;
    FloatingActionButton mHomeButton;
    RecyclerView mRecyclerView;
    Worker[] workers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mHomeButton = findViewById(R.id.bt_home);
        mRecyclerView = findViewById(R.id.recyclerView);
        Worker worker = new Worker();
        worker.setWorkerId(1);
        worker.setWorkerName("Ramesh");
        worker.setWorkerSalary(20000);
        ArrayList<String> phones = new ArrayList<>();
        phones.add("8008369511");
        worker.setPhoneNumber(phones);
        Worker[] tempWorkers = new Worker[1];
        tempWorkers[0] = worker;
        workers = tempWorkers;
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
    }
}