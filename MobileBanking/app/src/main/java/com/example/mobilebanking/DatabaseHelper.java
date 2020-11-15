package com.example.mobilebanking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "BankingDatabase.db";
    public static final String TABLE_NAME = "Customer_Table";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "Name";
    public static final String Col_3 = "Email";
    public static final String  Col_4 = "CurrentBalance";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(" create table " +TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,Name TEXT,Email TEXT,CurrentBalance INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(DataSet set )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Col_2,set.getName());
        content.put(Col_3,set.getEmail());
        content.put(Col_4,set.getCurrentBalance());

        long result = db.insert(TABLE_NAME,null,content);
        if(result == -1)
            return false;
        else
            return true;

    }
    public Cursor readAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME ,null);
    }
    public Cursor getRow(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select Name,Email,CurrentBalance from Customer_table where ID = ?",new String[]{ID});
    }
    //here i am updating data through name;
    public void updateCurrentBalance(String name, int balance)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_4,balance);
        db.update(TABLE_NAME,contentValues," Name = ? ",new String[]{name});
    }

    public void updateId(String oldId, String newId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",newId);
        db.update(TABLE_NAME,contentValues," ID = ? ",new String[]{oldId});
    }

    public int deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

}
