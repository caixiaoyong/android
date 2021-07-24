package com.example.cy.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cy on 21-1-22.
 */

public class MyAdapter extends BaseAdapter {
    private Context mcontext;
    private List<Mp3Info> mp3Infos;
    private Mp3Info mp3Info;
    private int pos=-1; //列表位置
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context,List<Mp3Info> mp3Info){
        this.mcontext=context;
        layoutInflater=LayoutInflater.from(context);
        this.mp3Infos=mp3Info;
    }

    public MyAdapter(ListMainActivity listMainActivity, List<HashMap<String, String>> mp3list, int listview_item, String[] strings, int[] ints) {

    }

    @Override
    public int getCount() {
        return mp3Infos.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder{
        //为了使用方便,将多个控件放在了一个类里面
        /*ImageView check;
        TextView number;
        TextView music_title;
        TextView music_Artist;
        ImageView music_menu;*/
       // ImageButton list_down_button;
        TextView music_Artist;
        TextView music_title;
        TextView music_duration;
        ImageView album_image;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view == null){
            view=layoutInflater.inflate(R.layout.listview_item,null);
            /*holder.music_title = view.findViewById(R.id.music_title);
            holder.music_Artist = view.findViewById(R.id.music_Artist);
            holder.music_menu = view.findViewById(R.id.music_menu);
            holder.number = view.findViewById(R.id.number);
            holder.check = view.findViewById(R.id.check);*/
            holder = new ViewHolder();
            holder.music_title = view.findViewById(R.id.music_title);
            holder.music_Artist = view.findViewById(R.id.music_Artist);
            holder.music_duration = view.findViewById(R.id.music_duration);
            view.setTag(holder);

        }else {
            holder= (ViewHolder) view.getTag();

        }
        mp3Info=mp3Infos.get(i);
        //显示标题
        holder.music_title.setText(mp3Info.getTitle());
        //显示艺术家
        holder.music_Artist.setText(mp3Info.getArtist());

       // holder.music_menu.setBackgroundResource(mp3Info.);
        //显示长度
        holder.music_duration.setText(String.valueOf(formatTime(mp3Info.getDuration())));
        return view;
    }


    public static String formatTime(Long time){
        //将歌曲的时间转换为分秒的制度
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";

        if(min.length() < 2){
            min = "0" + min;
            switch (sec.length()){
                case 4:
                    sec = "0" + sec;
                    break;
                case 3:
                    sec = "00" + sec;
                    break;
                case 2:
                    sec = "000" + sec;
                    break;
                case 1:
                    sec = "0000" + sec;
                    break;
            }
        }

        return min + ":" + sec.trim().substring(0,2);
    }
}
