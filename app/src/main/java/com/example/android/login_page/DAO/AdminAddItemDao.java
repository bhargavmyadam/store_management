package com.example.android.login_page.DAO;

public class AdminAddItemDao {
    public static final String TABLE_NAME="ADMIN_ADD_ITEM";
    public static final String COLUMN_NAME_ITEM_ID="ITEM_ID";
    public static final String COLUMN_NAME_ADMIN_ID="ADMIN_ID";
    public static final String COLUMN_NAME_QUANTITY="QUANTITY";
    public  static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_ITEM_ID
            +" INTEGER,"+COLUMN_NAME_ADMIN_ID+" INTEGER,"+COLUMN_NAME_QUANTITY+" INTEGER,PRIMARY KEY("+
            COLUMN_NAME_ADMIN_ID+","+COLUMN_NAME_ITEM_ID+"), FOREIGN KEY("+COLUMN_NAME_ITEM_ID+")" +
            " REFERENCES " + ItemDao.TABLE_NAME + "(" + ItemDao.COLUMN_NAME_ITEM_ID+"),"+
            "FOREIGN KEY("+COLUMN_NAME_ADMIN_ID +
                                ") REFERENCES "+ AdminDao.TABLE_NAME + "(" +AdminDao.COLUMN_NAME_ADMIN_ID+"))";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
