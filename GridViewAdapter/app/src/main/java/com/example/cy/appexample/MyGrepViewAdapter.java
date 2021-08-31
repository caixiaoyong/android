package com.example.cy.appexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by cy on 21-1-11.
 */

public class MyGrepViewAdapter extends BaseAdapter {//自定义baseAdapter
    private Context mContext;//Context是维持android各组件能够正常工作的一个核心功能类。
    private LayoutInflater mLayoutInflater;

    public MyGrepViewAdapter(Context context){
        this.mContext=context;
        mLayoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {//列表长度
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView textView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){//在应用中自定义一个view，需要获取这个view的布局
            view=mLayoutInflater.inflate(R.layout.layout_grid_item,null);
            holder=new ViewHolder();
            holder.imageView=view.findViewById(R.id.iv_grid);
            holder.textView=view.findViewById(R.id.tv_title);
            view.setTag(holder);//将Holder存储到view中
        }else {
            holder= (ViewHolder) view.getTag();
        }
        //赋值
        holder.textView.setText("呵呵！呵！");
        Glide.with(mContext).load("https://iconfont.alicdn.com/t/e309cb06-d26d-441c-8ea0-4d763fe2acab.png").into(holder.imageView);
        return view;
    }
}
