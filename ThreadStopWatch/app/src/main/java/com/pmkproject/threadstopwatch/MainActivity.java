package com.pmkproject.threadstopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    TextView tvSave;
    String str;
    ScrollView scroll;
    int numTv1=0,numTv2=0,numTv3=0;

    MyThread thread;

    boolean run=true;
    boolean runWait=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        tvSave=findViewById(R.id.tvSave);
        scroll=findViewById(R.id.scroll);
    }



    public void clickStart(View view) {
        run=true;
        runWait=true;
        if(thread==null) {
            thread=new MyThread();
            thread.start();
        }
    }

    public void clickStop(View view) {
        runWait=false;
        run = false;
        numTv1 = 0;
        numTv2 = 0;
        numTv3 = 0;
        thread=null;
    }

    public void clickPause(View view) {
        runWait=false;
    }

    public void clickSave(View view) {
        tvSave.append(str+"\n");
        scroll.fullScroll(View.FOCUS_DOWN);
    }


    class MyThread extends Thread{
        @Override
        public void run() {
            while (run){
                while(runWait) {
                    numTv3++;
                    if (numTv3 >= 100) {
                        numTv2++;
                        numTv3 = 0;
                    }
                    if (numTv2 >= 60) {
                        numTv1++;
                        numTv2 = 0;
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String s = String.format("%02d:%02d:%02d", numTv1, numTv2, numTv3);
                            str=s;
                            tv.setText(s);
                        }
                    });

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
