package com.example.android.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.android.login_page.Entity.Admin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class AdminActivity extends AppCompatActivity {
    EditText mAdminName;
    EditText mAdminEmail;
    Button mEdit;
    Admin admin;
    FloatingActionButton mHomeButton;
    AwesomeValidation mAwesomeValidation;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Intent intent = getIntent();
        admin = (Admin)intent.getSerializableExtra("admin");
        dbHelper = new DBHelper(this);
        mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        mEdit = findViewById(R.id.bt_edit);
        mAdminName = findViewById(R.id.et_name);
        mAdminEmail = findViewById(R.id.et_email);
        mAdminName.setText(admin.getAdminName());
        mAdminEmail.setText(admin.getEmail());
        mAdminEmail.setEnabled(false);
        mAdminName.setEnabled(false);
        mHomeButton = findViewById(R.id.bt_home);
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AdminActivity.this,DashBoardActivity.class);
                intent1.putExtra("admin",admin);
                startActivity(intent1);
            }
        });
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdit.getText().toString().equals(getString(R.string.edit))){
                    mEdit.setText(R.string.done);
                    mAdminName.setEnabled(true);
                    mAdminEmail.setEnabled(true);
                }
                else{
                    if(validateFields()){
                        updateDatabase();
                        mEdit.setText(R.string.edit);
                        mAdminName.setEnabled(false);
                        mAdminEmail.setEnabled(false);
                        mAdminName.setText(admin.getAdminName());
                        mAdminEmail.setText(admin.getEmail());
                    }

                }
            }
        });
    }

    private boolean validateFields() {
        mAwesomeValidation.addValidation(this,R.id.et_name, RegexTemplate.NOT_EMPTY,R.string.empty_fields);
        mAwesomeValidation.addValidation(this,R.id.et_email, "^(.+)@(.+)$",R.string.invalid_email);
        return  mAwesomeValidation.validate();
    }

    private void updateDatabase() {
        admin.setAdminName(mAdminName.getText().toString());
        admin.setEmail(mAdminEmail.getText().toString());
        AdminDao.updateDataBase(dbHelper.getWritableDatabase(),admin);
    }
}