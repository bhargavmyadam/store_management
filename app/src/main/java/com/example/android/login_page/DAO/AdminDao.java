package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.android.login_page.Entity.Admin;

public class AdminDao implements BaseColumns {
    public static final String TABLE_NAME = "ADMIN";
    public static final String COLUMN_NAME_ADMIN_ID = "ADMIN_ID";
    public static final String COLUMN_NAME_EMAIL = "EMAIL";
    public static final String COLUMN_NAME_PASSWORD = "PASSWORD";
    public static final String COLUMN_NAME_ADMIN_NAME = "ADMIN_NAME";
    public static final String SQL_CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(" + COLUMN_NAME_ADMIN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_EMAIL + " TEXT," + COLUMN_NAME_PASSWORD + " TEXT, "+ COLUMN_NAME_ADMIN_NAME + " TEXT)";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static boolean insertValues(SQLiteDatabase db, ContentValues values){
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        int newAdminID = cursor.getCount()+1;
        values.put(COLUMN_NAME_ADMIN_ID,newAdminID);
        long newRow = db.insert(TABLE_NAME,null,values);
        return newRow != -1;
    }

    public static Admin getUserDetails(SQLiteDatabase db, String email, String password){
        String selection = COLUMN_NAME_EMAIL + " = ? " + "AND " + COLUMN_NAME_PASSWORD + " = ? ";
        String[] selectionArgs = {email,password};
        Cursor cursor = (db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null));
        if(cursor.getCount() == 0){
            return null;
        }
        else{
            cursor.moveToNext();
            Admin admin = new Admin();
            admin.setAdminID(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ADMIN_ID)));
            admin.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_EMAIL)));
            admin.setAdminName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ADMIN_NAME)));
            admin.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_PASSWORD)));
            return admin;
        }
    }

    public static String getAdminName(SQLiteDatabase db, int adminId) {
        String selection = COLUMN_NAME_ADMIN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(adminId)};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        cursor.moveToNext();
        return cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ADMIN_NAME));
    }

    public static void updateDataBase(SQLiteDatabase writableDatabase, Admin admin) {
        String selection = COLUMN_NAME_ADMIN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(admin.getAdminID())};
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_EMAIL,admin.getEmail());
        values.put(COLUMN_NAME_ADMIN_NAME,admin.getAdminName());
        writableDatabase.update(TABLE_NAME,values,selection,selectionArgs);
    }

    public static String getAdminEmail(SQLiteDatabase db, int adminId) {
        String selection = COLUMN_NAME_ADMIN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(adminId)};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        cursor.moveToNext();
        return cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_EMAIL));
    }
}
