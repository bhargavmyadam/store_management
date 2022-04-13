package com.example.android.login_page.DAO;

import android.provider.BaseColumns;

public class CustomerContactDao implements BaseColumns {
    public static final String TABLE_NAME="CUSTOMER_CONTACT";
    public static final String COLUMN_NAME_CONTACT_NUM="CONTACT_NUM";
    public static final String COLUMN_NAME_CUSTOMER_ID="CUSTOMER_ID";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_CUSTOMER_ID+" INTEGER,"+
            COLUMN_NAME_CONTACT_NUM+" TEXT,PRIMARY KEY("+COLUMN_NAME_CUSTOMER_ID+","+COLUMN_NAME_CONTACT_NUM
            +"),FOREIGN KEY("+COLUMN_NAME_CUSTOMER_ID+" REFERENCES "+CustomerDao.COLUMN_NAME_CUSTOMER_ID+"))";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
