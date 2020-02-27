package com.pmkproject.ex26activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.et);
    }

    public void clickBtn(View view) {
        //EditText 의 글씨를 얻어와서
        //SecondActivity 를 실행시키면서 전달
        String str=et.getText().toString();

        //SecondActivity 를 실행 해주는 택배기사 (Intent) 객체 생성
        Intent intent=new Intent(this,SecondActivity.class);

        //SecondActivity 에 전달할 데이터를
        //택배기사에게 추가하기
        intent.putExtra("Name",str);
        intent.putExtra("Age",20);

        startActivity(intent);

    }
}
