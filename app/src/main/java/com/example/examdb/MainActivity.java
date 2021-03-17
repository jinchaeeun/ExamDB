package com.example.examdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String    TAG = "EXAM_DB";
    private EditText        titleETXT, mesETXT;
    //db 관련
    private DBOpenHelper    dbHelper;
    private SQLiteDatabase  db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        Intent intent = new Intent();
        Log.i(TAG, "onCreatet()");
    }

    public void init(){
        Log.i(TAG, "init()");
        titleETXT = findViewById(R.id.titleETXT);
        mesETXT = findViewById(R.id.mesETXT);

        //dbHelper = new DBOpenHelper(this);
        DBInfo.DB_ADAPTER = new DBAdapter(this); //DB생성처리
    }

    //이제 필요 없음
//    private int getDataCount(){
//        // select * from message_tbl;
//        Cursor cursor = db.rawQuery("select * from " + DBInfo.TABLE_MESSAGE, null);
//        return cursor.getCount();   //현재 db 안에 가지고 있는 데이터 갯수
//    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.addBTN:
                if(! (titleETXT.getText().toString().equals("") && mesETXT.getText().toString().equals("") )){

                    ContentValues newData = new ContentValues();
                    newData.put(DBInfo.KEY_TITLE, titleETXT.getText().toString());
                    newData.put(DBInfo.KEY_CONTENT, mesETXT.getText().toString());

                    DBInfo.DB_ADAPTER.insertRow(DBInfo.TABLE_MESSAGE, newData);

                    initETXT();
/*                  //(1) DB Open
                    db = dbHelper.getWritableDatabase(); //데이터베이스 쓰기 접근 권한
                    //(2) DB Write = > Insert
                    ContentValues newData = new ContentValues();
                    newData.put(DBInfo.KEY_TITLE, titleETXT.getText().toString());
                    newData.put(DBInfo.KEY_CONTENT, mesETXT.getText().toString());
                    db.insert(DBInfo.TABLE_MESSAGE, null, newData); //초기 데이터 null, 위의 newData 넣겠다.
                    getDataCount();
                    Log.i(TAG, "MainActivity : addBTN : DB ROW COUNT : " + getDataCount());

                    // (3) DB Close
                    db.close();*/

                    Toast.makeText(MainActivity.this, "내용 저장 완료", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "내용 입력 필요", Toast.LENGTH_SHORT).show();
                }
                break;
                //list에 저장하기

            case R.id.viewBTN:
                startActivity(new Intent(MainActivity.this, DataActivity.class));
                break;

        }
    }

    public void initETXT(){
        titleETXT.setText("");
        mesETXT.setText("");
    }
}