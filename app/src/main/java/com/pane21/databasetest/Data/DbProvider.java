package com.pane21.databasetest.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Switch;


public class DbProvider extends ContentProvider {
    private DbSQLiteOpenHelper mDbSQLiteOpenHelper;
    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);



    static {
        sUriMatcher.addURI(DbContract.CONTENT_AUTHORITY,DbContract.CONTENT_PATH_DB,100);
        sUriMatcher.addURI(DbContract.CONTENT_AUTHORITY,DbContract.CONTENT_PATH_DB+"#",101);

    }



    @Override
    public boolean onCreate() {
        mDbSQLiteOpenHelper = new DbSQLiteOpenHelper(getContext());

        return false;
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDbSQLiteOpenHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);

        Cursor cursor = null;
        switch(match) {
            case 100:
            cursor = db.query(DbContract.TableEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }




    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }
}
