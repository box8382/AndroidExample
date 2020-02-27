package com.pmkproject.ex27activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        et=findViewById(R.id.et);
    }

    public void clickBtn(View view) {
        //완료버튼을 누르면 EditText 에 써있는 글씨를 얻어와서
        //Intent 에게 추가시키고 SecondActivity 를 종료
        String str=et.getText().toString();

        //데이터를 복귀할 Intent 에게 추가하기
        Intent intent=getIntent();
        intent.putExtra("data",str);
        intent.putExtra("Age",20);

        //이 Activity 의 결과라고 설정?! 즉 다끝났다
        setResult(RESULT_OK,intent);
        //결과가 다끝났으니 RESULT_OK==-1 의 값을 보냄 -1을받으면 성공적으로 완료한것이고
        //다른 결과가 왔을경우 취소를 했거나 뒤로가기 버튼을 누른것임
        finish();
    }
}
