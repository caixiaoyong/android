package com.example.cy.musicplayer;

import android.content.ContentResolver;

import java.util.Iterator;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.cy.musicplayer.MyAdapter.formatTime;


/**
 * @author cy
 * @date 2021/1/26
 * 用到contentReslover来访问其他音乐程序提供的音乐文件以及数据
 */

public class FindSongs {
    // public static List<Mp3Info> getMp3Infos(Context context) {
    public  List<Mp3Info> getMp3Infos(ContentResolver contentResolver) {
        Log.d("Findsong","111111");
        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        List<Mp3Info> mp3Infos = new ArrayList<>();
        Log.d("Findsong","cursor.getCount() = " + cursor.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            //新建一个歌曲对象,将从cursor里读出的信息存放进去,直到取完cursor里面的内容为止.
            Mp3Info mp3Info = new Mp3Info();
            cursor.moveToNext();

            //音乐id
            long id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));
            //音乐标题
            String title = cursor.getString((cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE)));
            //艺术家
            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));
            //时长
            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));
            //文件大小
            long size = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE));
            //文件路径
            String url = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));
           /* //唱片图片
            String album = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM));
            //唱片图片ID
            long album_id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));*/
            //是否为音乐
            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
            //只把音乐添加到集合当中//&& duration/(1000 * 60) >= 1
            if (isMusic != 0 ) {
                mp3Info.setId(id);
                mp3Info.setTitle(title);
                mp3Info.setArtist(artist);
                mp3Info.setDuration(duration);
                mp3Info.setSize(size);
                mp3Info.setUrl(url);
                /*mp3Info.setAlbum(album);
                mp3Info.setAlbum_id(album_id);*/
                mp3Infos.add(mp3Info);
            }
        }
        Log.d("Findsong","aaaa");
        Log.d("Findsong","mp3Infos.size = " + mp3Infos.size());
        return mp3Infos;
    }

    /**
     * 往List集合中添加Map对象数据，每一个Map对象存放一首音乐的所有属性
     * @param mp3Infos
     * @return
     */
/*    public static List<HashMap<String, String>> getMusicMaps(
            List<Mp3Info> mp3Infos) {
        List<HashMap<String, String>> mp3list = new ArrayList<HashMap<String, String>>();
        String music_menu = String.valueOf(R.drawable.music_menu);
        String check_music = String.valueOf(R.drawable.check);
        //定义歌曲的序号
        int i = 0;
        for (Iterator iterator = mp3Infos.iterator(); iterator.hasNext();) {
            i++;
            Mp3Info mp3Info = (Mp3Info) iterator.next();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("number",String.valueOf(i));
            map.put("id",String.valueOf(mp3Info.getId()));
            map.put("title", mp3Info.getTitle());
            map.put("Artist", mp3Info.getArtist());
            map.put("duration", formatTime(mp3Info.getDuration()));
            map.put("size", String.valueOf(mp3Info.getSize()));
            map.put("url", mp3Info.getUrl());
            map.put("music_menu",music_menu);
            map.put("check_music",check_music);
            mp3list.add(map);
        }
        return mp3list;
    }*/

    /**
     * 格式化时间，将毫秒转换为分:秒格式
     * @
     * @return
     */
   /* public static String formatTime(long time) {
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";
        if (min.length() < 2) {
            min = "0" + time / (1000 * 60) + "";
        } else {
            min = time / (1000 * 60) + "";
        }
        if (sec.length() == 4) {
            sec = "0" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 3) {
            sec = "00" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 2) {
            sec = "000" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 1) {
            sec = "0000" + (time % (1000 * 60)) + "";
        }
        return min + ":" + sec.trim().substring(0, 2);
    }
*/

    public void setListAdpter(Context mcontext, List<Mp3Info> mp3Infos, ListView mLv) {

        List<HashMap<String, String>> mp3list = new ArrayList<HashMap<String, String>>();
        MyAdapter mAdapter = new MyAdapter(mcontext, mp3Infos);
        mLv.setAdapter(mAdapter);
    }

}
