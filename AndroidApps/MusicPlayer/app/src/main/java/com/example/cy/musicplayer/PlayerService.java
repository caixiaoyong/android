package com.example.cy.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class PlayerService extends Service implements AppConstant {
    private int current_position;

    private String musicPath;
    private String music_artist;
    private String music_title;
    private String notification_msg;
    private  MediaPlayer mediaPlayer;

    public PlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer = new MediaPlayer();                        //MediaPlayer是android中自带的一个播放器类,直接实例化后使用即可

        try {
            int msg = intent.getIntExtra("MSG", 0);
            musicPath = intent.getStringExtra("url");   //从intent中拿出歌曲的路径

            if (msg == AppConstant.PlayerMsg.PLAY_MSG) {
                playMusic(1);
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
            if (position >=0)

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

}
