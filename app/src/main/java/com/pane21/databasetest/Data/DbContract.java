package com.pane21.databasetest.Data;

import android.provider.BaseColumns;

//Created on 2/20/2017
public final class DbContract {


    public final static class TableEntry implements BaseColumns{
        public final static String TABLE_NAME = "littleTable";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "test";


    }


}
