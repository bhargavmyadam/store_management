package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class CustomerContactDao implements BaseColumns {
    public static final String TABLE_NAME="CUSTOMER_CONTACT";
    public static final String COLUMN_NAME_CONTACT_NUM="CONTACT_NUM";
    public static final String COLUMN_NAME_CUSTOMER_ID="CUSTOMER_ID";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_CUSTOMER_ID+" INTEGER,"+
            COLUMN_NAME_CONTACT_NUM+" TEXT,PRIMARY KEY("+COLUMN_NAME_CUSTOMER_ID+","+COLUMN_NAME_CONTACT_NUM
            +"),FOREIGN KEY("+COLUMN_NAME_CUSTOMER_ID+") REFERENCES "+ CustomerDao.TABLE_NAME + "("+ CustomerDao.COLUMN_NAME_CUSTOMER_ID+"))";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static List<String> getPhoneNumbers(SQLiteDatabase db, int id) {
        String selection = COLUMN_NAME_CUSTOMER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        List<String> phoneNos = new ArrayList<>();
        while (cursor.moveToNext()){
            phoneNos.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CONTACT_NUM)));
        }
        return phoneNos;
    }


    public static int getCustomerId(SQLiteDatabase readableDatabase,String mobile) {
        String selection = COLUMN_NAME_CONTACT_NUM + " = ?";
        String[] selectionArgs = {mobile};
        Cursor cursor = readableDatabase.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        cursor.moveToNext();
        return cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CUSTOMER_ID));
    }

    public static void addNewContact(SQLiteDatabase writableDatabase, int customerId, ArrayList<String> phoneNumbers) {
        for(String phone : phoneNumbers){
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_CUSTOMER_ID,customerId);
            values.put(COLUMN_NAME_CONTACT_NUM,phone);
            writableDatabase.insert(TABLE_NAME,null,values);
        }
    }

    public static boolean customerMobileExists(SQLiteDatabase readableDatabase, String mobile) {
        String selection = COLUMN_NAME_CONTACT_NUM + " = ?";
        String[] selectionArgs = {mobile};
        Cursor cursor = readableDatabase.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        return cursor.getCount() != 0;
    }
}
