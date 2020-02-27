package com.pmkproject.ex74introactivitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        iv=findViewById(R.id.iv);
        //뷰에 애니메이션 적용하기

        //res폴더의 anim폴더에 애니메이션 설계
        Animation ani= AnimationUtils.loadAnimation(this,R.anim.appear_logo);
        iv.startAnimation(ani);
        tv.startAnimation(AnimationUtils.loadAnimation(this,R.anim.appear_title));

        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.btn).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void clickBtn(View view) {
        startActivity(new Intent(this,SecondActivity.class));
        finish();
    }
}
