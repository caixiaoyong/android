package com.example.cy.fragment;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContainerActivity extends AppCompatActivity implements AFragment.IOnMessageClick{//5.Activity去实现接口

    private Fragment aFragment;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mTvTitle=findViewById(R.id.tv_title);

       /* 实例化Afragment
        aFragment=new AFragment();*/

       //2.调用AFragment的静态方法
        aFragment=AFragment.newInstance("直接传入参数");
        //把AFragment添加到Activity中,调用commitAllowingStateLoss   //3.加个tag ：a
        getFragmentManager().beginTransaction().add(R.id.fl_container,aFragment,"a").commitAllowingStateLoss();
    }

    public void setData(String text){//4.给Activity写一个公共的方法，用于调用传递信息
        mTvTitle.setText(text);
    }

    @Override
    public void onClick(String text) {//5
        mTvTitle.setText(text);
    }
}
