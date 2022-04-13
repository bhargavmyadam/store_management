package com.example.android.login_page.DAO;

import android.provider.BaseColumns;

public class TransactionDao implements BaseColumns {
    public static final String TABLE_NAME="TRANSACTION";
    public static final String COLUMN_NAME_TID="TID";
    public static final String COLUMN_NAME_T_DATE="T_DATE";
    public static final String COLUMN_NAME_ADMIN_ID="ADMIN_ID";
    public static final String COLUMN_NAME_CUSTOMER_ID="CUSTOMER_ID";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"( "+
            COLUMN_NAME_TID+" INTEGER PRIMARY KEY,"+COLUMN_NAME_T_DATE+" DATE,"+COLUMN_NAME_ADMIN_ID
            +" INTEGER,"+COLUMN_NAME_CUSTOMER_ID+" INTEGER"+
            ", FOREIGN KEY("+COLUMN_NAME_ADMIN_ID+" REFERENCES "+AdminDao.COLUMN_NAME_ADMIN_ID+")"
            +",FOREIGN KEY("+COLUMN_NAME_CUSTOMER_ID+" REFERENCES "+CustomerDao.COLUMN_NAME_CUSTOMER_ID+"))";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
