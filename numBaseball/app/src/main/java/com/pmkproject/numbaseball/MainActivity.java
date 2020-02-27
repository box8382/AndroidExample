package com.pmkproject.numbaseball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv01;
    TextView tv02;
    TextView tv03;

    Button regame;
    Button reset;
    TextView tv_main;

    Button[] btns=new Button[10];

    Random rnd=new Random();
    int com100;
    int com10;
    int com1;

    int user100;
    int user10;
    int user1;

    int str;
    int ball;

    int num=1;
    int game=1;

    ScrollView scroll;

    void resetM(){
        tv01.setText("");
        tv02.setText("");
        tv03.setText("");
        num=1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scroll=findViewById(R.id.scroll);

        while(true) {
            com100 = rnd.nextInt(10);
            com10 = rnd.nextInt(10);
            com1 = rnd.nextInt(10);
            if(com100!=com10 && com10!=com1 && com100!=com1)break;
        }

        tv01=findViewById(R.id.tv01);
        tv02=findViewById(R.id.tv02);
        tv03=findViewById(R.id.tv03);
        tv_main=findViewById(R.id.tv_main);

        regame=findViewById(R.id.regame);

        reset=findViewById(R.id.reset);
        for(int i=0;i<btns.length;i++){
            btns[i]=findViewById(R.id.btn00+i);
            btns[i].setOnClickListener(listener);
        }

        tv_main.append("게임은 총 12회차 입니다\n");

        regame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_main.setText("");
                for(int i=0;i<btns.length;i++){
                    btns[i].setVisibility(View.VISIBLE);
                    reset.setVisibility(View.VISIBLE);
                    while(true) {
                        com100 = rnd.nextInt(10);
                        com10 = rnd.nextInt(10);
                        com1 = rnd.nextInt(10);
                        if(com100!=com10 && com10!=com1 && com100!=com1)break;
                    }
                }
                game=1;
                num=1;
                str=0;
                ball=0;
                tv_main.append("게임은 총 12회차 입니다\n");
            }
        });
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int n=view.getId();
            Button btn=findViewById(n);

            if(num==1){
                user100=Integer.parseInt(btn.getText().toString());
                tv01.setText(user100+"");
            }
            if(num==2){
                user10=Integer.parseInt(btn.getText().toString());
                tv02.setText(user10+"");
            }
            if(num==3){
                user1=Integer.parseInt(btn.getText().toString());
                tv03.setText(user1+"");

                if(user100==com100 && user10==com10 && user1==com1){
                    tv_main.append("게임 승리!\n");
                    reset.setVisibility(View.INVISIBLE);
                    resetM();
                    for(int i=0;i<btns.length;i++){
                        btns[i].setVisibility(View.INVISIBLE);
                    }

                }else {
                    if(user100==user10 || user10==user1 || user100==user1) {
                        tv_main.append("중복된 숫자를 입력하셨습니다\n");
                        scroll.fullScroll(ScrollView.FOCUS_DOWN);
                        resetM();
                        return;
                    }
                    if (user100 == com100) str++;
                    if (user10 == com10) str++;
                    if (user1 == com1) str++;
                    if(user100==com10 || user100==com1) ball++;
                    if(user10==com100 || user10==com1) ball++;
                    if(user1==com10 || user1==com100) ball++;
                    tv_main.append(game+"회차)    "+user100+" "+user10+" "+user1+" : ["+str+"]스트라이크 , ["+ball+"]볼\n");
                    game++;
                    if(game>=13){
                        tv_main.append("..게임 오버..\n");
                        reset.setVisibility(View.INVISIBLE);
                        resetM();
                        for(int i=0;i<btns.length;i++) {
                            btns[i].setVisibility(View.INVISIBLE);
                        }
                    }
                    ball=0;
                    str=0;
                    resetM();
                    num--;
                }
            }
            num++;
            scroll.fullScroll(ScrollView.FOCUS_DOWN);
        }
    };

    public void clickreset(View view) {
        resetM();
    }
}
