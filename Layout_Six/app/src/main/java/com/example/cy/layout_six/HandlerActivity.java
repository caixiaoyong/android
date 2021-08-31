package com.example.cy.layout_six;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HandlerActivity extends AppCompatActivity {

    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mHandler=new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(HandlerActivity.this,TableLayoutActivity.class);
                startActivity(intent);
            }
        }, 3000);//延迟3000ms
      /*  mHandler=new Handler(){//UI主线程
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        ToastUtil.showmsg(HandlerActivity.this,"线程通信成功");
                                break;
                }
            }
        };
        new Thread(){
            @Override
            public void run() {
                super.run();
                Message msg = new Message();
                msg.what=1;
                mHandler.sendMessage(msg);
            }
        }.start();*/
    }
}
