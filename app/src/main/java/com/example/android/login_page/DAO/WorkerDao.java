package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.android.login_page.Entity.Worker;

import java.util.ArrayList;
import java.util.List;

public class WorkerDao implements BaseColumns {
    public static final String TABLE_NAME = "WORKER";
    public static final String COLUMN_NAME_WORKER_ID = "WORKER_ID";
    public static final String COLUMN_NAME_WORKER_NAME = "WORKER_NAME";
    public static final String COLUMN_NAME_WORKER_SALARY = "WORKER_SALARY";
    public static final String COLUMN_NAME_STREET = "STREET";
    public static final String COLUMN_NAME_CITY = "CITY";
    public static final String COLUMN_NAME_HOUSE_NO = "HOUSE_NO";
    public static final String COLUMN_NAME_ADMIN_ID = "ADMIN_ID";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(" + COLUMN_NAME_WORKER_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_WORKER_NAME + " TEXT," + COLUMN_NAME_WORKER_SALARY + " INTEGER,"+COLUMN_NAME_STREET + " TEXT," + COLUMN_NAME_CITY + " TEXT,"
            + COLUMN_NAME_HOUSE_NO + " INTEGER,"+ COLUMN_NAME_ADMIN_ID + " INTEGER, FOREIGN KEY (" + COLUMN_NAME_ADMIN_ID + ") REFERENCES " +
            AdminDao.TABLE_NAME + "(" + AdminDao.COLUMN_NAME_ADMIN_ID + "))";

    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static int insertValues(SQLiteDatabase db, ContentValues values){
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        int newWorkerID = cursor.getCount()+1;
        values.put(COLUMN_NAME_WORKER_ID,newWorkerID);
        long newRow = db.insert(TABLE_NAME,null,values);
        return newWorkerID;
    }

    public static Worker[] getAllWorkers(SQLiteDatabase db){
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        List<Worker> workerList = new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_WORKER_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_WORKER_NAME));
            int salary = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_WORKER_SALARY));
            String street = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_STREET));
            String city = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CITY));
            int houseNo = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOUSE_NO));
            int adminId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ADMIN_ID));
            List<String> phoneNumbers = WorkerContactDao.getPhoneNumbers(db,id);
            Worker worker = new Worker(id,name,salary,street,city,String.valueOf(houseNo),(ArrayList<String>) phoneNumbers);
            workerList.add(worker);
        }
        Worker[] workers = new Worker[workerList.size()];
        for (int i = 0; i < workerList.size(); i++) {
            workers[i] = workerList.get(i);
        }
        return workers;
    }
}
