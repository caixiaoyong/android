package com.example.cy.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * @author cy
 * @date
 */
public class MusicService extends Service {

    /**
     * 记录当前播放状态isplay
     * 设置全局MediaPlayer对象
     */
    static boolean isplay;
    MediaPlayer player;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {//创建服务
        //创建MediaPlayer对象，并加载播放的音频文件
        player=MediaPlayer.create(this,R.raw.music);
        Log.d("MusicService","onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//服务开始运行
        if(!player.isPlaying()){
            player.start();//播放音乐
            Log.d("MusicService","onStartCommad");
            //设置当前状态为正在播放音乐
            isplay=player.isPlaying();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {//服务被停止
        player.stop();//停止音乐的播放
        Log.d("MusicService","stopSelf");
        //设置当前状态为停止播放音乐
        isplay=player.isPlaying();
        player.release();//释放资源
        Log.d("MusicService","stopService");
        super.onDestroy();
    }
}
