package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.basgeekball.awesomevalidation.utility.custom.SimpleCustomValidation;
import com.example.android.login_page.DAO.AdminAddItemDao;
import com.example.android.login_page.DAO.ItemDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemActivity extends AppCompatActivity {
    EditText mName,mPrice,mQuantity,mSize,mBrand,mCategory,mAddQuantity,mRemoveQuantity;
    Item item;
    Admin admin;
    FloatingActionButton mHomeButton;
    Button mEdit;
    Button mRemove;
    AwesomeValidation mAwesomeValidation;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        initFields();
        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        populateFields();
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ItemActivity.this,DashBoardActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdit.getText().toString().equals(getString(R.string.edit))){
                    mEdit.setText(R.string.done);
                    mRemove.setText(R.string.cancel);
                    mAddQuantity.setEnabled(true);
                    mRemoveQuantity.setEnabled(true);
                    mPrice.setEnabled(true);
                }
                else{
                    if(validateFields()){
                        updateDatabase();
                        mEdit.setText(R.string.edit);
                        mRemove.setText(R.string.remove);
                        mAddQuantity.setEnabled(false);
                        mRemoveQuantity.setEnabled(false);
                        mPrice.setEnabled(false);
                        populateFields();
                    }

                }
            }
        });
        mRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRemove.getText().toString().equals(getString(R.string.remove))){
                    ItemDao.removeItemStock(dbHelper.getWritableDatabase(),item.getItemId());
                    AdminAddItemDao.removeItem(dbHelper.getWritableDatabase(),item.getItemId(),item.getQuantity());
                    Intent intent = new Intent(ItemActivity.this,ItemsActivity.class);
                    intent.putExtra("admin",admin);
                    startActivity(intent);
                }
                else{
                    mEdit.setText(R.string.edit);
                    mRemove.setText(R.string.remove);
                    populateFields();
                }
            }
        });
    }

    private void updateDatabase() {
        ItemDao.updateDatabase(dbHelper.getWritableDatabase(),item.getItemId(),admin.getAdminID(),Integer.parseInt(mAddQuantity.getText().toString()) - Integer.parseInt(mRemoveQuantity.getText().toString()),Integer.parseInt(mPrice.getText().toString()));
        item.setQuantity(item.getQuantity() + Integer.parseInt(mAddQuantity.getText().toString())- Integer.parseInt(mRemoveQuantity.getText().toString()));
        item.setPrice(Integer.parseInt(mPrice.getText().toString()));
    }

    private boolean validateFields() {
        mAwesomeValidation.addValidation(this,R.id.et_add_quantity, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_price, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this, R.id.et_remove_quantity, new SimpleCustomValidation() {
            @Override
            public boolean compare(String s) {
                return (item.getQuantity() + Integer.parseInt(mAddQuantity.getText().toString()) - Integer.parseInt(s)) >= 0;
            }
        },R.string.invalid_quantity);
        return mAwesomeValidation.validate();
    }

    private void populateFields() {
        mAddQuantity.setText("0");
        mRemoveQuantity.setText("0");
        mName.setText(item.getItemName());
        mPrice.setText(String.valueOf(item.getPrice()));
        mQuantity.setText(String.valueOf(item.getQuantity()));
        mSize.setText(String.valueOf(item.getSize()));
        mBrand.setText(item.getBrand());
        mCategory.setText(item.getCategory());
        mAddQuantity.setEnabled(false);
        mRemoveQuantity.setEnabled(false);
        mName.setEnabled(false);
        mPrice.setEnabled(false);
        mQuantity.setEnabled(false);
        mSize.setEnabled(false);
        mBrand.setEnabled(false);
        mCategory.setEnabled(false);
    }

    private void initFields() {
        mAddQuantity = findViewById(R.id.et_add_quantity);
        mRemoveQuantity = findViewById(R.id.et_remove_quantity);
        mName = findViewById(R.id.et_name);
        mPrice = findViewById(R.id.et_price);
        mQuantity = findViewById(R.id.et_quantity);
        mSize = findViewById(R.id.et_size);
        mBrand = findViewById(R.id.et_brand);
        mCategory = findViewById(R.id.et_category);
        mEdit = findViewById(R.id.bt_edit);
        mRemove = findViewById(R.id.bt_remove);
        mHomeButton = findViewById(R.id.bt_home);
        Intent intent = getIntent();
        item = (Item)intent.getSerializableExtra("item");
        admin = (Admin)intent.getSerializableExtra("admin");
        dbHelper = new DBHelper(this);
    }
}