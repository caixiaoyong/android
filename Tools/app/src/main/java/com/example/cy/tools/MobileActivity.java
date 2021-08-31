package com.example.cy.tools;

import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MobileActivity extends AppCompatActivity {

    private Button mBt0,mBt1,mBt2;
    MyBr2 myBr2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        mBt0=findViewById(R.id.btn_0);
        mBt1=findViewById(R.id.btn_1);
        mBt2=findViewById(R.id.btn_2);
        OnClick onClick=new OnClick();
        mBt0.setOnClickListener(onClick);
        mBt1.setOnClickListener(onClick);
        mBt2.setOnClickListener(onClick);
        myBr2=new MyBr2();//使用myBr2接收广播
        IntentFilter itFilter=new IntentFilter();//匹配广播类型
        itFilter.addAction("intent.action.AIRPLANE_MODE");//飞行模式广播
        registerReceiver(myBr2,itFilter);//注册

    }
    //取消广播
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBr2);
    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent=null;
            switch (view.getId()){
                case R.id.btn_0:
                    //跳转到飞行模式  222  intent.action.AIRPLANE_MODE;
                    intent=new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                    //intent=new Intent(Settings.ACTION_SETTINGS);

                    break;
                case R.id.btn_1:
                    //跳转到应用管理界面
                    intent=new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                    break;
                case R.id.btn_2:
                    //跳转到网络运营商
                    intent=new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
                    break;
            }
            startActivity(intent);
        }
    }
}
