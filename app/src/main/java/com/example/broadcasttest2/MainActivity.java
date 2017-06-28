package com.example.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 本地广播的几点优势：
 * 1、可以明确的知道正在发送的广播不会离开我们程序，因此不必担心机密数据泄露
 * 2、其他的程序无法将广播发送到我们程序的内部，因此不需要担心会有安全漏洞的隐患
 * 3、发送本地广播比发送系统全局广播将会更加有效
 */
public class MainActivity extends AppCompatActivity {

    private Button send_broadcast;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_broadcast = (Button) findViewById(R.id.send_broadcast);
        //获取实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        send_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent("com.example.broadcasttest.MY_RECEIVER");
//                intent.putExtra("data","有序广播");
//                sendOrderedBroadcast(intent,null);//发送有序广播的方法
                Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);//发送本地广播
            }
        });
        //动态注册本地广播监听器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册的广播必须要销毁
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    //内部类：广播接收器
    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"onReceive LocalReceiver本地广播",Toast.LENGTH_SHORT).show();
        }
    }
}
