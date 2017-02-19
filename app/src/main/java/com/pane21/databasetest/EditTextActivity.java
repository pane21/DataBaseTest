package com.pane21.databasetest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pane21.databasetest.Data.DbSQLiteOpenHelper;

public class EditTextActivity extends AppCompatActivity {
    private EditText mTextView;
    private DbSQLiteOpenHelper mSQLiteDatabase;
    private Button mButton;
    private EditText mOldName;
    private EditText mNewName;
    private Button mUpdate;
    private Button mDelButton;
    private EditText mDelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        mTextView = (EditText)findViewById(R.id.editText2);
        mButton = (Button) findViewById(R.id.button);
        mSQLiteDatabase = new DbSQLiteOpenHelper(this,"mikey.db", null, 1);
        mOldName = (EditText)findViewById(R.id.editOld);
        mNewName = (EditText)findViewById(R.id.editNew);
        mUpdate = (Button)findViewById(R.id.button2);
        mDelButton = (Button)findViewById(R.id.button3);
        mDelName = (EditText)findViewById(R.id.editDel);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mSQLiteDatabase.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("name", mTextView.getText().toString());
                db.insert("littleTable",null,values);
                finish();

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

    private void delName(){
        //DELETE * FROM VIVZTABLE Where Name='vivz'
        SQLiteDatabase db = mSQLiteDatabase.getWritableDatabase();
        String[] whereNameis = {mDelName.getText().toString()};
            db.delete("littleTable","name=?",whereNameis);
        finish();

    }



    private void updateName(String oldName, String newName){
        SQLiteDatabase db = mSQLiteDatabase.getWritableDatabase();
        //UPDATE littleTable SET name='Mike' WHERE name='Toto';
        ContentValues values = new ContentValues();
        values.put("name",newName);
        String[] whereArgs={oldName};
        db.update("littleTable",values,"name=?",whereArgs);
        finish();
    }



}
