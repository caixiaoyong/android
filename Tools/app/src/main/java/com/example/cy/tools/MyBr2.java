package com.example.cy.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by cy on 21-1-20.
 */

public class MyBr2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "飞行模式状态发生改变~", Toast.LENGTH_SHORT).show();
    }
}
