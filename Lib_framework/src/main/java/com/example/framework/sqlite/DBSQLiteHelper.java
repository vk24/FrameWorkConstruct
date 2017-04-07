package com.example.framework.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * function: 数据库帮助类
 * describe:详细描述
 * email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/5/31.
 */

public class DBSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TEST.db";
    private static final String TABLE_NAME = "CollTable";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase mSqliteDB;

    public DBSQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.mSqliteDB = db;
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR , age INTEGER , desc VARCHAR) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void insert (ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public Cursor query () {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }

    public void deleted (int id) {
        if (mSqliteDB != null) {
            mSqliteDB = getWritableDatabase();
            mSqliteDB.delete(TABLE_NAME,"_id=?",new String[]{String.valueOf(id)});
        }
    }

    public void closeDB () {
        if (mSqliteDB != null)
            mSqliteDB.close();
    }

}
