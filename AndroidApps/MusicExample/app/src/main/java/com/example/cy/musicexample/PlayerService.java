package com.example.cy.musicexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerService extends Service implements AppConstant {
    private int current_position;

    private String musicPath;
    private String music_artist;
    private String music_title;
    private String notification_msg;
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    private boolean isPause = true;
    private boolean isChangToNext;
    //private ChangeToNextReceiver changeToNextReceiver;
    private ProgressChangeReceiver progressChangeReceiver;
    /**
     * MediaPlayer是android中自带的一个播放器类,直接实例化后使用即可
     * 自定义的广播接收器
     */
    //private PlayReceiver playReceiver;
    /**
     * 发送广播的intent
     */
    private Intent intent_to_activity = new Intent("com.example.communication.RECEIVER");
    private Intent intent_to_progressBar = new Intent("com.example.communication.BAR");

    private Handler myHandler = new Handler() {
        /**
         * 运用到了Handler对各种消息的处理,主要是用它来更新UI
         * 自定义广播接收器类中,收到消息后,会给这个myHandler发送消息
         * 再由这个myHandler做统一的处理
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == PlayerMsg.PLAY_MSG) {
                current_position = mediaPlayer.getCurrentPosition();
                intent_to_progressBar.putExtra("position", current_position);
                sendBroadcast(intent_to_progressBar);
                myHandler.sendEmptyMessageDelayed(PlayerMsg.PLAY_MSG, 1200);
            }
            if (msg.what == PlayerMsg.PAUSE) {
                stopMusic();
            }
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        /**
         *更新接收进度条
         */
        notification_msg = null;

        //1.告诉系统，当这个广播到来时，用progressChangeReceiver来接收
        progressChangeReceiver = new ProgressChangeReceiver();

        //playReceiver = new PlayReceiver();
       // changeToNextReceiver = new ChangeToNextReceiver();

        //2.用过滤器IntentFilter来声明可匹配的广播类型
        IntentFilter intentPlayFilter = new IntentFilter();
        IntentFilter intentChangeFilter = new IntentFilter();
        //intentChangeFilter.addAction("com.example.communication.ChANGE_MUSIC");
        //intentPlayFilter.addAction("com.example.communication.PLAY");

        IntentFilter intentProgressChangeFilter = new IntentFilter();
        intentProgressChangeFilter.addAction("com.example.communication.PROGRESS_BAR");
        intentProgressChangeFilter.addAction("com.example.communication.PLAY");
        intentProgressChangeFilter.addAction("com.example.communication.ChANGE_MUSIC");



        //3.用registerReceiver(progressChangeReceiver, intentProgressChangeFilter);来注册
        registerReceiver(progressChangeReceiver, intentProgressChangeFilter);
        //字符串注意和activity中的一致.
        //registerReceiver(changeToNextReceiver, intentChangeFilter);
        //registerReceiver(playReceiver, intentPlayFilter);

        /**
         * 对MainActivity传进来的intent进行判断和处理
         */
        try {
            int msg = intent.getIntExtra("MSG", 0);
            musicPath = intent.getStringExtra("url");   //从intent中拿出歌曲的路径
            SendBroadcastToActivity(intent);
            if (msg == AppConstant.PlayerMsg.PLAY_MSG) {
                //向myHandler发送消息,由myHandler做出处理
                myHandler.sendEmptyMessage(PlayerMsg.PLAY_MSG);
                playMusic(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    private void playMusic(int position) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(musicPath);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MyPreparedListener(position));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MyPreparedListener implements MediaPlayer.OnPreparedListener {

        private int position;

        public MyPreparedListener(int position) {
            this.position = position;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            if (position >= 0)

            {
                mediaPlayer.seekTo(position);
                mediaPlayer.start();
            }
        }
    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void onDestory() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void SendBroadcastToActivity(Intent intent) {
        music_title = intent.getStringExtra("title");
        music_artist = intent.getStringExtra("artist");
        intent_to_activity.putExtra("title", intent.getStringExtra("title"));
        intent_to_activity.putExtra("artist", intent.getStringExtra("artist"));
        intent_to_activity.putExtra("album", intent.getStringExtra("album"));
        intent_to_activity.putExtra("album_id", intent.getLongExtra("album_id", 0));
        sendBroadcast(intent_to_activity);

    }

    /*private class PlayReceiver extends BroadcastReceiver {//播放与暂停广播接收器
        public PlayReceiver() {
            super();
        }
        @Override
        public void onReceive(Context context, Intent intent) {
            isPause = intent.getBooleanExtra("isPause", true);
            isChangToNext = intent.getBooleanExtra("isChangeToNext", false);

            if (isPause) {
                myHandler.sendEmptyMessage(PlayerMsg.PAUSE);
                Log.d("PlayerService","音乐暂停");
            } else {
                current_position = intent.getIntExtra("position", 0);
                playMusic(current_position);
                myHandler.sendEmptyMessage(PlayerMsg.PLAY_MSG);
                Log.d("PlayerService","音乐播放");
            }
        }
    }*/

    private class ProgressChangeReceiver extends BroadcastReceiver {
        /**
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == "com.example.communication.PROGRESS_BAR") {
                //#进度条的广播接收器
                current_position = intent.getIntExtra("current_position", 0);
                playMusic(current_position);
                Log.d("PlayerService","进度条发生改变");
                return;
            }

            if (intent.getAction() == "com.example.communication.PLAY") {
                //播放与暂停广播接收器
                isPause = intent.getBooleanExtra("isPause", true);
                isChangToNext = intent.getBooleanExtra("isChangeToNext", false);

                if (isPause) {
                    myHandler.sendEmptyMessage(PlayerMsg.PAUSE);
                    Log.d("PlayerService","音乐暂停");
                } else {
                    current_position = intent.getIntExtra("position", 0);
                    playMusic(current_position);
                    myHandler.sendEmptyMessage(PlayerMsg.PLAY_MSG);
                    Log.d("PlayerService","音乐播放");
                }
                return;
            }

            if (intent.getAction()=="com.example.communication.ChANGE_MUSIC"){
                //换歌广播接收器
                isChangToNext = intent.getBooleanExtra("isChangeToNext", false);
                Log.d("PlayerService","歌曲发生改变");
                if (isChangToNext) {
                    musicPath = intent.getStringExtra("music_url");
                    music_artist = intent.getStringExtra("music_artist");
                    music_title = intent.getStringExtra("music_title");
                    playMusic(0);

                }
                return;
            }

        }
    }

 /*   private class ChangeToNextReceiver extends BroadcastReceiver {    //换歌广播接收器

        public ChangeToNextReceiver() {
            super();
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            isChangToNext = intent.getBooleanExtra("isChangeToNext", false);
            Log.d("PlayerService","歌曲发生改变");
            if (isChangToNext) {
                musicPath = intent.getStringExtra("music_url");
                music_artist = intent.getStringExtra("music_artist");
                music_title = intent.getStringExtra("music_title");
                playMusic(0);

            }
        }

    }*/
}

