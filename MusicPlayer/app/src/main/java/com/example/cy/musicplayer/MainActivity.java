package com.example.cy.musicplayer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * @author cy
 * @date 2021/01/22
 */
public class MainActivity extends AppCompatActivity /*implements MainFragment.OnFragmentInteractionListener*/{


    private ImageButton mIbtstart,mIbtlast,mIbtnext, mIbtlable;
    private ImageView img1;
    private RotateAnimation animation;
    private SeekBar seekBar;
    private MusicService.MyBinder musicControl;
    private ServiceConnection conn;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    updateProgress();
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent=new Intent(MainActivity.this,MusicService.class);
        conn=new ServiceConnection() {

            /**
             *  Acitvity 与Service连接成功时回调该方法
             */

            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                //获得MusicService中的MyBinder
                musicControl= (MusicService.MyBinder) iBinder;
                //获取进度条(歌曲时长)的最大值
                seekBar.setMax(musicControl.getDuration());
                //设置进度条的进度
                seekBar.setProgress(musicControl.getCurrenPostion());
            }

            /**
             * Acitvity 与Service断开连接时回调该方法
             */

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        //使用混合的方法开启服务
        startService(intent);
        bindService(intent, conn, BIND_AUTO_CREATE);


        /**
         * 2.图片旋转动画定义
         */
        img1=findViewById(R.id.img_1);
        animation = new RotateAnimation(0, 360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);


        /**
         * 1.播放按钮定义
         */
        mIbtstart=findViewById(R.id.ibt_start);
        mIbtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** 调用MyBinder中的play()方法*/
                musicControl.play();
                update(v);

            }
        });

        /**
         * 3.对seekBar设置监听，方便用户在拖动进度条时能到达相应的位置
         */
        seekBar=findViewById(R.id.sb_1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //进度条改变 i指当前所在位置
                if(b){
                    musicControl.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //开始触摸进度条
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //停止触摸进度条
            }
        });

        /**
         * 4.切歌
         */
        mIbtlast=findViewById(R.id.ibt_last);
        mIbtnext=findViewById(R.id.ibt_next);
        mIbtlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //   changeMusic(AppConstant.PlayerMsg.PREVIOUS_MUSIC,)
            }
        });

        mIbtlable =findViewById(R.id.ibt_lable);
        mIbtlable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,ListMainActivity.class);
                startActivity(intent1);
            }
        });
    }


  /*  private void changeMusic(int mode, int msg, List) {
    }*/

    public void update(View v) {

        if (musicControl.isPlaying()) {
            ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.pause0,null));

            indatae();handler.sendEmptyMessage(0);
        } else {
            ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.play0,null));
            animation.cancel();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //进入到界面后开始更新进度条
        if (musicControl != null){
            handler.sendEmptyMessage(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出应用后与service解除绑定
        unbindService(conn);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止更新进度条的进度
        handler.removeCallbacksAndMessages(null);
    }


    /**
     * 设置旋转动画
     */
    private void indatae() {

        //不停顿
        animation.setInterpolator(new LinearInterpolator());
        //设定转一圈的时间
        animation.setDuration(5000);
        //设定无限循环
        animation.setRepeatCount(Animation.INFINITE);
        //重复的模式
        animation.setRepeatMode(Animation.RESTART);
        //开启动画
        img1.startAnimation(animation);
        //动画停留在结束的位置
        animation.setFillAfter(true);
    }

    /**
     * 更新进度条
     */
    private void updateProgress() {
        int currenPostion = musicControl.getCurrenPostion();
        seekBar.setProgress(currenPostion);
        //使用Handler每500毫秒更新一次进度条
        handler.sendEmptyMessageDelayed(0, 500);
    }


}
