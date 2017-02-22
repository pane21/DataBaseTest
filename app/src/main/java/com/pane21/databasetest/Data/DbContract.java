package com.pane21.databasetest.Data;

import android.net.Uri;
import android.provider.BaseColumns;

//Created on 2/20/2017
public final class DbContract {

    public static final String CONTENT_AUTHORITY = "com.pane21.databasetest";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String CONTENT_PATH_DB = TableEntry.TABLE_NAME;



    public final static class TableEntry implements BaseColumns{
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, CONTENT_PATH_DB);

        public final static String TABLE_NAME = "littleTable";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "test";



    }




}
