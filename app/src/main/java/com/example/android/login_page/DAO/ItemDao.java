package com.example.android.login_page.DAO;

import android.provider.BaseColumns;

public class ItemDao implements BaseColumns {
    public static final String TABLE_NAME="ITEM";
    public static final String COLUMN_NAME_ITEM_ID="ITEM_ID";
    public static final String COLUMN_NAME_ITEM_NAME="ITEM_NAME";
    public static final String COLUMN_NAME_PRICE="PRICE";
    public static final String COLUMN_NAME_SIZE="SIZE";
    public static final String COLUMN_NAME_QUANTITY="QUANTITY";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"( "+COLUMN_NAME_ITEM_ID+" INTEGER PRIMARY KEY,"
            +COLUMN_NAME_ITEM_NAME+" TEXT,"+COLUMN_NAME_PRICE+" INTEGER,"+COLUMN_NAME_SIZE+" INTEGER,"+COLUMN_NAME_QUANTITY
            +" INTEGER,FOREIGN KEY( "+COLUMN_NAME_ITEM_NAME+" REFERENCES "+ ItemDescDao.COLUMN_NAME_ITEM_NAME+")" +" )";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
