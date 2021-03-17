package com.example.examdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.DiskWriteViolation;
import android.util.Log;

// SQLite Database에 관련된 모든 메서드 ----------------------------
//따로따로 만들지말고 여기 한번에 넣자
public class DBAdapter {
    // Member variable --------------------------------------------
    private final String TAG = "EXAM_DB";
    private DBOpenHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;


    // Member Method - Override -----------------------------------
    public DBAdapter(Context context) { //context만 빋겠다.
        this.context = context;
        this.dbHelper = new DBOpenHelper(context);
        Log.i(TAG, " => DBAdapter : DBAdapter()");
    }

    // Member Method - Custom -----------------------------------
    //DB Open & Close---------------------------------------
    public void openDB(boolean isWrite) {
        if (isWrite == true) {
            this.db = dbHelper.getWritableDatabase();
        } else {
            this.db = dbHelper.getReadableDatabase();
        }
        Log.i(TAG, " => DBAdapter : openDB()");
    }

    public void closeDB() {
        if (db.isOpen()) dbHelper.close();
        Log.i(TAG, " => DBAdapter : closeDB()");
    }

    //DB Insert/Update/Delete -------------------------------------------------
    public long insertRow(String tablename, ContentValues newValue) {
        openDB(true);   //insert니까 true 로 줘야 권한 허용

        long rowId = db.insert(tablename, null, newValue);

        closeDB();
        Log.i(TAG, " => DBAdapter : insertRow(), rowId - " + rowId);
        return rowId;
    }

    public boolean deleteRow(String tablename, long rowID){
        openDB(true);

        // delete from message_tbl where_id=
        int delNums = db.delete(tablename, DBInfo.KEY_ID + "=" + rowID, null);
        Log.i(TAG, " => DBAdapter : deleteRow() || rowID : " + rowID + "|| delNums : " + delNums);

        //db.execSQL("delete from "+tablename + " where _id = " + rowID + ";" );
        // String[] pos = newValue;
        //db.delete(tablename, "_id=?", newValue);
        //getAllRow();

        closeDB();

        return (delNums >0)? true:false;
    }
    public Cursor getAllRow() {
        openDB(true);

        Cursor cursor = db.rawQuery("select * from " + DBInfo.TABLE_MESSAGE, null);

        //closeDB(); //뭐 안되면 getAllow했는데서 DB 닫아주면 됨
        Log.i(TAG, " => DBAdapter : insertRow(), Count - " + cursor.getCount()); //db 갯수 보기
        
        showCursor(cursor);         //Data 확인용
        cursor.moveToFirst();       //Data 전달용
        
        return cursor;
    }

    // Debug ----내가 제대로 가져왔는지 보고싶을 때--------------------------------------------------------------
    private void showCursor(Cursor cursor) {
        //cursor 안 다 확인
        if (cursor != null) {
            String tmp = "";
            while (cursor.moveToNext()) {
                tmp = "[ " + cursor.getInt(cursor.getColumnIndex(DBInfo.KEY_ID)) + " ] ";  //이거의 indexID를 주세요
                tmp += cursor.getString(cursor.getColumnIndex(DBInfo.KEY_TITLE)) + " , ";
                tmp += cursor.getString(cursor.getColumnIndex(DBInfo.KEY_CONTENT)) + "";
            }
            Log.i(TAG, " => DBAdapter : showCursor() ===> " + tmp);
        }
    }
    
    private int getRowCount(Cursor cursor) {
        if (cursor != null) {
            Log.i(TAG, " => DBAdapter : getRowCount() ===> ");
            return cursor.getCount();
        } else {
            return -1;
        }
    }

    //클릭해서 삭제하기

}
