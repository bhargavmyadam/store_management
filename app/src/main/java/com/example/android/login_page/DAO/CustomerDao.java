package com.example.android.login_page.DAO;

import android.provider.BaseColumns;

public class CustomerDao implements BaseColumns {
    public static final String TABLE_NAME="CUSTOMER";
    public static final String COLUMN_NAME_CUSTOMER_ID="CUSTOMER_ID";
    public static final String COLUMN_NAME_CUSTOMER_NAME="CUSTOMER_NAME";
    public static final String COLUMN_NAME_NUMBER_OF_VISITS="NUMBER_OF_VISITS";
    public static final String COLUMN_NAME_STREET="STREET";
    public static final String COLUMN_NAME_CITY="CITY";
    public static final String COLUMN_NAME_HOUSE_NO="HOUSE_NO";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_CUSTOMER_ID
            +" INTEGER PRIMARY KEY,"+COLUMN_NAME_CUSTOMER_NAME+" TEXT,"+COLUMN_NAME_NUMBER_OF_VISITS
            +" INTEGER,"+COLUMN_NAME_STREET+" TEXT,"+COLUMN_NAME_CITY+" TEXT,"+COLUMN_NAME_HOUSE_NO
            +" TEXT)";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
