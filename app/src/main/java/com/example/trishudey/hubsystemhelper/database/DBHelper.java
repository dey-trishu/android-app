package com.example.trishudey.hubsystemhelper.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by trishu.dey on 11/09/15.
 */
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HubSystem";
    public static final String USER_TABLE_NAME = "userInformation";
    public static  String CONTACTS_COLUMN_NAME = "username";
    public static  String CONTACTS_COLUMN_PASSWORD = "password";
    public static SQLiteDatabase db;


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table userInformation " +
                        "(username text primary key,password text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertUser  (String name, String password)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", name);
        contentValues.put("password", password);
        db.insert("userInformation", null, contentValues);
        return true;
    }

    public Cursor getData(String user,String pass){
        db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from userInformation where username = \""+user+"\" AND password = \""+pass+"\"", null ); //password = ""+pass+"" AND
        return res;
    }

    public int numberOfRows(){
        db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USER_TABLE_NAME);
        return numRows;
    }

    public int updateUser (String name, String password)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        String[] selectionArgs = { String.valueOf(name) };
        int val =db.update("userInformation", contentValues, "username = ? ", selectionArgs);
        return val;
    }

    public Integer deleteUser (String user)
    {
        db = this.getWritableDatabase();
        String[] selectionArgs = { String.valueOf(user) };
        return db.delete("userInformation",
                "username = ? ",
                selectionArgs);
    }

    public ArrayList<String> getAllUsers()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from userInformation", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}

