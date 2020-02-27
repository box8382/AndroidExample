package com.pmkproject.ex32thread2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MyThread myThread;


    //화면에 보이기전에 실행되는 메소드 즉 앱 실행했을때
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //화면이 보여질때 실행되는 메소드
    @Override
    protected void onStart() {
        super.onStart();
    }

    //액티비티가 재개? 됬을때 즉 다시 화면이 띄워질때 발동
    @Override
    protected void onResume() {
        super.onResume();
    }


    //이 액티비티가 화면에서 안보이기 시작할때 발동되는 메소드
    @Override
    protected void onPause() {
        super.onPause();
    }

    //이 액티비티가 아예 화면에서 안보이면 발동 메소드
    @Override
    protected void onStop() {
        if(myThread!=null) {
            myThread.stopThread();
        }
        super.onStop();
    }


    //이 액티비티가 메모리에 없어지면 발동 메소드
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //뒤로가기 버튼을 눌렀을때 발동하는 메소드
    @Override
    public void onBackPressed() {
        //Thread 멈추기!!-run method를 끝내면 쓰레드가 종료됨
        //이 에제에서는 run메소드 안에 while문에 의해 무한반복중이라서
        //while을 break해야 run메소드가 종료됨
//        myThread.isRun=false;


        super.onBackPressed();
    }



    public void clickBtn(View view) {
        //5초에 한번씩 현재시간을 Toast 로 출력

        myThread=new MyThread();
        myThread.start();

    }

    class MyThread extends Thread{

        boolean isRun=true;

        @Override
        public void run() {
            while (isRun){
                //현재시간
                Date now=new Date();
                final String s=now.toString();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //이 안에서는 UI변경 가능
                        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                    }
                });

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {}

            }//while..
        }//run method..

        void stopThread(){
            isRun=false;
        }
    }//MyThread class..
}//MainActivity class..
