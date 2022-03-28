package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.login_page.DAO.AdminDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;

public class SignUpActivity extends AppCompatActivity {

    Button mLoginButton;
    EditText mFullName;
    EditText mEmail;
    EditText mPassword;
    EditText mConfirmPassword;
    Button mSignUp;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mLoginButton = findViewById(R.id.bt_already_user);
        mFullName = findViewById(R.id.et_fullname);
        mEmail = findViewById(R.id.userEmailId);
        mPassword = findViewById(R.id.et_password);
        mConfirmPassword = findViewById(R.id.confirmPassword);
        mSignUp = findViewById(R.id.signUpBtn);
        dbHelper = new DBHelper(this);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields()){
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(AdminDao.COLUMN_NAME_EMAIL,mEmail.getText().toString());
                    values.put(AdminDao.COLUMN_NAME_ADMIN_NAME,mFullName.getText().toString());
                    values.put(AdminDao.COLUMN_NAME_PASSWORD,mConfirmPassword.getText().toString());
                    AdminDao.insertValues(db,values);
                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean validateFields(){
        return true;
    }
}