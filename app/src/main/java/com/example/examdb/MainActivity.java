package com.example.examdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText titleETXT, mesETXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        Intent intent = new Intent();
    }

    public void init(){
        titleETXT = findViewById(R.id.titleETXT);
        mesETXT = findViewById(R.id.mesETXT);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.addBTN:
//                String str1 = titleETXT.getText().toString();
//                String str2 =  mesETXT.getText().toString();
//                intent.putExtra("titleETXT",str1);
//                intent.putExtra("mesETXT",str2);
                //list에 저장하기

                break;
            case R.id.viewBTN:

                startActivity(new Intent(MainActivity.this, DataActivity.class));
                break;

        }
    }
}