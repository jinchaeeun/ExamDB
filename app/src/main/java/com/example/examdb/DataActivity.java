package com.example.examdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayList arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        init();

        Intent intent = getIntent();
//        intent.getParcelableExtra("titleETXT");
//        intent.getParcelableExtra("mesETXT");

//        ArrayList mStrings = new ArrayList<String>();
//        mStrings.add(intent.getParcelableExtra("titleETXT"));
//        mStrings.add(intent.getParcelableExtra("mesETXT"));

        //ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_2, mStrings);
        //lv.setAdapter(adapter1);


    }
    public void init(){
        lv= findViewById(R.id.lv);
    }

}