package com.pmkproject.ex27activity3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);

    }

    //startActivityForResult() 로 실행한 Activity 가 종료되어 이 Activity 가
    //다시 보여질 때 자동으로 실행되는 콜백 메소드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String str=data.getStringExtra("data");
                    int age=data.getIntExtra("Age",0);
                    tv.setText(str+", "+age);
                }else tv.setText("취소되었습니다");
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void clickBtn(View view) {
        //SecondActivity 실행
        Intent intent=new Intent(this,SecondActivity.class);
        //SecondActivity 로 가는 Intent 가 돌아오도록 하려면
        startActivityForResult(intent,1);
    }
}
