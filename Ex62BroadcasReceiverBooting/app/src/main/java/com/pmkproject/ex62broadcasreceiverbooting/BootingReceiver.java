package com.pmkproject.ex62broadcasreceiverbooting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootingReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //N(누가) 버전 부터는 앱을 설치한 후 적어도 1회 직접 사용자가 런처화면에서 앱 아이콘을 클릭하여 실행한 앱만 BOOT_COMPLETE를 받을 수 있음
        Toast.makeText(context, "BootingReceiver received!!", Toast.LENGTH_SHORT).show();


        String action=intent.getAction();
        if(action.equals(intent.ACTION_BOOT_COMPLETED)){

            //이 앱에 있는 MainActivity 를 실행
            Intent i=new Intent(context,MainActivity.class);
            //새로운 Activity를 기존 Activity가 아닌 곳에서
            //실행하려면.. 새로운 Task에서 실행되도록..
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }

    }
}
