package com.pmkproject.ex03widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //멤버변수
    TextView tv;//참조변수
    Button btn;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //XML 에서 만들어진 View 객체를 찾아와서 참조변수에 대입
        tv=this.findViewById(R.id.tv);
        btn=findViewById(R.id.btn);
        et=findViewById(R.id.et);

        //버튼이 클릭되면 TextView 의 글씨 변경
        //버튼클릭 이벤트를 듣는 리스터 객체 생성 및 붙이기
        btn.setOnClickListener(new View.OnClickListener() {
            //버튼이 클릭되면 자동으로 실행되는 콜백메소드
            @Override
            public void onClick(View view) {
                //EditText 에 써있는 글씨를 얻어오기!

                //얻어온 글씨를 TextView 에 설정하기!
                Editable editable = et.getText();
                String str = editable.toString();

                //얻어온 글씨(str)
                tv.setText(str);
            }
        });


    }
}
