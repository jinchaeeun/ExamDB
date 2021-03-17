package com.example.examdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    private final String    TAG = "EXAM_DB";
    private ListView        dataLST;
    //데이터베이스의 data를 너을 땐
    private SimpleCursorAdapter adapter;
    //db 관련
    private DBOpenHelper    dbHelper;
    private SQLiteDatabase db;

    // Member Method - Override -----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        init();
        Log.i(TAG, " => DataActivity : onCreate()");
        Intent intent = getIntent();

    }

    // Member Method - Costom -----------------------------------
    public void init(){
        Log.i(TAG, " => DataActivity : init()");
        dataLST= findViewById(R.id.lv);
        dbHelper = new DBOpenHelper(this);
        //List data
        db = dbHelper.getReadableDatabase(); //읽기만 할 때
        Cursor cursor = db.rawQuery("select * from " + DBInfo.TABLE_MESSAGE, null);

        // data <---> Layout ==> List item
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, new String[]{DBInfo.KEY_TITLE, DBInfo.KEY_CONTENT},
                new int[]{android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        //ListView <== List설정
        dataLST.setAdapter(adapter);



    }

}