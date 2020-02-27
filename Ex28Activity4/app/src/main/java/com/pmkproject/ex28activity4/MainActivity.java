package com.pmkproject.ex28activity4;

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

        //묵시적(implicit) Intent 를 통해 SecondActivity 실행
        Intent intent=new Intent();
//        intent.setAction("aaa");
        intent.setAction("bbb");
        startActivity(intent);
    }
}
