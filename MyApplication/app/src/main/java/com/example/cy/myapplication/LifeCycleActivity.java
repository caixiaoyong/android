package com.example.cy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by cy on 21-1-5.
 */

public class LifeCycleActivity extends AppCompatActivity {

    private Button mbtntestCover,mbtnJump;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LifeCycle","---onCreate---");
        Log.d("LifeCycle","taskid:"+getTaskId()+",hash:"+hashCode());//演示过程任务栈id,为查看当前Activity是新指令还是复用,故打印唯一hashcode来标示
        logtaskName();//打印当前Activity所在任务栈的名称

        setContentView(R.layout.activity_lifecycle);

        mbtnJump=findViewById(R.id.btn_jump);
        mbtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LifeCycleActivity.this,LifeCycleActivity.class);
                startActivity(intent);

            }
        });

        mbtntestCover=findViewById(R.id.btn_testCover);
        mbtntestCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LifeCycleActivity.this,TestCoverActivity.class);
                //Activity间数据的传递
                Bundle bundle=new Bundle();
                bundle.putString("name","cy");
                bundle.putInt("number",88);
                intent.putExtras(bundle);//数据传递,putExtras通过Bundle传递的，所以自己也可以定义Bundle再调用的
                     startActivity(intent);
//                startActivityForResult(intent,0);//requestcode=0;
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(LifeCycleActivity.this,data.getExtras().getString("title"),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("LifeCycle","---onNewIntent---");
        Log.d("LifeCycle","taskid:"+getTaskId()+",hash:"+hashCode());//演示过程任务栈id,为查看当前Activity是新指令还是复用,故打印唯一hashcode来标示
        logtaskName();
    }

    private  void  logtaskName(){
      try {
          ActivityInfo info=getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);//异常try/catch一下
          Log.d("LifeCycleActivityName",info.taskAffinity);//当前Activity所在任务栈的名称
      } catch (PackageManager.NameNotFoundException e) {
          e.printStackTrace();
      }
  }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle","---onStart---");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle","---onResume---");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycle","---onPause---");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle","---onStop---");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LifeCycle","---onRestat---");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle","---onDestroy---");
    }
}
