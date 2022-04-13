package com.example.android.login_page.DAO;

import android.provider.BaseColumns;

public class ItemDescDao implements BaseColumns {
    public static final String TABLE_NAME="ITEM_DESC";
    public static final String COLUMN_NAME_ITEM_NAME="ITEM_NAME";
    public static final String COLUMN_NAME_BRAND="BRAND";
    public static final String COLUMN_NAME_CATEGORY="CATEGORY";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_ITEM_NAME+" TEXT PRIMARY KEY,"+COLUMN_NAME_BRAND
            +" TEXT,"+COLUMN_NAME_CATEGORY+" TEXT" +")";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
