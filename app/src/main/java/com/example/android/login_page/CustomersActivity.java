package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.android.login_page.Adapters.CustomerAdapter;
import com.example.android.login_page.Adapters.WorkerAdapter;
import com.example.android.login_page.DAO.CustomerDao;
import com.example.android.login_page.DAO.WorkerDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Customer;
import com.example.android.login_page.Entity.Worker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustomersActivity extends AppCompatActivity {

    Admin admin;
    FloatingActionButton mHomeButton;
    RecyclerView mRecyclerView;
    Customer[] customers;
    SearchView mCustomerSearch;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mHomeButton = findViewById(R.id.bt_home);
        mRecyclerView = findViewById(R.id.recyclerView);
        mCustomerSearch = findViewById(R.id.sv_customer_name);
        mCustomerSearch.setSubmitButtonEnabled(true);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mCustomerSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s.isEmpty()){
                    customers = CustomerDao.getAllCustomers(db);
                }
                else{
                    customers = CustomerDao.getAllCustomersByName(db,s);
                }
                CustomerAdapter adapter = new CustomerAdapter(customers,new ClickHandler());
                mRecyclerView.setAdapter(adapter);
                mCustomerSearch.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty()){
                    customers = CustomerDao.getAllCustomers(db);
                }
                else{
                    customers = CustomerDao.getAllCustomersByName(db,s);
                }
                CustomerAdapter adapter = new CustomerAdapter(customers,new ClickHandler());
                mRecyclerView.setAdapter(adapter);
                return true;
            }
        });
        customers = CustomerDao.getAllCustomers(db);
        CustomerAdapter adapter = new CustomerAdapter(customers,new ClickHandler());
        mRecyclerView.setAdapter(adapter);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomersActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }


    public class ClickHandler implements CustomerAdapter.OnClickViewHolder{
        @Override
        public void onClick(Customer customer) {
            Intent intent = new Intent(CustomersActivity.this,WorkerActivity.class);
            intent.putExtra("customer",customer);
            intent.putExtra("admin",admin);
            startActivity(intent);
        }
    }
}