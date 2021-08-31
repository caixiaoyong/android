package com.example.cy.tools;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtn0, mBtn1, mBtn2, mBtn3, mBtn4, mBtn5, mBtn6, mBtn7, mBtn8;
    MyBr1 myBr1;MyBr3 myBr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn0 = findViewById(R.id.bt_0);
        mBtn1 = findViewById(R.id.bt_1);
        mBtn2 = findViewById(R.id.bt_2);
       /* Drawable drawable=getResources().getDrawable(R.drawable.wifi);
        drawable.setBounds(0,0,200,100);
        mBtn2.setCompoundDrawables(drawable,null,null,null);*/
        mBtn3 = findViewById(R.id.bt_3);
        mBtn4 = findViewById(R.id.bt_4);
        mBtn5 = findViewById(R.id.bt_5);
        mBtn6 = findViewById(R.id.bt_6);
        mBtn7 = findViewById(R.id.bt_7);
       /* mBtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        Onclick onclick = new Onclick();
        mBtn0.setOnClickListener(onclick);
        mBtn1.setOnClickListener(onclick);
        mBtn2.setOnClickListener(onclick);
        mBtn3.setOnClickListener(onclick);
        mBtn4.setOnClickListener(onclick);
        mBtn5.setOnClickListener(onclick);
        mBtn6.setOnClickListener(onclick);
        mBtn7.setOnClickListener(onclick);



        myBr1 = new MyBr1();
        IntentFilter itFilter = new IntentFilter();//匹配广播类型
        itFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//网络状态改变广播
        registerReceiver(myBr1,itFilter);

 /*       myBr3=new MyBr3();
        IntentFilter itFilter3=new IntentFilter();
        itFilter3.addAction("Intent.ACTION_CONFIGURATION_CHANGED");//设置改变时广播
        //(包括的改变:界面语言，设备方向，等)
        registerReceiver(myBr3,itFilter3);
*/


    }
    //取消广播
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBr1);
       // unregisterReceiver(myBr3);
    }

    public class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.bt_0:
                    //跳转到系统的设置界面
                    intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                    break;

                case R.id.bt_1:
                    //跳转到添加帐户界面
                    intent = new Intent(Settings.ACTION_ADD_ACCOUNT);
                    break;
                case R.id.bt_2:
                    //跳转到系统的WLAN界面 111  android.net.conn.CONNECTIVITY_CHANGE
                    intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    break;
                case R.id.bt_3:
                    //跳转到蓝牙管理界面
                    intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                    break;
                case R.id.bt_4:
                    //跳转到移动网络面
                    intent = new Intent(MainActivity.this, MobileActivity.class);
                    break;
                case R.id.bt_5:
                    //跳转到更多连接
                    intent = new Intent(MainActivity.this, MoreConnectionActivity.class);
                    break;
                case R.id.bt_6:
                    //跳转到显示和亮度界面
                    intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
                    break;
                case R.id.bt_7:
                    //跳转到关于手机界面
                    intent = new Intent(MainActivity.this, AboutPhoneActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
