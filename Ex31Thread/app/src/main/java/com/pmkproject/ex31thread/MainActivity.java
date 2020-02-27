package com.pmkproject.ex31thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
    }

    public void clickBtn(View view) {
        //오래걸리는 작업(Ex. Network.. file IO.. 등등)
        //별도의 Thread 를 사용하지 않으므로 Main Thread 가 처리함
        for(int i=0;i<20;i++){


            //0.5초간 대기
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//for..

    }//clickBtn method..

    public void clickBtn2(View view) {
        //오래걸리는 작업 수행하는 직원객체(MyThread) 생성 및 실행

        MyThread t=new MyThread();
        t.start();//자동으로 run() method 실행 [절대 run method 직접 호출 X]


    }//clickBtn2 method


    //오래걸리는 작업을 수행하는 Thread 의 설계
    class MyThread extends Thread{

        @Override
        public void run() {
            //이 직원 객체가 해야할 작업을 코딩

            //오래걸리는 작업
            for(int i=0;i<20;i++){
                num++;

                //화면에 num 값 출력//
                //UI 변경작업은 반드시
                //MainThread 만 할 수 있음. [실행하는 디바이스의 android 버전이(pie 버전이) 부터 UI 변경이 가능함 ]

                //별도 Thread에서 UI변경작업을 하려면 ..
                //반드시 MainThread에게 변경 요청해야함

                //방법1. Handler를 이용하는 방법
//                handler.sendEmptyMessage(0);

                //방법2. Activity클래스의 메소드인 runOnUiThread()를 이용하는 방법
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //이 Runnable 쓰레드는
                        //Main Thread의 위임장을 받았기에
                        //UI변경이 가능함
                        tv.setText(num+"");
                    }
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        }//run method..

    }//MyThread class..

    //MainActivity 의 멤버변수
    //방법1.
//    Handler handler=new Handler(){
//        //sendEmptyMessage() 가 호출되면
//        //자동으로 실행되는 메소드
//        //이 메소드 안에서 UI변경 작업이 가능함
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            tv.setText(num + "");
//        }
//    };

}//MainActivity class..
