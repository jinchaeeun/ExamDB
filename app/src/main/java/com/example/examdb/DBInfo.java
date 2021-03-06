package com.example.examdb;

//DESC : Database 관련 상수 저장 -----------------------------------------
public class DBInfo {
    // DB 관련 상수 ------------------------------------------------------
    public static DBAdapter         DB_ADAPTER; //클래스를 변수로 만들기
    
    public static final String      DB_NAME     = "Message.db"; //.db라는 걸보고 database 이름이구나 확인위해
    public static final int         DB_VERSION  = 1;    //db 버전

    // Table 관련 상수 ---------------------------------------------------
    public static final String      TABLE_MESSAGE  = "message_tbl";
    public static final String      KEY_ID      = "_id";
    public static final String      KEY_TITLE      = "title";
    public static final String      KEY_CONTENT = "content";

    
    //다음처럼 테이블 추가
    /*
    public static final String      TABLE_USER  = "user_tbl";
    public static final String      KEY_NAME      = "name";
    public static final String      KEY_PHONE      = "phone";
    */
}
