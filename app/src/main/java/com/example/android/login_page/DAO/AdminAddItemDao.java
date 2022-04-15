package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.login_page.Entity.Item;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AdminAddItemDao {
    public static final String TABLE_NAME="ADMIN_ADD_ITEM";
    public static final String COLUMN_NAME_ITEM_ID="ITEM_ID";
    public static final String COLUMN_NAME_ADMIN_ID="ADMIN_ID";
    public static final String COLUMN_NAME_DATE = "DATE";
    public static final String COLUMN_NAME_QUANTITY="QUANTITY";
    public  static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_ITEM_ID
            +" INTEGER,"+COLUMN_NAME_ADMIN_ID+" INTEGER," + COLUMN_NAME_DATE +" DATE,"+COLUMN_NAME_QUANTITY+" INTEGER,PRIMARY KEY("+
            COLUMN_NAME_ADMIN_ID+","+COLUMN_NAME_ITEM_ID+ ","+ COLUMN_NAME_DATE + "), FOREIGN KEY("+COLUMN_NAME_ITEM_ID+")" +
            " REFERENCES " + ItemDao.TABLE_NAME + "(" + ItemDao.COLUMN_NAME_ITEM_ID+"),"+
            "FOREIGN KEY("+COLUMN_NAME_ADMIN_ID +
                                ") REFERENCES "+ AdminDao.TABLE_NAME + "(" +AdminDao.COLUMN_NAME_ADMIN_ID+"))";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void addItem(SQLiteDatabase db, Item item, int adminID) {
        String date = LocalDateTime.now().toLocalDate().toString();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ADMIN_ID,adminID);
        values.put(COLUMN_NAME_ITEM_ID,item.getItemId());
        values.put(COLUMN_NAME_QUANTITY,item.getQuantity());
        values.put(COLUMN_NAME_DATE,date);
        db.insert(TABLE_NAME,null,values);
    }
}
