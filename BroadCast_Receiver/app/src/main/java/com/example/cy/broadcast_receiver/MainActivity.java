package com.example.cy.broadcast_receiver;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mbtnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取按钮
        mbtnSend=findViewById(R.id.btn_send);
        //为按钮设置单击事件
        mbtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送广播
                Intent intent=new Intent();
                //为Intent添加动作
                intent.setAction("activity1");
                //30.0版本较高，需要启动应用程序才可
                intent.setComponent(new ComponentName("com.example.cy.broadcast_receiver","com.example.cy.broadcast_receiver.MyReceiver"));
                sendBroadcast(intent);
                Log.d("MainActivity","sendBroadcast");
            }
        });
    }
}
