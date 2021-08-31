package com.example.cy.listviewadapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {


    private Context mContext;
    private ListView mLv;
    private List<Icon> mData=null;
    private ListViewAdapter mAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mContext=ListViewActivity.this;
        mLv =findViewById(R.id.li_main);
        mData=new LinkedList<Icon>();

        mData.add(new Icon("red car",R.drawable.car1));
        mData.add(new Icon("gray car",R.drawable.car2));
        mData.add(new Icon("yello car",R.drawable.car3));
        mAdapter=new ListViewAdapter((LinkedList<Icon>)mData,mContext);
        mLv.setAdapter(mAdapter);

    }
}
