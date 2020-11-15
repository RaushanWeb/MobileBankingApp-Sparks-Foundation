package com.example.mobilebanking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TransactionDatabase extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "Transaction.db";
    public final static String TABLE_NAME = "Transaction_Table";
    public final static String Clo_1 = "ID";
    public final static String Clo_2 = "SenderName";
    public final static String Col_3 = "ReciverName";
    public final static String Col_4 = "Balance";

    public TransactionDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,SenderName TEXT,ReciverName TEXT,Balance INTEGER)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String s1 = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(s1);
        onCreate(db);
    }

    //here, i am inserting data
    public boolean transactionInserted(SetDataTransaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SenderName", transaction.getSenderName());
        values.put("ReciverName", transaction.getReciverName());
        values.put("Balance", transaction.getBalance());

        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    //here, i am reading all the data .
    public Cursor readTransactionData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String s2 = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(s2, null);
    }

    //getting senderName reciverName and balanc in form of array
    public Cursor getTransactionDataRow(String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select SenderName,ReciverName,Balance from " + TABLE_NAME + " where ID = ?", new String[]{Id});
    }

    public int deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

}
