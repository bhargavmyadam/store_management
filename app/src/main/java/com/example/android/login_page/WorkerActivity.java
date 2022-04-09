package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Worker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WorkerActivity extends AppCompatActivity {

    TextView mWorkerDetails;
    Worker worker;
    Admin admin;
    FloatingActionButton mHomeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        mWorkerDetails = findViewById(R.id.tv_worker_details);
        mHomeButton = findViewById(R.id.bt_home);
        Intent intent = getIntent();
        worker = (Worker)intent.getSerializableExtra("worker");
        admin = (Admin)intent.getSerializableExtra("admin");
        mWorkerDetails.setText("");
        mWorkerDetails.append(worker.getWorkerName() + "\n");
        mWorkerDetails.append(worker.getWorkerSalary() + "\n");
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(WorkerActivity.this,DashBoardActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });
    }
}