package com.example.android.login_page.DAO;

import android.provider.BaseColumns;

public class TransactionUpdateItemDao implements BaseColumns {
    public static final String TABLE_NAME="TRANSACTION_UPDATE_ITEM";
    public static final String COLUMN_NAME_TID="TID";
    public static final String COLUMN_NAME_ITEM_ID="ITEM_ID";
    public static final String COLUMN_NAME_QUANTITY="QUANTITY";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_TID+" INTEGER,"+COLUMN_NAME_ITEM_ID+" INTEGER,"+
            COLUMN_NAME_QUANTITY+" INTEGER,PRIMARY KEY("+COLUMN_NAME_TID+","+COLUMN_NAME_ITEM_ID+")"+", FOREIGN KEY("+COLUMN_NAME_TID
            +") REFERENCES "+ TransactionDao.TABLE_NAME + "(" + TransactionDao.COLUMN_NAME_TID+"),FOREIGN KEY("+COLUMN_NAME_ITEM_ID+") REFERENCES "+ItemDao.TABLE_NAME +"(" + ItemDao.COLUMN_NAME_ITEM_ID+"))";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
