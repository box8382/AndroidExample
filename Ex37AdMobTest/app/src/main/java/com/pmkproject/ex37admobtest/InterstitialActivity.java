package com.pmkproject.ex37admobtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class InterstitialActivity extends AppCompatActivity {

    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        //광고요청자 객체 생성
        AdRequest adRequest=new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

        //광고로드에 대한 리스너를 추가
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Toast.makeText(InterstitialActivity.this, "로드 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clickBtn(View view) {
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }else{
            Toast.makeText(this,"아직 광고가 로드 되지 않았습니다",Toast.LENGTH_SHORT).show();
        }
    }
}
