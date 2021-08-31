package com.example.cy.tools;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoreConnectionActivity extends AppCompatActivity {

    private Button mBton1,mBton0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_connection);
        mBton0=findViewById(R.id.bton_0);
        mBton1=findViewById(R.id.bton_1);
        OnClick onClick=new OnClick();
        mBton0.setOnClickListener(onClick);
        mBton1.setOnClickListener(onClick);
    }
    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent=null;
            switch (view.getId()){
                case R.id.bton_0:
                    //NFC共享界面20
                    intent=new Intent(Settings.ACTION_NFCSHARING_SETTINGS);
                    break;
                case R.id.bton_1:
                    //NFC设置界面
                    intent=new Intent(Settings.ACTION_NFC_SETTINGS);
                    break;
            }
            startActivity(intent);
        }
    }
}
