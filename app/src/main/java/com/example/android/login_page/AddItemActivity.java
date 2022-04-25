package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.android.login_page.DAO.ItemDao;
import com.example.android.login_page.DAO.WorkerContactDao;
import com.example.android.login_page.DAO.WorkerDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;
import com.example.android.login_page.Entity.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {
    FloatingActionButton mHomeButton;
    Admin admin;
    EditText mItemName;
    EditText mPrice;
    EditText mSize;
    EditText mQuantity;
    EditText mBrand;
    EditText mCategory;
    Button mAddButton;
    TextView mErrorMsg;
    AwesomeValidation mAwesomeValidation;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mHomeButton = findViewById(R.id.bt_home);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        mItemName = findViewById(R.id.et_item_name);
        mPrice = findViewById(R.id.et_price);
        mSize = findViewById(R.id.et_size);
        mQuantity = findViewById(R.id.et_quantity);
        mBrand = findViewById(R.id.et_brand);
        mCategory = findViewById(R.id.et_category);
        mAddButton = findViewById(R.id.bt_add);
        mErrorMsg = findViewById(R.id.tv_error_message);
        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields()){
                    Item item = new Item(-1,mItemName.getText().toString(),mBrand.getText().toString(),Integer.parseInt(mPrice.getText().toString()),mCategory.getText().toString(),Integer.parseInt(mQuantity.getText().toString()),Integer.parseInt(mSize.getText().toString()));
                    ItemDao.addItem(db,item,admin.getAdminID());
                    Intent intent = new Intent(AddItemActivity.this,ItemsActivity.class);
                    intent.putExtra("admin",admin);
                    startActivity(intent);
                }
            }
        });


        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddItemActivity.this,DashBoardActivity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
    }

    private boolean validateFields() {
        mAwesomeValidation.addValidation(this,R.id.et_item_name, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_price, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_size, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_quantity, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_brand, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_category, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        return mAwesomeValidation.validate();
    }
}