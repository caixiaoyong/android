package com.example.cy.musicplayer;

/**
 * Created by cy on 21-1-25.
 */

public interface AppConstant {
    public class PlayerMsg{
        public static final int PLAY_MSG = 1;                      //开始播放
        public static final int PAUSE = 2;                         //暂停播放
        public static final int PREVIOUS_MUSIC = 3;                //上一首
        public static final int NEXT_MUSIC = 4;                    //下一首
        public static final int LOOP_MODE = 5;                     //循环播放
        public static final int RANDOM_MODE = 6;                   //随机播放
        public static final int CHANGE_TO_MY_MUSIC_FRAGMENT=7;     //更换fragment消息
        public static final int LIST_CLICK = 8;                    //列表点击
        public static final int BACK_TO_MAIN_FRAGMENT=9;           //回退到主fragment
        public static final int DISMISS_CLICK = 10;                //回退到主fragment
        public static final int FRAGMENT_RANDOM_PLAY = 11;         //小卷毛点歌
        public static final int ADD_TO_FAVORITE = 12;              //加入我的最爱
        public static final int DELETE_FROM_FAVORITE = 13;         //删除我的最爱
    }

    public class NotificationMsg{
        public static final String NOTIFICATION_PREVIOUS_MUSIC = "PREVIOUS";
        public static final String NOTIFICATION_NEXT_MUSIC = "NEXT";
        public static final String NOTIFICATION_PAUSE_MUSIC = "PLAY";
        public static final String NOTIFICATION_EXIT = "EXIT";
    }

}

