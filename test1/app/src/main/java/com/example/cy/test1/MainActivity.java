package com.example.cy.test1;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        int resId = getResources().getIdentifier("config_automatic_brightness_available", "boolean", "android");
        boolean resVal = getResources().getBoolean(resId);
         System.out.println(resVal);
        */


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE){
            if(event.getAction() == KeyEvent.ACTION_DOWN){
                event.startTracking();
                if(event.getRepeatCount() == 0){
                    Toast.makeText(this,"点击",Toast.LENGTH_LONG);
                    Log.d("Mediakey","按住");
                }
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
/* private boolean shortPress=false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 10) {
                Log.d("aaa", "长按");
            }
        }
    };
 @Override
 public boolean onKeyDown(int keyCode, KeyEvent event) {
     if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
         if (event.getRepeatCount() == 0) {
             mHandler.sendEmptyMessageDelayed(10, 500);
             //2222 myHandler.postDelayed(mRunnable,500);
         }
         return true;
     }
     return super.onKeyDown(keyCode, event);
 }



    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
            if (mHandler.hasMessages(10)) {
                Log.d("aaa", "短按");
                mHandler.removeMessages(10);
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);

    }

    *//*33333*//* @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
            shortPress = false;
            Toast.makeText(this, "长按", Toast.LENGTH_SHORT).show();
            Log.d("MediaKey","长按");
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }*/


}
