package com.pmkproject.ex63servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickStart(View view) {
        //백그라운드 작업 시작
        //백그라운드 작업 전용 class인 Service를 시작하도록
        Intent intent=new Intent(this, MyService.class);
        startService(intent);
    }

    public void clickStop(View view) {
        //백그라운드 작업 종료
        Intent intent=new Intent(this,MyService.class);
        stopService(intent);
    }


}
