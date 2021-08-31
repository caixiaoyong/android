package com.example.cy.layout_six;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private Button mBtnGridView,mBtnFrame,mBtnTable,mBtnDemo;
    private FrameLayout mFLt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBtnGridView=findViewById(R.id.btn_gridview);
        mBtnFrame=findViewById(R.id.btn_frame);
        mBtnTable=findViewById(R.id.btn_table);
        mBtnDemo=findViewById(R.id.demo);
      /*  mBtnGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        mFLt=findViewById(R.id.mylayout);
        //7:在主Java代码中获取帧布局对象,并且实例化一个PlaneView类
        final PlaneView plane=new PlaneView(MainActivity.this);
        //8.为我们实例化的气球对象添加触摸事件监听器，
        plane.setOnTouchListener(new View.OnTouchListener() {
            @Override//重写onTouch方法,改变气球的X,Y坐标，调用invalidate()重绘方法
            public boolean onTouch(View view, MotionEvent event) {
                //设置气球显示的位置
                plane.bitmapX=event.getX()-150;
                plane.bitmapY=event.getY()-150;
                //调用重绘方法
                plane.invalidate();
                return true;
            }
        });
        // 9: 将气球对象添加到帧布局中
        mFLt.addView(plane);
      setListeners();
    }

    private void setListeners() {
        Onclick onClick=new Onclick();
        mBtnGridView.setOnClickListener(onClick);
        mBtnFrame.setOnClickListener(onClick);
        mBtnTable.setOnClickListener(onClick);
        mBtnDemo.setOnClickListener(onClick);

    }

    private class Onclick implements View.OnClickListener{
            @Override
        public void onClick(View v){
            Intent intent=null;
            switch(v.getId()){
                case R.id.btn_gridview:
                    //跳转到GridView演示界面
                    intent= new Intent(MainActivity.this,GridViewActivity.class);
                    break;
                case R.id.btn_frame:
                    //跳转到FrameLayoutActivity演示界面
                    intent=new Intent(MainActivity.this,FrameLayoutActivity.class);
                    break;
                case R.id.btn_table:
                    //跳转到TableLayoutActivity演示界面
                    intent=new Intent(MainActivity.this,TableLayoutActivity.class);
                    break;
                case R.id.demo:
                    //跳转到DemoActivity演示界面
                    intent=new Intent(MainActivity.this,DemoActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
