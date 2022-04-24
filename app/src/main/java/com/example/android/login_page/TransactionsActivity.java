package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.android.login_page.Adapters.TransactionAdapter;
import com.example.android.login_page.Adapters.WorkerAdapter;
import com.example.android.login_page.DAO.TransactionDao;
import com.example.android.login_page.DAO.WorkerDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Transaction;
import com.example.android.login_page.Entity.Worker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TransactionsActivity extends AppCompatActivity {
    Admin admin;
    FloatingActionButton mHomeButton;
    RecyclerView mRecyclerView;
    Transaction[] transactions;
    FloatingActionButton mAddButton;
    SearchView mTransactionSearch;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mHomeButton = findViewById(R.id.bt_home);
        mAddButton = findViewById(R.id.bt_add);
        mRecyclerView = findViewById(R.id.recyclerView);
        mTransactionSearch = findViewById(R.id.sv_transaction_date);
        mTransactionSearch.setSubmitButtonEnabled(true);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mTransactionSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s.isEmpty()){
                    transactions = TransactionDao.getAllTransactions(db);
                }
                else{
                    transactions = TransactionDao.getAllTransactionsByDate(db,s);
                }
                TransactionAdapter adapter = new TransactionAdapter(transactions,new ClickHandler(),db);
                mRecyclerView.setAdapter(adapter);
                mTransactionSearch.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty()){
                    transactions = TransactionDao.getAllTransactions(db);
                }
                else{
                    transactions = TransactionDao.getAllTransactionsByDate(db,s);
                }
                TransactionAdapter adapter = new TransactionAdapter(transactions,new ClickHandler(),db);
                mRecyclerView.setAdapter(adapter);
                return true;
            }
        });
        transactions = TransactionDao.getAllTransactions(db);
        TransactionAdapter adapter = new TransactionAdapter(transactions,new ClickHandler(),db);
        mRecyclerView.setAdapter(adapter);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransactionsActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransactionsActivity.this,AddTransactionActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }
    public class ClickHandler implements TransactionAdapter.OnClickViewHolder{
        @Override
        public void onClick(Transaction transaction) {
            Intent intent = new Intent(TransactionsActivity.this,TransactionActivity.class);
            intent.putExtra("transaction",transaction);
            intent.putExtra("admin",admin);
            startActivity(intent);
        }
    }
}