package com.pane21.databasetest.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "DbSQLiteOpenHelper";


    public DbSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TABLE =
                "CREATE TABLE littleTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";
        db.execSQL(SQL_CREATE_TABLE);


}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
