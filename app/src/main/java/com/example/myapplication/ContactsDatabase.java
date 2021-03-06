package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Agata on 2016-11-11.
 */
public class ContactsDatabase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = ("create table "+TABLE_NAME+" (id integer primary key not null, "+
        "name text not null, phone integer not null, username text not null, password text not null)");

    public ContactsDatabase(Context context){
        super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXIST "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_PHONE,c.getPhone());
        values.put(COLUMN_USERNAME,c.getUname());
        values.put(COLUMN_PASSWORD,c.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String uname){
        db=this.getReadableDatabase();
        String query = "select username, password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b="not found";
        if (cursor.moveToFirst()){
            do{
                a=cursor.getString(0);
                if(a.equals(uname)){
                    b=cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());

        }
        return b;



    }
}
