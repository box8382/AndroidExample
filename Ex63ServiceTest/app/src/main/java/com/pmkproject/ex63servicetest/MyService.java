package com.pmkproject.ex63servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyService extends Service {

    //서비스를 실행하기 위해 startService()를
    //호출 했을 때 자동으로 실행되는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //이 안에서 백그라운드 작업 실행
        //3초마다 토스트 보이기
        Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
        handler.sendEmptyMessageDelayed(0,3000);

        //서비스 객체는 메모리 문제가 발생할 때
        //운영체제에서 임의적으로 kill하는 경우도 있음
        //START_STICKY : kill되었다가 다시 메모리 문제가 해결되면 자동 실행되도록 하는 리턴값
        return START_STICKY;
    }

    //서비스를 종료하기 위해 stopService()를
    //호출하면 자동으로 실행되는 메소드
    @Override
    public void onDestroy() {
        handler.removeMessages(0);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Toast.makeText(MyService.this, "aaa", Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(0,3000);
        }
    };

}
