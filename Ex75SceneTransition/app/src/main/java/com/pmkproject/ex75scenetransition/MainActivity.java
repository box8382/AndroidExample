package com.pmkproject.ex75scenetransition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rootLayout;

    Scene scene1;
    Scene scene2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout=findViewById(R.id.layout_root);

        //2개의 장면(Scene)객체를 생성
        scene1=Scene.getSceneForLayout(rootLayout, R.layout.scene1,this);
        scene2=Scene.getSceneForLayout(rootLayout, R.layout.scene2,this);


        //우선 처음 보여질 장면(Scene)을 화면에 보이도록.
        scene1.enter();
    }

    //장면(Scene)에서 지정한 onClick메소드들
    public void clickBtn1(View v){
        //장면 1로 전환
        //전환 효과 객체 생성
        Transition transition=TransitionInflater.from(this).inflateTransition(R.transition.tran);
        TransitionManager.go(scene1, transition);

    }
    public void clickBtn2(View v){
        //장면 1로 전환
        Transition transition=TransitionInflater.from(this).inflateTransition(R.transition.tran);
        TransitionManager.go(scene2, transition);
    }
    public void clickBtn3(View v){

    }

}
