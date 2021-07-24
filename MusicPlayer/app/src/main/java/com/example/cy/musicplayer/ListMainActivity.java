package com.example.cy.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;

/**
 * @author cy
 * @date 2021/1/26
 */


public class ListMainActivity extends AppCompatActivity {
    /**
     * 查找歌曲的类的实例
     */
    private FindSongs finder;
    private Context mContext;
    private ListView mLv;
     private List<Mp3Info> mp3Infos;
    private List<HashMap<String,Object>>mp3list;
    private HashMap<String,Object>map;
    private MyAdapter myAdapter;
    /**
     * 记录当前播放器的状态
     */
    private boolean isPause;
    private static int music_position;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_view);


        music_position=0;

        mLv=findViewById(R.id.music_list);
        //finder=new FindSongs();
        //获取歌曲对象集合
      // mp3Infos=FindSongs.getMp3Infos(getApplicationContext());
        //显示歌曲列表
       // setListAdpter(FindSongs.getMusicMaps(mp3Infos));
       // myAdapter=new MyAdapter(getApplicationContext(),mp3Infos);

        finder=new FindSongs();
        //mContext=ListMainActivity.this;

        mp3Infos=finder.getMp3Infos(getContentResolver());
        myAdapter=new MyAdapter(getApplicationContext(),mp3Infos);
        finder.setListAdpter(getApplicationContext(),mp3Infos,mLv);

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               if(l==AppConstant.PlayerMsg.LIST_CLICK){
                   if (mp3Infos != null) {
                       isPause = false;
                       initService(i);
                   }

               }


                    String str=mLv.getItemAtPosition(i)+"";
                Log.d("ListMainActivity","OnItemClick功能实现！\n"+str);


            }

            private void initService(int i) {
                music_position = i;
                Mp3Info mp3Info = mp3Infos.get(i);
                Intent intent=new Intent();
                intent.putExtra("url", mp3Info.getUrl());
                intent.putExtra("title", mp3Info.getTitle());
                intent.putExtra("artist", mp3Info.getArtist());
                intent.putExtra("MSG", AppConstant.PlayerMsg.PLAY_MSG);
                intent.setClass(ListMainActivity.this, MusicService.class);
                startService(intent);
            }
        });
    }

    /*public void setListAdpter(List<HashMap<String, String>> mp3list) {
        myAdapter = new MyAdapter(this, mp3list,
                R.layout.listview_item, new String[]{"title","music_duration"
                ,"Artist" }, new int[]{R.id.music_title,R.id.music_duration,
                R.id.music_Artist});
        mLv.setAdapter(myAdapter);

    }*/
}
