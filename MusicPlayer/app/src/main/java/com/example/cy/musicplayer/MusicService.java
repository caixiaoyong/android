package com.example.cy.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import java.io.IOException;

/**
 * @author cy
 * @date 2021/01/22
 */
public class MusicService extends Service {
    static boolean isplay;
    MediaPlayer player;



    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.love);
        super.onCreate();

        /*try {
            if (player == null) {
                player.setDataSource(curMusic);
                player.prepare();
                player.start();
            }else if (player.isPlaying()){
                player.stop();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            player.reset();
                            player.setDataSource(curMusic);
                            player.prepare();
                            player.start();
                        } catch (IOException e) {
                            curMusic="/home/cy/AndroidStudioProjects/MusicPlayer/app/src/main/res/raw";
                            e.printStackTrace();
                        }
                    }
                },1000);
            }else {
                player=new MediaPlayer();
                player.setDataSource(curMusic);
                player.prepare();
                player.start();
            }
        } catch (IOException e) {
            curMusic="/home/cy/AndroidStudioProjects/MusicPlayer/app/src/main/res/raw";
        }*/

    }


    /**
     * 使用onBinder方法返回的对象
     */
    public class MyBinder extends Binder {

        public boolean isPlaying() {
            return player.isPlaying();
        }

        /**
         * 播放或暂停歌曲
         */
        public void play() {
            if (player.isPlaying()) {
                player.pause();
            } else {
                player.start();
            }
        }

        /**
         * 返回歌曲的长度，单位为毫秒
         */
        public int getDuration() {
            return player.getDuration();
        }

        /**
         * 返回歌曲目前的进度，单位为毫秒
         */
        public int getCurrenPostion() {
            return player.getCurrentPosition();
        }

        /**
         * 可以通过进度条设置歌曲播放的进度，单位为毫秒
         */
        public void seekTo(int mesc) {
            player.seekTo(mesc);
        }
    }

   /* @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!player.isPlaying()){
            player.start();
            isplay=player.isPlaying();
        }
        return super.onStartCommand(intent, flags, startId);
    }*/


}
