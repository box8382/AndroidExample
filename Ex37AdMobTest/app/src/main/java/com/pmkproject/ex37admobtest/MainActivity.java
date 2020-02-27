package com.pmkproject.ex37admobtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView=findViewById(R.id.adview);


        //모바일광고 초기화 완료 리스너 설정
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Toast.makeText(MainActivity.this, "광고초기화 완료", Toast.LENGTH_SHORT).show();
            }
        });

        //광고 요청자 객체 생성 : 광고를 ADMOB서버로부터 읽어오는 객체
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void clickBtn(View view) {
        //전면광고 보여주는 액티비티로 이동
        Intent intent=new Intent(MainActivity.this,InterstitialActivity.class);
        startActivity(intent);
    }

    public void clickBtn2(View view) {
        //동영상 광고 보여주는 액티비티 이동
        Intent intent=new Intent(MainActivity.this,RewardedActivity.class);
        startActivity(intent);
    }
}
