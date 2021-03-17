package com.example.examdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    private final String    TAG = "EXAM_DB";
    private ListView        dataLST;

    //데이터베이스의 data를 넣을 땐 SimpleCursorAdapter 사용한다.
    private SimpleCursorAdapter adapter;  //예쁘게하고싶으면 customAdapter를 사용해야한다
    //db 관련
    //private DBOpenHelper    dbHelper;
    //private SQLiteDatabase db;

    // Member Method - Override -----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        init();
        Log.i(TAG, " => DataActivity : onCreate()");
        Intent intent = getIntent();
        
        //리스트뷰 아이템 클릭 시 삭제
        dataLST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, " => DataActivity : onItemClick() id : "+ id + " | position : "+ position);
                // (1) DB에서 데이터 삭제
                // position은 그냥 리스트뷰에 나오는 위치 값
                DBInfo.DB_ADAPTER.deleteRow(DBInfo.TABLE_MESSAGE, id);

                //(2) List 갱신
                //Cursor cursor = DBInfo.DB_ADAPTER.getAllRow();
                adapter.changeCursor(DBInfo.DB_ADAPTER.getAllRow()); //커서가 바뀌었다는 것 확인.
            }
        });

    }

    // Member Method - Costom -----------------------------------
    public void init(){
        Log.i(TAG, " => DataActivity : init()");
        dataLST= findViewById(R.id.lv);
        //dbHelper = new DBOpenHelper(this);

        //List data
        Cursor cursor = DBInfo.DB_ADAPTER.getAllRow();
        //db = dbHelper.getReadableDatabase(); //읽기만 할 때
        //Cursor cursor = db.rawQuery("select * from " + DBInfo.TABLE_MESSAGE, null);

        // data <---> Layout ==> List item
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, new String[]{DBInfo.KEY_TITLE, DBInfo.KEY_CONTENT},
                new int[]{android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        //ListView <== List설정
        dataLST.setAdapter(adapter);

    }

    //클릭했을 때 나오는 데이터 인덱스 가지고 삭제


}
