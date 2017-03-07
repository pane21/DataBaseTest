package com.pane21.databasetest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pane21.databasetest.Data.DbContract;
import com.pane21.databasetest.Data.DbSQLiteOpenHelper;

public class EditTextActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private EditText mTextView;
    private DbSQLiteOpenHelper mSQLiteDatabase;
    private Button mButton;
    private EditText mOldName;
    private EditText mNewName;
    private Button mUpdate;
    private Button mDelButton;
    private EditText mDelName;
    private Uri mCurrentUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        Intent intent = getIntent();
        Uri selectedUri = intent.getData();

        if(selectedUri == null){
            setTitle("Add a Name");
        }else {

            setTitle("Edit Name");
        }

        getSupportLoaderManager().initLoader(0,null,this);

        mTextView = (EditText)findViewById(R.id.editText2);
        mButton = (Button) findViewById(R.id.button);
        mSQLiteDatabase = new DbSQLiteOpenHelper(this);
        mOldName = (EditText)findViewById(R.id.editOld);
        mNewName = (EditText)findViewById(R.id.editNew);
        mUpdate = (Button)findViewById(R.id.button2);
        mDelButton = (Button)findViewById(R.id.button3);
        mDelName = (EditText)findViewById(R.id.editDel);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            insertName();

            }
        });

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateName(mOldName.getText().toString(), mNewName.getText().toString());

            }
        });


        mDelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delName();

            }
        });

    }




    private void insertName(){
        ContentValues values = new ContentValues();
        values.put(DbContract.TableEntry.COLUMN_NAME, mTextView.getText().toString());

        getContentResolver().insert(DbContract.TableEntry.CONTENT_URI,values);
        finish();


    }

    private void delName(){
        //DELETE * FROM VIVZTABLE Where Name='vivz'
        String[] whereNameis = {mDelName.getText().toString()};

        getContentResolver().delete(DbContract.TableEntry.CONTENT_URI, DbContract.TableEntry.COLUMN_NAME+"=?",whereNameis);


        finish();

    }



    private void updateName(String oldName, String newName){
        //UPDATE littleTable SET name='Mike' WHERE name='Toto';

        ContentValues values = new ContentValues();
        values.put(DbContract.TableEntry.COLUMN_NAME,newName);
        String[] whereArgs={oldName};
        getContentResolver().update(DbContract.TableEntry.CONTENT_URI,values, DbContract.TableEntry.COLUMN_NAME+"=?",whereArgs);



        finish();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,mCurrentUri,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data.moveToFirst()){
            mOldName.setText(data.getString(data.getColumnIndex(DbContract.TableEntry.COLUMN_NAME)));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
