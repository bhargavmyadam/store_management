package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class WorkerContactDao implements BaseColumns {
    public  static final String TABLE_NAME="WORKER_CONTACT";
    public  static final String COLUMN_NAME_WORKER_ID = "WORKER_ID";
    public  static final String COLUMN_NAME_CONTACT_NUM="PHONE_NUMBER";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME
            +"("+COLUMN_NAME_WORKER_ID+" INTEGER,"+COLUMN_NAME_CONTACT_NUM
            +" TEXT, FOREIGN KEY ("+COLUMN_NAME_WORKER_ID+") REFERENCES "
            +WorkerDao.TABLE_NAME+"("+WorkerDao.COLUMN_NAME_WORKER_ID+")"+
            ")";
    public  static final String SQL_DELETE_TABLE="DROP TABLE IF EXISTS " + TABLE_NAME;


    public static final boolean insertValues(SQLiteDatabase db, int workerId, List<String> phoneNumbers){
        for(String phone : phoneNumbers){
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_WORKER_ID,workerId);
            values.put(COLUMN_NAME_CONTACT_NUM,phone);
            long newRow = db.insert(TABLE_NAME,null,values);
            if(newRow == -1){
                return false;
            }
        }
        return true;
    }

    public static List<String> getPhoneNumbers(SQLiteDatabase db,int id){
        String selection = COLUMN_NAME_WORKER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        List<String> phoneNos = new ArrayList<>();
        while (cursor.moveToNext()){
            phoneNos.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CONTACT_NUM)));
        }
        return phoneNos;

    }
}
