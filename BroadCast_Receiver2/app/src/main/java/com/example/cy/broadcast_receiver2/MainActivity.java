package com.example.cy.broadcast_receiver2;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author cy
 * @data 2021/01/20
 */
public class MainActivity extends AppCompatActivity {

    private Button mbtnsend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注意：发送广播之前，要先注册
        // 告诉系统，当这个广播到来时，用MyReciver来接收
        MyReceiver receiver=new MyReceiver();
        //利用过滤器IntentFilter来声明可以匹配的广播类型
        IntentFilter filter=new IntentFilter();
        filter.addAction("Action");
        //利用registerReceiver(receiver, filter);来注册
        registerReceiver(receiver,filter);

        mbtnsend = findViewById(R.id.btn_send);
        mbtnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("Action");
               /* Intent intent=new Intent();
               intent.setAction("Action");
                intent.putExtra("cy","hello receiver");*/

                // 发送广播
                sendBroadcast(intent);

            }
        });
    }

}
