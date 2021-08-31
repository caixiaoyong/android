package com.example.cy.listviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by cy on 21-1-12.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context mcontext;
    private LinkedList<Icon> mData;
    /**
     * 将XML布局文件实例化为相应的 View 对象
     */
    private LayoutInflater mLayoutInflater;

    public ListViewAdapter(LinkedList<Icon> Data,Context context){
        this.mcontext=context;
        mLayoutInflater=LayoutInflater.from(context);
        this.mData=Data;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


   /* public View getView(int i, View view, ViewGroup viewGroup) {
        //在应用中自定义一个view，需要获取这个view的布局
        view=mLayoutInflater.inflate(R.layout.activity_list_item,null);
        ImageView img_Icon=view.findViewById(R.id.img_icon);
        TextView tv_Name=view.findViewById(R.id.tv_name);
        img_Icon.setBackgroundResource(mData.get(i).getIcon());
        tv_Name.setText(mData.get(i).getName());
        return view;
    }*/

   //BaseAdapter的优化
    static class ViewHolder{
// getView()会被调用多次，那么findViewById一样得调用多次，优化：添加ViewHolder组件
       ImageView img_Icon;
       TextView tv_Name;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
//原先界面上有多少个Item，getView方法就会被调用多次，优化后：复用view

        if (view == null) {
            //在应用中自定义一个view，需要获取这个view的布局

            /*inflate()的作用就是将一个用xml定义的布局文件查找出来，注意与findViewById()的区别，
            inflate是加载一个布局文件，而findViewById则是从布局文件中查找一个控件*/
            view=mLayoutInflater.inflate(R.layout.activity_list_item,null);
            holder=new ViewHolder();
            holder.img_Icon=view.findViewById(R.id.img_icon);
            holder.tv_Name=view.findViewById(R.id.tv_name);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        holder.img_Icon.setBackgroundResource(mData.get(i).getIcon());
        holder.tv_Name.setText(mData.get(i).getName());
        return view;
    }

}
