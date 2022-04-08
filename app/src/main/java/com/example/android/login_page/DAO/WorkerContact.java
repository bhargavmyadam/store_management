package com.example.android.login_page.DAO;

import android.provider.BaseColumns;

public class WorkerContact implements BaseColumns {
    public  static final String TABLE_NAME="WORKER_CONTACT";
    public  static final String COLUMN_NAME_WORKER_ID = "WORKER_ID";
    public  static final String COLUMN_NAME_CONTACT_NUM="PHONE_NUMBER";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME
            +"("+COLUMN_NAME_WORKER_ID+" INTEGER,"+COLUMN_NAME_CONTACT_NUM
            +" TEXT, FOREIGN KEY ("+COLUMN_NAME_WORKER_ID+") REFERENCES "
            +WorkerDao.TABLE_NAME+"("+WorkerDao.COLUMN_NAME_WORKER_ID+")"+
            ")";
    public  static final String SQL_DELETE_TABLE="DROP TABLE IF EXISTS " + TABLE_NAME;
}
