package com.example.nagato.otpcreator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contacts_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(DataModel.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DataModel.Table_Name);

        // Create tables again
        onCreate(db);
    }

    public long insertContact(String data) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` will be inserted automatically.
        values.put(DataModel.Column_Data, data);

        // insert row
        long id = db.insert(DataModel.Table_Name, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public DataModel getContacts(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DataModel.Table_Name,
                new String[]{DataModel.Column_ID, DataModel.Column_Data},
                DataModel.Column_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        DataModel contact = new DataModel(
                cursor.getInt(cursor.getColumnIndex(DataModel.Column_ID)),
                cursor.getString(cursor.getColumnIndex(DataModel.Column_Data)));

        // close the db connection
        cursor.close();

        return contact;
    }

    public List<DataModel> getAllNotes() {
        List<DataModel> contacts = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataModel.Table_Name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataModel contact = new DataModel();
                contact.setId(cursor.getInt(cursor.getColumnIndex(DataModel.Column_ID)));
                contact.setData(cursor.getString(cursor.getColumnIndex(DataModel.Column_Data)));

                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return contacts;
    }

    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + DataModel.Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public void deleteContact(DataModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataModel.Table_Name, DataModel.Column_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }
}
