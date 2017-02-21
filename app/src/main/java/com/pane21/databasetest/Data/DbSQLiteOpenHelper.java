package com.pane21.databasetest.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pane21.databasetest.Data.DbContract.TableEntry;


public class DbSQLiteOpenHelper extends SQLiteOpenHelper {
        private static final String TAG = "DbSQLiteOpenHelper";

        public static final String DATABASE_NAME = "mikey.db";
        public static final int DATABASE_VERSION = 1;


    public DbSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TABLE =
                "CREATE TABLE " + TableEntry.TABLE_NAME + "("
                        + TableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + TableEntry.COLUMN_NAME + " TEXT);";
        db.execSQL(SQL_CREATE_TABLE);

}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
