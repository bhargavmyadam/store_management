package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.android.login_page.Adapters.ItemAdapter;
import com.example.android.login_page.DAO.ItemDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddOrRemoveItemsActivity extends AppCompatActivity {
    Admin admin;
    FloatingActionButton mBackButton;
    RecyclerView mRecyclerView;
    Item[] items;
    SearchView mItemSearch;
    DBHelper dbHelper;
    Bundle transactionBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_remove_items);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mBackButton = findViewById(R.id.bt_back);
        mRecyclerView = findViewById(R.id.recyclerView);
        mItemSearch = findViewById(R.id.sv_item_name);
        mItemSearch.setSubmitButtonEnabled(true);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        transactionBundle = getIntent().getBundleExtra("transactionBundle");
        mItemSearch.

                setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s.isEmpty()){
                    items = ItemDao.getAllItems(db);
                }
                else{
                    items = ItemDao.getAllItemsByName(db,s);
                }
                ItemAdapter adapter = new ItemAdapter(items,new ClickHandler());
                mRecyclerView.setAdapter(adapter);
                mItemSearch.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty()){
                    items = ItemDao.getAllItems(db);
                }
                else{
                    items = ItemDao.getAllItemsByName(db,s);
                }
                ItemAdapter adapter = new ItemAdapter(items,new ClickHandler());
                mRecyclerView.setAdapter(adapter);
                return true;
            }
        });
        items = ItemDao.getAllItems(db);
        ItemAdapter adapter = new ItemAdapter(items,new ClickHandler());
        mRecyclerView.setAdapter(adapter);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddOrRemoveItemsActivity.this,AddTransactionActivity.class);
                intent.putExtra("admin",admin);
                intent.putExtra("transactionBundle",transactionBundle);
                startActivity(intent);
            }
        });
    }

    public class ClickHandler implements ItemAdapter.OnClickViewHolder{
        @Override
        public void onClick(Item item) {
            Intent intent = new Intent(AddOrRemoveItemsActivity.this,TransactionItemQuantity.class);
            intent.putExtra("item",item);
            intent.putExtra("admin",admin);
            intent.putExtra("transactionBundle",transactionBundle);
            startActivity(intent);
        }
    }
}