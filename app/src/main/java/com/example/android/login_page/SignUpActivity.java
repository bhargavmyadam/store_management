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
import com.example.android.login_page.DAO.AdminDao;
import com.example.android.login_page.DataBaseHelper.DBHelper;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    Button mLoginButton;
    EditText mFullName;
    EditText mEmail;
    EditText mPassword;
    EditText mConfirmPassword;
    Button mSignUp;
    TextView mErrorMsg;
    AwesomeValidation mAwesomeValidation;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mLoginButton = findViewById(R.id.bt_already_user);
        mFullName = findViewById(R.id.et_fullname);
        mEmail = findViewById(R.id.userEmailId);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirmPassword);
        mSignUp = findViewById(R.id.signUpBtn);
        mErrorMsg = findViewById(R.id.tv_error_message);
        dbHelper = new DBHelper(this);
        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
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
        mAwesomeValidation.addValidation(this,R.id.et_fullname, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.userEmailId, "^(.+)@(.+)$",R.string.invalid_email);
        mAwesomeValidation.addValidation(this,R.id.password,"^(.{8,32})$",R.string.invalid_password);
        if(mAwesomeValidation.validate()){
            if(mPassword.getText().toString().equals(mConfirmPassword.getText().toString())){
                return true;
            }
            mErrorMsg.setText(R.string.password_match);
            return false;
        }
        return false;
    }
}