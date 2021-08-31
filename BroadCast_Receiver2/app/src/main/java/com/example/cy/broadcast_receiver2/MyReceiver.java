package com.example.cy.broadcast_receiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @author cy
 * @21-1-7.
 */

public class MyReceiver extends BroadcastReceiver {

    private static final String Action1="Action";
    @Override
    public void onReceive(Context context, Intent intent) {

       // String a=intent.getStringExtra("cy");
      //  Log.i(Action1,a);
             Toast.makeText(context, "MyReceiver收到广播!", Toast.LENGTH_SHORT).show();

    }

   

   

}
