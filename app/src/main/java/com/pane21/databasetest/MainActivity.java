package com.pane21.databasetest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pane21.databasetest.Data.DbSQLiteOpenHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DbSQLiteOpenHelper mDbSQLiteOpenHelper;
    ArrayAdapter<String> mArrayAdapter;
    ListView mListView;


    long mRowId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.content_main);

        mDbSQLiteOpenHelper = new DbSQLiteOpenHelper(this, "mikey.db", null, 1);
        displayDatabaseRows();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertEntry();
                displayDatabaseRows();
                Snackbar.make(view, mRowId + " inserted", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onStart() {
        displayDatabaseRows();
        super.onStart();

    }


    private void displayDatabaseRows() {
        SQLiteDatabase db = mDbSQLiteOpenHelper.getReadableDatabase();


        ArrayList<String> mArrayList = new ArrayList<>();


//
//   Cursor cursor = mDbSQLiteOpenHelper.rawQuery("SELECT * FROM littleTable",null);
        Cursor cursor = db.query("littleTable",null,null,null,null,null,null);


        while (cursor.moveToNext()) {
        mArrayList.add(cursor.getString(cursor.getColumnIndex("_id"))+ ". "+ cursor.getString(cursor.getColumnIndex("name")));


    }
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mArrayList);
        mListView.setAdapter(mArrayAdapter);



        cursor.close();
    }

    private void insertEntry(){
        SQLiteDatabase db = mDbSQLiteOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", "Toto");

        mRowId = db.insert("littleTable",null,values);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, EditTextActivity.class);
            startActivity(intent);


            return true;
        }
        if (id == R.id.action_update) {



            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
