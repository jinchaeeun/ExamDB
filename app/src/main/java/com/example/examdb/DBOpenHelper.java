package com.example.examdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    // Member variable --------------------------------------------
    private final String    TAG ="EXAM_DB";
    
    //테이블 생성. 공백 잘 지키기
    private static final String CREATE_TABLE_MESSAGE = "create table if not exists " + 
                                                        DBInfo.TABLE_MESSAGE + 
                                                        "(" + DBInfo.KEY_ID + " integer primary key autoincrement, " +
                                                        DBInfo.KEY_TITLE + " text not null, " +
                                                        DBInfo.KEY_CONTENT + " text not null );";
    //테이블 삭제
    private static final String DROP_TABLE_MESSAGE =    "drop table if exists " 
                                                        + DBInfo.TABLE_MESSAGE +";";
    private Context         context;

    // Member Method - Constructor -----------------------------------
    // Constructor에서 DB 생성됨
    // 지정된 이름의 Database 생성
    public DBOpenHelper(@Nullable Context context) {
        super(context, DBInfo.DB_NAME, null, DBInfo.DB_VERSION);
        //context 값 넣어주기
        this.context = context;
        Log.i(TAG, " => DBOpenHelper : DBOpenHelper()");
    }
    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, Context context1) {
        super(context, name, factory, version);
        this.context = context;
        Log.i(TAG, " => DBOpenHelper : DBOpenHelper()");
    }


    // Member Method - Override -----------------------------------
    @Override
    public void onCreate(SQLiteDatabase db) { //Constructor에서 db인자
        //db가 생성되었을 때 호출.
        //Table 생성
        db.execSQL(CREATE_TABLE_MESSAGE);
        Log.i(TAG, " => DBOpenHelper : OnCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DB 업그레이드 시에 사용
        //if(newVersion != DBInfo.DB_VERSION) 안해도 자동으로 된다.

        db.execSQL(DROP_TABLE_MESSAGE); //기존 Table 삭제
        onCreate(db); //db.execSQL(CREATE_TABLE_MESSAGE); // Table 새로 생성
        Log.i(TAG, "DBOpenHelper : onUpgrade()");

    }
}
