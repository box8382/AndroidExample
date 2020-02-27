package com.pmkproject.ex43fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    MyFragment myFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);

        //Fragment를 관리하는 관리자객체
        fragmentManager=getSupportFragmentManager();


        //id를 이용해서 Fragment를 참조하기
        myFragment= (MyFragment) fragmentManager.findFragmentById(R.id.fragment);
    }

    public void clickBtn(View view) {
//        tv.setText("aaa");
        //MyFragmen에 TextView의 글씨변경
        myFragment.tv.setText("ddadwdwqewqdewfeafejfewnfawf ejkwnfejkwanfjk");

    }
}
