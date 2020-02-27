package com.pmkproject.ex04imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //뷰 참조변수 : 멤버변수
    ImageView iv;
    Button btnAus,btnBel,btnCan,btnKor;

    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=findViewById(R.id.iv);
        btnAus=findViewById(R.id.btn01);
        btnBel=findViewById(R.id.btn02);
        btnCan=findViewById(R.id.btn03);
        btnKor=findViewById(R.id.btn04);

        //각 버튼들을 클릭하는 것을 듣는 리스너객체 생성 및 설정
        btnAus.setOnClickListener(listener);
        btnBel.setOnClickListener(listener);
        btnCan.setOnClickListener(listener);
        btnKor.setOnClickListener(listener);

        //이미지뷰를 클릭하는 것을 듣는 리스너 생성 및 추가
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setImageResource(R.drawable.flag_australia+num);
                num++;
                if(num>12)num=0;
            }
        });

    }//onCreate method..

    //멤버변수 위치
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //버튼의 종류에 따라 이미지를 변경

            //매개변수로 전달된 View view 가 클릭된 객체를 참조하고 있음.

            //클릭된 버튼의 id를 얻어오기!!
            int id = view.getId();
            switch(id){
                case R.id.btn01:
                    iv.setImageResource(R.drawable.flag_australia);
                    break;

                case R.id.btn02:
                    iv.setImageResource(R.drawable.flag_belgium);
                    break;

                case R.id.btn03:
                    iv.setImageResource(R.drawable.flag_canada);
                    break;

                case R.id.btn04:
                    iv.setImageResource(R.drawable.flag_korea);
                    break;

            }

        }
    };

}//MainActivity class..
