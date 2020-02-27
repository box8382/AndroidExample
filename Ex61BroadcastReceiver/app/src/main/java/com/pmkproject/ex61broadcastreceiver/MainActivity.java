package com.pmkproject.ex61broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //@Broadcast Receiver : 휴대폰의 상태변화를 인지하고자 할 때 사용함[Ex. 배터리 부족, 와이파이 불가능, 블루투스탐색, 등등]
        //즉, 앱 에서 운영체제의 정보를 취득하고 싶을 때 사용
        //안드로이드 운영체제는 운영체제의 정보를
        //방송(Broadcast)를 통해 모든 앱에게
        //알려주고 있음. 그러므로 이를 이용하고 싶다면
        //방송 수신기(Broadcast Receiver)를 만들면 됨

    }

    public void clickBtn(View view) {

        //방송 보내기!

        //명시적 인텐트를 통해 리시버 실행하기 [내 앱안에 있는 리시버를 실행할 때(명시적)]
//        Intent intent=new Intent(this, MyReceiver.class);
//        sendBroadcast(intent);

        //묵시적 인텐트로 리시버 실행하기 [내 디바이스 안에 설치된 모든 앱에게 방송을 보내는 방식(묵시적)]
        Intent intent=new Intent();
        intent.setAction("aaa");//"aaa" 라는 액션방송을 듣는 모든 리시버 받응해라...
        sendBroadcast(intent);

        //Oreo버전부터 브로드캐스트리시버 나 서비스 컴포넌트 사용에 제한을 두고 있음(백그라운드에서 너무 리소스를 많이 잠식할 수 있어서..)
        // -묵시적 인텐트를 통한 리시버 호출은 금지함!!
        //본인 앱에서 sendBroadcast()로 발동하는 방송은 본인 앱의 리시버만 가능하기에.. 명시적 인텐트만 권장함
        //그럼에도 묵시적인텐트로 하고싶다면?
        //앱이 켜져 있을때만 동작하도록 리시버를 이 액티비티에서 등록시켜 사용함
        //즉, Manifest.xml에 등록하지않고...
        //자바에서 리시버를 등록하여 사용할 수 있음!

        //시스템 브로드캐스트 인텐트는 여전히 묵시적 인텐트로 사용가능함!!
    }

    //액티비티가 화면에 보여질때
    //자동으로 실행되는 메소드
    @Override
    protected void onResume() {
        super.onResume();

        //동적으로 리시버 등록..(Manifest에 등록하지 않음)
        myReceiver=new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("aaa");
        registerReceiver(myReceiver,filter);
    }

    //액티비티가 화면에 보여지지 않을떄
    //자동으로 실행되는 메소드
    @Override
    protected void onPause() {
        super.onPause();

        //리시버를 제거
        unregisterReceiver(myReceiver);
    }

}
