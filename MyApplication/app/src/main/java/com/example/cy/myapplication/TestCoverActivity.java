package com.example.cy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestCoverActivity extends AppCompatActivity {

    private TextView mTvTitle;
    private Button mBtnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_cover);

        mTvTitle=findViewById(R.id.tv_title);
        mBtnFinish=findViewById(R.id.btn_finish);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        int number = bundle.getInt("number");

        mTvTitle.setText(name+","+number);

        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle1=new Bundle();
                bundle1.putString("title","我回来了");
                intent.putExtras(bundle1);
                setResult(Activity.RESULT_OK,intent);//当使用startActivityForResult时会把请求结果传回LifeCycle里的
                //public final void setResult(int resultCode, Intent data) //resultCode=RESULT_OK
                finish();//关闭当前页面LifeCycleActivity按钮
            }
        });
    }
}
