package com.example.cy.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

/**
 * @author cy
 */
public class MainActivity extends AppCompatActivity {

    private ImageButton btnPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnPlayer=findViewById(R.id.btn_player);
        final Intent intent=new Intent(MainActivity.this,MusicService.class);
        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动和停止MusicService
                if(MusicService.isplay ==false){
                    //启动Service
                    startService(intent);
                    Log.d("MianActivity","isplay");
                    //图片按钮start
                    ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.play,null));
                }else{
                    stopService(intent);Log.d("MianActivity","unplay");
                    //图片按钮stop
                    ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.stop,null));
                }
            }
        });

    }

    @Override
    protected void onStart() {
        //启动Service
        startService(new Intent(MainActivity.this,MusicService.class));
        super.onStart();
        Log.d("MianActivity","startService");
    }
}
