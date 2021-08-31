package com.example.cy.mediakey;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Time time=new Time();
        time.setToNow();
        int hour=time.hour;
        tv1=findViewById(R.id.aaaaaaa);
        tv1.setText("hour: "+hour);
        bt1=findViewById(R.id.list);
        bt1.setText(R.string.bbb);
        bt1.setVisibility(View.INVISIBLE);
    }

    private long last = 0;
    private long secondTime =0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 10) {
                Log.d("aaa", "长按");
            }
        }
    };
//2222    private Runnable mRunnable=new Runnable() {
//        @Override
//        public void run() {
//            Log.d("aaa", "长按");
//        }
//    };
//    private Handler myHandler=new Handler();
    private long longTime=0;
    private boolean shortPress=false;

   /* private boolean mHeadSetKeyHandledInDown = false;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_HEADSETHOOK) {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                if (!mHeadSetKeyHandledInDown) {
                    Log.d("aaa", "短按");
                }
               // mHeadSetKeyHandledInDown = false;
            } else {
                if (event.getRepeatCount() == 1) {
                    Log.d("aaa", "长按");
                    mHeadSetKeyHandledInDown = true;
                }
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }*/

        @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
            if (event.getRepeatCount() == 0) {
                mHandler.sendEmptyMessageDelayed(10, 500);
                //2222 myHandler.postDelayed(mRunnable,500);
                mHandler.p
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

   /*33333*/ @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
            shortPress = false;
            Toast.makeText(this, "长按", Toast.LENGTH_SHORT).show();
            Log.d("MediaKey","长按");
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

public void aaa(String a,String b){

}



    private void wakeUpScreen(Context context) {
        if (context == null) {
            return;
        }
        PowerManager powerManager = (PowerManager)(context.getSystemService(Context.POWER_SERVICE));
        PowerManager.WakeLock wakeLock = null;
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP
                | PowerManager.ON_AFTER_RELEASE, "Notice_wake_lock");
        wakeLock.acquire(10);

    }

    public void postNoti() {


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify();//发送通知

    }
    public void initRingtoneManager(){
        RingtoneManager mRingtoneManager=new RingtoneManager(this);
    }
}
