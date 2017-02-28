package com.pane21.databasetest;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pane21.databasetest.Data.DbContract.TableEntry;
import com.pane21.databasetest.Data.DbSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {
    private DbSQLiteOpenHelper mDbSQLiteOpenHelper;
    ArrayAdapter<String> mArrayAdapter;
    ListView mListView;
    TextView emptyView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emptyView = (TextView)findViewById(R.id.empty_title_text);
        mListView = (ListView) findViewById(R.id.content_main);
        mListView.setEmptyView(emptyView);



        displayDatabaseRows();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertEntry();
                Snackbar.make(view,""+ TableEntry._ID + " inserted", Snackbar.LENGTH_LONG)
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

        mDbSQLiteOpenHelper = new DbSQLiteOpenHelper(this);
//        SQLiteDatabase db = mDbSQLiteOpenHelper.getReadableDatabase();



//        ArrayList<String> mArrayList = new ArrayList<>();

//   Cursor cursor = mDbSQLiteOpenHelper.rawQuery("SELECT * FROM littleTable",null);
//        Cursor cursor = db.query(TableEntry.TABLE_NAME,null,null,null,null,null,null);
        Cursor cursor = getContentResolver().query(TableEntry.CONTENT_URI,null,null,null,null);


//        while (cursor.moveToNext()) {
//        mArrayList.add(cursor.getString(cursor.getColumnIndex(TableEntry._ID))+ ". "+ cursor.getString(cursor.getColumnIndex(TableEntry.COLUMN_NAME)));
//
//
//    }

        CustomCursorAdapter adapter = new CustomCursorAdapter(this,cursor);

//        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mArrayList);
        mListView.setAdapter(adapter);



//        cursor.close();
    }

    private void insertEntry(){
//        SQLiteDatabase db = mDbSQLiteOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableEntry.COLUMN_NAME,"Toto");

        getContentResolver().insert(TableEntry.CONTENT_URI,values);

        displayDatabaseRows();


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
            SQLiteDatabase db = mDbSQLiteOpenHelper.getWritableDatabase();
            db.delete(TableEntry.TABLE_NAME,null,null);
            displayDatabaseRows();




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

      private class CustomCursorAdapter extends CursorAdapter{

          public CustomCursorAdapter(Context context, Cursor c) {
              super(context, c, 0);
          }

          @Override
          public View newView(Context context, Cursor cursor, ViewGroup parent) {
              return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
          }

          @Override
          public void bindView(View view, Context context, Cursor cursor) {

              TextView nameView = (TextView)view.findViewById(R.id.name_view);

              String body = cursor.getString(cursor.getColumnIndex(TableEntry.COLUMN_NAME));

              nameView.setText(body);


          }
      }
}
