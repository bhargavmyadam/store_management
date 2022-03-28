package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.login_page.DAO.AdminDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;
import com.example.android.login_page.Entity.Admin;

public class MainActivity extends AppCompatActivity {

    Button mRegisterButton;
    Button mLoginButton;
    DBHelper dbHelper;
    EditText mEmail;
    EditText mPassword;
    TextView mWrongCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegisterButton = findViewById(R.id.button_signup);
        mLoginButton = findViewById(R.id.button_signin);
        dbHelper = new DBHelper(this);
        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);
        mWrongCredentials = findViewById(R.id.tv_wrong_credentials);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                Admin admin = AdminDao.getUserDetails(db,email,password);
                if(admin!=null){
                    mWrongCredentials.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(MainActivity.this,DashBoard.class);
                    intent.putExtra("admin",admin);
                    startActivity(intent);
                }
                else{
                    mWrongCredentials.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}