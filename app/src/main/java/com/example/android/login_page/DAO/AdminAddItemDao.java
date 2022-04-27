package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.login_page.Entity.AdminItem;
import com.example.android.login_page.Entity.Customer;
import com.example.android.login_page.Entity.Item;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public static void updateDatabase(SQLiteDatabase writableDatabase, int itemId, int adminID, int qty) {
        String date = LocalDateTime.now().toLocalDate().toString();
        String selection = COLUMN_NAME_ITEM_ID + " = ? AND " + COLUMN_NAME_ADMIN_ID + " = ? AND " + COLUMN_NAME_DATE + " = ?";
        String[] selectionArgs = {String.valueOf(itemId),String.valueOf(adminID),date};
        Cursor cursor = writableDatabase.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        if (cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_ITEM_ID,itemId);
            values.put(COLUMN_NAME_ADMIN_ID,adminID);
            values.put(COLUMN_NAME_DATE,date);
            values.put(COLUMN_NAME_QUANTITY,qty);
            writableDatabase.insert(TABLE_NAME,null,values);
        }
        else{
            cursor.moveToNext();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_QUANTITY,cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY))+qty);
            writableDatabase.update(TABLE_NAME,values,selection,selectionArgs);
        }
    }

    public static AdminItem[] getFullLog(SQLiteDatabase db) {
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        List<AdminItem> itemLog = new ArrayList<>();
        while(cursor.moveToNext()){
            int adminId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ADMIN_ID));
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_ID));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_DATE));
            AdminItem adminItem = new AdminItem(adminId,itemId,quantity,date);
            itemLog.add(adminItem);
        }
        AdminItem[] fullLog = new AdminItem[itemLog.size()];
        for (int i = 0; i < itemLog.size(); i++) {
            fullLog[i] = itemLog.get(i);
        }
        return fullLog;
    }

    public static AdminItem[] getLogByDate(SQLiteDatabase db, String date) {
        String selection = COLUMN_NAME_DATE + " = ?";
        String[] selectionArgs = {date};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        List<AdminItem> itemLog = new ArrayList<>();
        while(cursor.moveToNext()){
            int adminId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ADMIN_ID));
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_ID));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY));
            AdminItem adminItem = new AdminItem(adminId,itemId,quantity,date);
            itemLog.add(adminItem);
        }
        AdminItem[] fullLog = new AdminItem[itemLog.size()];
        for (int i = 0; i < itemLog.size(); i++) {
            fullLog[i] = itemLog.get(i);
        }
        return fullLog;
    }
}
