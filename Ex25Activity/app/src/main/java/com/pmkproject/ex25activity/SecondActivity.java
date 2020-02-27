package com.pmkproject.ex25activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //화면에 보여질 내용물 뷰를 설정
        setContentView(R.layout.activity_second);

        //제목줄(Action Bar) 객체를 얻어오기
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Second Activity");
    }
}
