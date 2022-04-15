package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.android.login_page.Entity.Item;

import java.util.HashMap;

public class ItemDescDao implements BaseColumns {
    public static final String TABLE_NAME="ITEM_DESC";
    public static final String COLUMN_NAME_ITEM_NAME="ITEM_NAME";
    public static final String COLUMN_NAME_BRAND="BRAND";
    public static final String COLUMN_NAME_CATEGORY="CATEGORY";
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_NAME_ITEM_NAME+" TEXT PRIMARY KEY,"+COLUMN_NAME_BRAND
            +" TEXT,"+COLUMN_NAME_CATEGORY+" TEXT" +")";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static HashMap<String, String> getBrandAndCategory(SQLiteDatabase db, String itemName) {
        String selection = COLUMN_NAME_ITEM_NAME + " = ?";
        String[] selectionArgs = {itemName};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        cursor.moveToNext();
        HashMap<String,String> brandAndCategory = new HashMap<>();
        brandAndCategory.put("brand",cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_BRAND)));
        brandAndCategory.put("category",cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CATEGORY)));
        return brandAndCategory;
    }

    public static void addItem(SQLiteDatabase db, Item item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ITEM_NAME,item.getItemName());
        values.put(COLUMN_NAME_BRAND,item.getBrand());
        values.put(COLUMN_NAME_CATEGORY,item.getCategory());
        db.insert(TABLE_NAME,null,values);
    }
}
