package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.android.login_page.Entity.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemDao implements BaseColumns {
    public static final String TABLE_NAME="ITEM";
    public static final String COLUMN_NAME_ITEM_ID="ITEM_ID";
    public static final String COLUMN_NAME_ITEM_NAME="ITEM_NAME";
    public static final String COLUMN_NAME_PRICE="PRICE";
    public static final String COLUMN_NAME_SIZE="SIZE";
    public static final String COLUMN_NAME_QUANTITY="QUANTITY";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"( "+COLUMN_NAME_ITEM_ID+" INTEGER PRIMARY KEY,"
            +COLUMN_NAME_ITEM_NAME+" TEXT,"+COLUMN_NAME_PRICE+" INTEGER,"+COLUMN_NAME_SIZE+" INTEGER,"+COLUMN_NAME_QUANTITY
            +" INTEGER,FOREIGN KEY( "+COLUMN_NAME_ITEM_NAME+") REFERENCES "+ ItemDescDao.TABLE_NAME + "(" +ItemDescDao.COLUMN_NAME_ITEM_NAME+")" +" )";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static Item[] getAllItems(SQLiteDatabase db) {
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        List<Item> itemList = new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_NAME));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_PRICE));
            int size = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_SIZE));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY));
            HashMap<String,String> brandAndCategory = ItemDescDao.getBrandAndCategory(db,name);
            Item item = new Item(id,name,brandAndCategory.get("brand"),price,brandAndCategory.get("category"),quantity,size);
            itemList.add(item);
        }
        Item[] items = new Item[itemList.size()];
        for (int i = 0; i < itemList.size(); i++) {
            items[i] = itemList.get(i);
        }
        return items;
    }

    public static Item[] getAllItemsByName(SQLiteDatabase db, String itemName) {
        String selection = COLUMN_NAME_ITEM_NAME + " = ?";
        String[] selectionArgs = {itemName};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        List<Item> itemList = new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_NAME));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_PRICE));
            int size = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_SIZE));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY));
            HashMap<String,String> brandAndCategory = ItemDescDao.getBrandAndCategory(db,name);
            Item item = new Item(id,name,brandAndCategory.get("brand"),price,brandAndCategory.get("category"),quantity,size);
            itemList.add(item);
        }
        Item[] items = new Item[itemList.size()];
        for (int i = 0; i < itemList.size(); i++) {
            items[i] = itemList.get(i);
        }
        return items;
    }

    public static void addItem(SQLiteDatabase db, Item item, int adminID) {
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToLast();
        int newItemID = 1;
        if(cursor.getCount()!=0){
            newItemID = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_ID)) + 1;
        }
        item.setItemId(newItemID);
        ContentValues values = getValues(item);
        db.insert(TABLE_NAME,null,values);
        ItemDescDao.addItem(db,item);
        AdminAddItemDao.addItem(db,item,adminID);
    }

    private static ContentValues getValues(Item item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ITEM_ID,item.getItemId());
        values.put(COLUMN_NAME_ITEM_NAME,item.getItemName());
        values.put(COLUMN_NAME_PRICE,item.getPrice());
        values.put(COLUMN_NAME_QUANTITY,item.getQuantity());
        values.put(COLUMN_NAME_SIZE,item.getSize());
        return values;
    }

    public static void updateDatabase(SQLiteDatabase writableDatabase, int itemId, int adminID, int qty, int price) {
        String selection = COLUMN_NAME_ITEM_ID + " = ? ";
        String[] selectionArgs = {String.valueOf(itemId)};
        Cursor cursor = writableDatabase.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        cursor.moveToNext();
        int newQty = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY)) + qty;
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_QUANTITY,newQty);
        values.put(COLUMN_NAME_PRICE,price);
        writableDatabase.update(TABLE_NAME,values,selection,selectionArgs);
        AdminAddItemDao.updateDatabase(writableDatabase,itemId,adminID,qty);
    }

    public static int getTotalAmount(SQLiteDatabase db, HashMap<Integer, Integer> items) {
        int totAmount = 0;
        for(int itemId : items.keySet()){
            String selection = COLUMN_NAME_ITEM_ID + " = ?";
            String[] selectionArgs = {String.valueOf(itemId)};
            Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
            cursor.moveToNext();
            totAmount += (cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_PRICE)) * items.get(itemId));
        }
        return totAmount;
    }

    public static Item getItemById(SQLiteDatabase readableDatabase, int itemId) {
        String selection = COLUMN_NAME_ITEM_ID + " = ?";
        String[] selectionArgs = {String.valueOf(itemId)};
        Cursor cursor = readableDatabase.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        cursor.moveToNext();
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_NAME));
        int price = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_PRICE));
        int size = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_SIZE));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY));
        HashMap<String,String> brandAndCategory = ItemDescDao.getBrandAndCategory(readableDatabase,name);
        Item item = new Item(id,name,brandAndCategory.get("brand"),price,brandAndCategory.get("category"),quantity,size);
        return item;
    }

    public static void updateQuantity(SQLiteDatabase writableDatabase, int transactionId) {
        HashMap<Integer,Integer> items = TransactionUpdateItemDao.getItems(writableDatabase,transactionId);
        for(int itemId : items.keySet()){
            String selection = COLUMN_NAME_ITEM_ID + " = ?";
            String[] selectionArgs = {String.valueOf(itemId)};
            Cursor cursor = writableDatabase.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
            ContentValues values = new ContentValues();
            cursor.moveToNext();
            values.put(COLUMN_NAME_QUANTITY,cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY)) - items.get(itemId));
            writableDatabase.update(TABLE_NAME,values,selection,selectionArgs);
        }
    }

    public static void removeItemStock(SQLiteDatabase writableDatabase, int itemId) {
        String selection = COLUMN_NAME_ITEM_ID + " = ?";
        String[] selectionArgs = {String.valueOf(itemId)};
        Cursor cursor = writableDatabase.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        cursor.moveToNext();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_QUANTITY,0);
        writableDatabase.update(TABLE_NAME,values,selection,selectionArgs);
    }

    public static ArrayList<Integer> getItemIdsByName(SQLiteDatabase db, String s) {
        String selection = COLUMN_NAME_ITEM_NAME + " = ?";
        String[] selectionArgs = {s};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        ArrayList<Integer> itemIds = new ArrayList<>();
        while (cursor.moveToNext()){
            itemIds.add(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_ITEM_ID)));
        }
        return itemIds;
    }
}
