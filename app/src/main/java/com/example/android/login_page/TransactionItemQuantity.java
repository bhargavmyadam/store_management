package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.login_page.DAO.TransactionDao;
import com.example.android.login_page.DAO.TransactionUpdateItemDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.Slider;

import java.util.HashMap;

public class TransactionItemQuantity extends AppCompatActivity {
    EditText mAvailabelQuantity;
    Slider mQuantitySlider;
    FloatingActionButton mBackButton;
    Button mAddButton;
    Button mRemoveButton;
    Bundle bundle;
    Admin admin;
    Item item;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_item_quantity);
        mAvailabelQuantity = findViewById(R.id.et_available_qty);
        mQuantitySlider = findViewById(R.id.slider_quantity);
        admin = (Admin)getIntent().getSerializableExtra("admin");
        bundle = getIntent().getBundleExtra("transactionBundle");
        item = (Item)getIntent().getSerializableExtra("item");
        mBackButton = findViewById(R.id.bt_back);
        dbHelper = new DBHelper(this);
        setAvailabelQuantityField();
        configureAddAndRemoveButtons();
        configureSlider();
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransactionItemQuantity.this,AddOrRemoveItemsActivity.class);
                intent.putExtra("admin",admin);
                intent.putExtra("transactionBundle",bundle);
                startActivity(intent);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRemoveButton.isEnabled()){
                    TransactionUpdateItemDao.updateQty(dbHelper.getWritableDatabase(),bundle.getInt("tid"),item.getItemId(),(int)mQuantitySlider.getValue());
                }
                else{
                    TransactionUpdateItemDao.addNewItem(dbHelper.getWritableDatabase(),bundle.getInt("tid"),item.getItemId(),(int)mQuantitySlider.getValue());
                }
                Intent intent = new Intent(TransactionItemQuantity.this,AddOrRemoveItemsActivity.class);
                intent.putExtra("admin",admin);
                intent.putExtra("transactionBundle",bundle);
                startActivity(intent);
            }
        });
        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransactionUpdateItemDao.deleteTransactionItem(dbHelper.getWritableDatabase(),bundle.getInt("tid"),item.getItemId());
                Intent intent = new Intent(TransactionItemQuantity.this,AddOrRemoveItemsActivity.class);
                intent.putExtra("admin",admin);
                intent.putExtra("transactionBundle",bundle);
                startActivity(intent);
            }
        });
    }

    private void setAvailabelQuantityField() {
        HashMap<Integer,Integer> items = TransactionUpdateItemDao.getItems(dbHelper.getReadableDatabase(),bundle.getInt("tid"));
        mAvailabelQuantity.setText(String.valueOf(item.getQuantity()));
    }

    private void configureAddAndRemoveButtons() {
        mAddButton = findViewById(R.id.bt_add);
        mRemoveButton = findViewById(R.id.bt_remove);
        HashMap<Integer,Integer> items = TransactionUpdateItemDao.getItems(dbHelper.getReadableDatabase(),bundle.getInt("tid"));
        if(items.containsKey(item.getItemId())){
            mAddButton.setText(R.string.update_qty);
            mRemoveButton.setEnabled(true);
        }
        else{
            mAddButton.setText(R.string.add_to_transaction);
            mRemoveButton.setEnabled(false);
        }
    }

    private void configureSlider() {
        if (item.getQuantity() != 0) {
            mQuantitySlider.setValueFrom(1);
            mQuantitySlider.setValueTo(item.getQuantity());
            HashMap<Integer,Integer> items = TransactionUpdateItemDao.getItems(dbHelper.getReadableDatabase(),bundle.getInt("tid"));
            mQuantitySlider.setValue(items.getOrDefault(item.getItemId(), 1));
        }
        else{
            mQuantitySlider.setValueFrom(0);
            mQuantitySlider.setValueTo(item.getQuantity());
            mAddButton.setEnabled(false);
            mRemoveButton.setEnabled(false);
        }

    }
}