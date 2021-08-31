package com.example.cy.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnImageView;
    private Button mBtnLifeCyle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnImageView=findViewById(R.id.btn_imageview);//获取按钮
        mBtnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到ImageView界面
                Intent intent=new Intent(MainActivity.this,ImageViewActivity.class);
                startActivity(intent);
            }
        });
        mBtnLifeCyle=findViewById(R.id.btn_lifecyle);
        mBtnLifeCyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LifeCycleActivity.class);
                startActivity(intent);
            }
        });

    }
}
