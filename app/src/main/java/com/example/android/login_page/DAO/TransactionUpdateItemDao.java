package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.HashMap;

public class TransactionUpdateItemDao implements BaseColumns {
    public static final String TABLE_NAME="TRANSACTION_UPDATE_ITEM";
    public static final String COLUMN_NAME_TID="TID";
    public static final String COLUMN_NAME_ITEM_ID="ITEM_ID";
    public static final String COLUMN_NAME_QUANTITY="QUANTITY";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_TID+" INTEGER,"+COLUMN_NAME_ITEM_ID+" INTEGER,"+
            COLUMN_NAME_QUANTITY+" INTEGER,PRIMARY KEY("+COLUMN_NAME_TID+","+COLUMN_NAME_ITEM_ID+")"+", FOREIGN KEY("+COLUMN_NAME_TID
            +") REFERENCES "+ TransactionDao.TABLE_NAME + "(" + TransactionDao.COLUMN_NAME_TID+"),FOREIGN KEY("+COLUMN_NAME_ITEM_ID+") REFERENCES "+ItemDao.TABLE_NAME +"(" + ItemDao.COLUMN_NAME_ITEM_ID+"))";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static HashMap<Integer, Integer> getItems(SQLiteDatabase db, int tId) {
        String selection = COLUMN_NAME_TID + " = ? ";
        String[] selectionArgs = {String.valueOf(tId)};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        HashMap<Integer,Integer> items = new HashMap<>();
        while (cursor.moveToNext()){
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_ID));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY));
            items.put(itemId,quantity);
        }
        return items;
    }

    public static int getAmount(SQLiteDatabase db, int transactionId) {
        HashMap<Integer,Integer> items = getItems(db,transactionId);
        return ItemDao.getTotalAmount(db,items);
    }

    public static void deleteTransaction(SQLiteDatabase writableDatabase, int transactionId) {
        String selection = COLUMN_NAME_TID + " = ? ";
        String[] selectionArgs = {String.valueOf(transactionId)};
        writableDatabase.delete(TABLE_NAME,selection,selectionArgs);
    }

    public static void updateQty(SQLiteDatabase writableDatabase, int tid, int itemId, int value) {
        String selection = COLUMN_NAME_TID + " = ? AND " + COLUMN_NAME_ITEM_ID + " = ?";
        String[] selectionArgs = {String.valueOf(tid),String.valueOf(itemId)};
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_QUANTITY,value);
        writableDatabase.update(TABLE_NAME,values,selection,selectionArgs);
    }

    public static void addNewItem(SQLiteDatabase writableDatabase, int tid, int itemId, int value) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TID,tid);
        values.put(COLUMN_NAME_ITEM_ID,itemId);
        values.put(COLUMN_NAME_QUANTITY,value);
        writableDatabase.insert(TABLE_NAME,null,values);
    }

    public static void deleteTransactionItem(SQLiteDatabase writableDatabase, int tid, int itemId) {
        String selection = COLUMN_NAME_TID + " = ? AND " + COLUMN_NAME_ITEM_ID + " = ?";
        String[] selectionArgs = {String.valueOf(tid),String.valueOf(itemId)};
        writableDatabase.delete(TABLE_NAME,selection,selectionArgs);
    }
}
