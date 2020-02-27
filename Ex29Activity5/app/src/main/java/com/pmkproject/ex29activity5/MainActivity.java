package com.pmkproject.ex29activity5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {
        //Ex28Activity4 앱의 SecondActivity 실행
        Intent intent=new Intent();
        intent.setAction("aaa");
        startActivity(intent);
    }
}
