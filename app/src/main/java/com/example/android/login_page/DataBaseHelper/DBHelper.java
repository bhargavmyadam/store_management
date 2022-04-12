package com.example.android.login_page.DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.login_page.DAO.AdminDao;
import com.example.android.login_page.DAO.WorkerContactDao;
import com.example.android.login_page.DAO.WorkerDao;

import java.io.Serializable;

public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "StoreManagement.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(AdminDao.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(WorkerDao.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(WorkerContactDao.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(AdminDao.SQL_DELETE_TABLE);
        sqLiteDatabase.execSQL(WorkerDao.SQL_DELETE_TABLE);
        sqLiteDatabase.execSQL(WorkerContactDao.SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
