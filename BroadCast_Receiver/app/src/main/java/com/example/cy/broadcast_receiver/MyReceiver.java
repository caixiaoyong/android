package com.example.cy.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cy on 21-1-7.
 */

public class MyReceiver extends BroadcastReceiver {//继承BroadcastReceive类
    /**
     * 定义常量，私有类型静态常量
     */
    private static final String ACTION1="activity1" ;
    private static final String ACTION2="bactivity2" ;
    @Override
    public void onReceive(Context context, Intent intent) {//实现onReceive方法
             //回复第一个广播
        if(intent.getAction().equals(ACTION1)){
            Toast.makeText(context, "MyReceiver收到，activity1", Toast.LENGTH_LONG).show();
            Log.d("MyReceive","a");
        }else if (intent.getAction().equals(ACTION2)){
            //回复第二个广播
            Toast.makeText(context, "MyReceiver收到，bactivity2", Toast.LENGTH_LONG).show();
            Log.d("MyReceive","b");
        }
    }
}
