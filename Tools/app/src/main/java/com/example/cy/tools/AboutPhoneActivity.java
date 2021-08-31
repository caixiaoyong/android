package com.example.cy.tools;

import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutPhoneActivity extends AppCompatActivity {

    private Button mBtt0,mBtt1,mBtt2,mBtt3,mBtt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_phone);
        mBtt0=findViewById(R.id.btt_0);
        mBtt1=findViewById(R.id.btt_1);
        mBtt2=findViewById(R.id.btt_2);
        mBtt3=findViewById(R.id.btt_3);
        mBtt4=findViewById(R.id.btt_4);
        OnClick onClick=new OnClick();
        mBtt0.setOnClickListener(onClick);
        mBtt1.setOnClickListener(onClick);
        mBtt2.setOnClickListener(onClick);
        mBtt3.setOnClickListener(onClick);
        mBtt4.setOnClickListener(onClick);

    }

 /*   @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBr3);
    }*/

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent=null;
            switch (view.getId()){
                case R.id.btt_0:
                    //IP设置界面
                    intent=new Intent(Settings.ACTION_WIFI_IP_SETTINGS);
                    break;
                case R.id.btt_1:
                    //系统语言选择界面
                    intent=new Intent(Settings.ACTION_LOCALE_SETTINGS);
                    break;
                case R.id.btt_2:
                    //内部存储界面
                    intent=new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
                    break;
                case R.id.btt_3:
                    //开发者选项
                    intent=new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
                    break;
                case R.id.btt_4:
                    //APN设置界面
                    intent=new Intent(Settings.ACTION_APN_SETTINGS);
                    break;
            }
            startActivity(intent);
        }
    }
}
