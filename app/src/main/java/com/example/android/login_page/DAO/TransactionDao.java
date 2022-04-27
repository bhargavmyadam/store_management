package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.android.login_page.Entity.Transaction;
import com.example.android.login_page.Entity.Worker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionDao implements BaseColumns {
    public static final String TABLE_NAME="TRANSACTION_TABLE";
    public static final String COLUMN_NAME_TID="TID";
    public static final String COLUMN_NAME_T_DATE="T_DATE";
    public static final String COLUMN_NAME_ADMIN_ID="ADMIN_ID";
    public static final String COLUMN_NAME_CUSTOMER_ID="CUSTOMER_ID";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"( "+
            COLUMN_NAME_TID+" INTEGER PRIMARY KEY,"+COLUMN_NAME_T_DATE+" DATE,"+COLUMN_NAME_ADMIN_ID
            +" INTEGER,"+COLUMN_NAME_CUSTOMER_ID+" INTEGER"+
            ", FOREIGN KEY("+COLUMN_NAME_ADMIN_ID+") REFERENCES "+ AdminDao.TABLE_NAME + "(" + AdminDao.COLUMN_NAME_ADMIN_ID+")"
            +",FOREIGN KEY("+COLUMN_NAME_CUSTOMER_ID+") REFERENCES "+ CustomerDao.TABLE_NAME + "(" +CustomerDao.COLUMN_NAME_CUSTOMER_ID+"))";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static Transaction[] getAllTransactions(SQLiteDatabase db) {
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        List<Transaction> transactionList = new ArrayList<>();
        while(cursor.moveToNext()){
            int tId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_TID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_T_DATE));
            int adminId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ADMIN_ID));
            int customerId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CUSTOMER_ID));
            HashMap<Integer,Integer> items = TransactionUpdateItemDao.getItems(db,tId);
            Transaction transaction = new Transaction(tId,date,adminId,customerId,items);
            transactionList.add(transaction);
        }
        Transaction[] transactions = new Transaction[transactionList.size()];
        for (int i = 0; i < transactionList.size(); i++) {
            transactions[i] = transactionList.get(i);
        }
        return transactions;
    }

    public static Transaction[] getAllTransactionsByDate(SQLiteDatabase db, String s) {
        String selection = COLUMN_NAME_T_DATE + " = ?";
        String[] selectionArgs = {s};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        List<Transaction> transactionList = new ArrayList<>();
        while(cursor.moveToNext()){
            int tId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_TID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_T_DATE));
            int adminId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ADMIN_ID));
            int customerId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CUSTOMER_ID));
            HashMap<Integer,Integer> items = TransactionUpdateItemDao.getItems(db,tId);
            Transaction transaction = new Transaction(tId,date,adminId,customerId,items);
            transactionList.add(transaction);
        }
        Transaction[] transactions = new Transaction[transactionList.size()];
        for (int i = 0; i < transactionList.size(); i++) {
            transactions[i] = transactionList.get(i);
        }
        return transactions;
    }

    public static int generateNewTid(SQLiteDatabase db) {
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if(cursor.getCount() == 0){
            return 1;
        }
        else{
            cursor.moveToLast();
            return cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_TID)) + 1;
        }
    }

    public static void addDummyTransaction(SQLiteDatabase db,int transactionId, int adminId) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TID,transactionId);
        values.put(COLUMN_NAME_T_DATE, LocalDateTime.now().toLocalDate().toString());
        values.put(COLUMN_NAME_ADMIN_ID,adminId);
        db.insert(TABLE_NAME,null,values);
    }

    public static void updateDummyTransaction(SQLiteDatabase writableDatabase, int transactionId, int customerId) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CUSTOMER_ID,customerId);
        String selection = COLUMN_NAME_TID + " = ?";
        String[] selectionArgs = {String.valueOf(transactionId)};
        writableDatabase.update(TABLE_NAME,values,selection,selectionArgs);
    }

    public static void deleteDummyTransaction(SQLiteDatabase writableDatabase, int transactionId) {
        String selection = COLUMN_NAME_TID + " = ?";
        String[] selectionArgs = {String.valueOf(transactionId)};
        writableDatabase.delete(TABLE_NAME,selection,selectionArgs);
    }

    public static LocalDate getTransactionDate(SQLiteDatabase db, int tid) {
        String selection = COLUMN_NAME_TID + " = ?";
        String[] selectionArgs = {String.valueOf(tid)};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        cursor.moveToNext();
        String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_T_DATE));
        return LocalDate.parse(date);
    }
}
