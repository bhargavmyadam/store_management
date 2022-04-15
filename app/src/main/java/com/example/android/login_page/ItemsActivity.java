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
import com.example.android.login_page.Adapters.WorkerAdapter;
import com.example.android.login_page.DAO.ItemDao;
import com.example.android.login_page.DAO.WorkerDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Item;
import com.example.android.login_page.Entity.Worker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemsActivity extends AppCompatActivity {
    Admin admin;
    FloatingActionButton mHomeButton;
    RecyclerView mRecyclerView;
    Item[] items;
    FloatingActionButton mAddButton;
    SearchView mItemSearch;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mHomeButton = findViewById(R.id.bt_home);
        mAddButton = findViewById(R.id.bt_add);
        mRecyclerView = findViewById(R.id.recyclerView);
        mItemSearch = findViewById(R.id.sv_item_name);
        mItemSearch.setSubmitButtonEnabled(true);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mItemSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s.isEmpty()){
                    items = ItemDao.getAllItems(db);
                }
                else{
                    items = ItemDao.getAllItemsByName(db,s);
                }
                ItemAdapter adapter = new ItemAdapter(items,new ItemsActivity.ClickHandler());
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
                ItemAdapter adapter = new ItemAdapter(items,new ItemsActivity.ClickHandler());
                mRecyclerView.setAdapter(adapter);
                return true;
            }
        });
        items = ItemDao.getAllItems(db);
        ItemAdapter adapter = new ItemAdapter(items,new ItemsActivity.ClickHandler());
        mRecyclerView.setAdapter(adapter);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemsActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemsActivity.this,AddItemActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }

    public class ClickHandler implements ItemAdapter.OnClickViewHolder{
        @Override
        public void onClick(Item item) {
            Intent intent = new Intent(ItemsActivity.this,WorkerActivity.class);
            intent.putExtra("item",item);
            intent.putExtra("admin",admin);
            startActivity(intent);
        }
    }
}