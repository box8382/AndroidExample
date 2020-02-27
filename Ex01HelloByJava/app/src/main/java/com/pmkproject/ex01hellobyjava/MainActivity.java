package com.pmkproject.ex01hellobyjava;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.setContentView(R.layout.activity_main);


        //화면에 보여질 View 객체 생성하기!!
        Button btn = new Button(this);
        btn.setText("버튼");

//        setContentView(btn);

        //화면에 글씨를 보여주는 View 객체 생성!
//        TextView tv= new TextView(this);
        tv = new TextView(this);
        tv.setText("하이");
        tv.setTextSize(32); //32 pixel사이즈
        tv.setTextColor(Color.BLUE);

//        setContentView(tv);

        //Activity 는 한번에 1개의 View 만 설정할 수 있음.
        //그래서 ViewGroup 을 1개 만들고 그 안에 View 들을 추가

        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(linearLayout.VERTICAL);

        linearLayout.addView(btn);
        linearLayout.addView(tv);
        setContentView(linearLayout);

        //버튼 클릭하는 것에 반응하기!!
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //택스트뷰 글씨 변경하기!!
                tv.setText("변경완료");
            }
        });

    }
}
