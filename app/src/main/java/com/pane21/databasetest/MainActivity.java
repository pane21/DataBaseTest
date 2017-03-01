package com.pane21.databasetest;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pane21.databasetest.Data.DbContract.TableEntry;
import com.pane21.databasetest.Data.DbSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private DbSQLiteOpenHelper mDbSQLiteOpenHelper;
    ListView mListView;
    TextView emptyView;
    CustomCursorAdapter mCustomCursorAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emptyView = (TextView)findViewById(R.id.empty_title_text);
        mListView = (ListView) findViewById(R.id.content_main);
        mListView.setEmptyView(emptyView);

        mCustomCursorAdapter = new CustomCursorAdapter(this, null);
        mListView.setAdapter(mCustomCursorAdapter);
//        getLoaderManager().initLoader(0,null,this);
        getSupportLoaderManager().initLoader(0,null,this);


//        displayDatabaseRows();



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
//        displayDatabaseRows();
        super.onStart();

    }



//    private void displayDatabaseRows() {
//
//        mDbSQLiteOpenHelper = new DbSQLiteOpenHelper(this);
//
//        Cursor cursor = getContentResolver().query(TableEntry.CONTENT_URI,null,null,null,null);
//
//
//        CustomCursorAdapter adapter = new CustomCursorAdapter(this,cursor);
//
//        mListView.setAdapter(adapter);
//
//    }

    private void insertEntry(){
//        SQLiteDatabase db = mDbSQLiteOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableEntry.COLUMN_NAME,"Toto");

        getContentResolver().insert(TableEntry.CONTENT_URI,values);

//        displayDatabaseRows();


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
//            displayDatabaseRows();




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,TableEntry.CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCustomCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCustomCursorAdapter.swapCursor(null);

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
