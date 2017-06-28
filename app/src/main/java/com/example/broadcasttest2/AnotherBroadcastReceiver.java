package com.example.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 系统广播
 */
public class AnotherBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"AnotherBroadcastReceiver就收的消息是："+intent.getStringExtra("data"),Toast.LENGTH_SHORT)
                .show();
    }
}
