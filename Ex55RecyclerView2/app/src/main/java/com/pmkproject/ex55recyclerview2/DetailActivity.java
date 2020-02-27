package com.pmkproject.ex55recyclerview2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        iv=findViewById(R.id.iv);

        Intent intent=getIntent();
        String name=intent.getStringExtra("Name");
        int imgId=intent.getIntExtra("Img",R.drawable.img11);

        //name을 액션바에 title로 설정
        getSupportActionBar().setTitle(name);

        //imgId를 이미지뷰에 보여주기
        Glide.with(this).load(imgId).into(iv);

        //iv에게 Transition(전환)의 Pair를 위한 이름 부여
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            iv.setTransitionName("IMG");
        }


    }
}
