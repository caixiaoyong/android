package com.example.cy.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by cy on 21-1-20.
 */

public class MyBr1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "网络状态发生改变~", Toast.LENGTH_SHORT).show();
    }
}
