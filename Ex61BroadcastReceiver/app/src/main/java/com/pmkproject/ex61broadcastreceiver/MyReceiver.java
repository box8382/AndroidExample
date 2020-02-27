package com.pmkproject.ex61broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //방송을 수신하면 자동으로 실행되는 메소드
        Toast.makeText(context, "Receiver", Toast.LENGTH_SHORT).show();
    }
}
