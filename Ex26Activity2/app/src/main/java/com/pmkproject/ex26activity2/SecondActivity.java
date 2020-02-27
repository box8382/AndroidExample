package com.pmkproject.ex26activity2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv=findViewById(R.id.tv);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("다음창");
        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent=getIntent();
        String str=intent.getStringExtra("Name");
        int age=intent.getIntExtra("Age",0);
        tv.setText(str+", "+age);

    }
}
