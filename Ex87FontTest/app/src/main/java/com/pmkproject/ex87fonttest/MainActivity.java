package com.pmkproject.ex87fonttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        tv2=findViewById(R.id.tv2);

        //폰트설정
        //Asset폴더의 창고관리자 객체 소환
        AssetManager assetManager=getAssets();
        Typeface typeface=Typeface.createFromAsset(assetManager,"fonts/CookieRun Black.otf");

        tv.setTypeface(typeface);
        tv2.setTypeface(typeface);
    }
}
