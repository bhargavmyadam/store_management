package com.example.android.login_page.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.android.login_page.Entity.Customer;
import com.example.android.login_page.Entity.Worker;

import java.util.ArrayList;
import java.util.List;

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

    public static Customer[] getAllCustomers(SQLiteDatabase db) {
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        List<Customer> customersList = new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CUSTOMER_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CUSTOMER_NAME));
            String street = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_STREET));
            String city = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CITY));
            String houseNo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOUSE_NO));
            int numberOfVisits = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_NUMBER_OF_VISITS));
            List<String> phoneNumbers = CustomerContactDao.getPhoneNumbers(db,id);
            Customer customer = new Customer(id,numberOfVisits,name,(ArrayList<String>) phoneNumbers,street,city,houseNo);
            customersList.add(customer);
        }
        Customer[] customers = new Customer[customersList.size()];
        for (int i = 0; i < customersList.size(); i++) {
            customers[i] = customersList.get(i);
        }
        return customers;
    }

    public static Customer[] getAllCustomersByName(SQLiteDatabase db, String customerName) {
        String selection = COLUMN_NAME_CUSTOMER_NAME + " = ?";
        String[] selectionArgs = {customerName};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        List<Customer> customersList = new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CUSTOMER_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CUSTOMER_NAME));
            String street = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_STREET));
            String city = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CITY));
            String houseNo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOUSE_NO));
            int numberOfVisits = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_NUMBER_OF_VISITS));
            List<String> phoneNumbers = CustomerContactDao.getPhoneNumbers(db,id);
            Customer customer = new Customer(id,numberOfVisits,name,(ArrayList<String>) phoneNumbers,street,city,houseNo);
            customersList.add(customer);
        }
        Customer[] customers = new Customer[customersList.size()];
        for (int i = 0; i < customersList.size(); i++) {
            customers[i] = customersList.get(i);
        }
        return customers;
    }

    public static Customer getCustomerById(SQLiteDatabase readableDatabase, int customerId) {
        String selection = COLUMN_NAME_CUSTOMER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(customerId)};
        Cursor cursor = readableDatabase.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        cursor.moveToNext();
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CUSTOMER_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CUSTOMER_NAME));
        String street = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_STREET));
        String city = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_CITY));
        String houseNo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_HOUSE_NO));
        int numberOfVisits = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_NUMBER_OF_VISITS));
        List<String> phoneNumbers = CustomerContactDao.getPhoneNumbers(readableDatabase,id);
        return new Customer(id,numberOfVisits,name,(ArrayList<String>) phoneNumbers,street,city,houseNo);
    }

    public static int addNewCustomer(SQLiteDatabase writableDatabase, Customer newCustomer) {
        Cursor cursor = writableDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        int customerId;
        if(cursor.getCount() == 0){
            customerId = 1;
        }
        else{
            cursor.moveToLast();
            customerId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_CUSTOMER_ID)) + 1;
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_CUSTOMER_NAME,newCustomer.getCustomerName());
        values.put(COLUMN_NAME_NUMBER_OF_VISITS,newCustomer.getNumberOfVisits());
        values.put(COLUMN_NAME_CITY,newCustomer.getCity());
        values.put(COLUMN_NAME_STREET,newCustomer.getStreet());
        values.put(COLUMN_NAME_HOUSE_NO,newCustomer.getHouseNumber());
        values.put(COLUMN_NAME_CUSTOMER_ID,customerId);
        writableDatabase.insert(TABLE_NAME,null,values);
        CustomerContactDao.addNewContact(writableDatabase,customerId,newCustomer.getPhoneNumbers());
        return customerId;
    }
}
