package com.pmkproject.ex02widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //XML 에서 만든 TextView 를 참조하여 제거하기
        TextView tv=findViewById(R.id.tv);
        tv.setSelected(true);
    }
}
