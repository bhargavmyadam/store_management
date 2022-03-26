package com.example.android.login_page.DAO;

import android.provider.BaseColumns;

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
            + COLUMN_NAME_HOUSE_NO + " TEXT,"+ COLUMN_NAME_ADMIN_ID + " INTEGER)";

    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
