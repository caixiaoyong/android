package com.example.cy.fragment.mainActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cy.fragment.R;

/**
 * Created by cy on 21-1-19.
 */

public class MyFragment extends Fragment {

    private TextView mTvTitle;

    private String mcontent;
    /*public MyFragment() {
        super();
    }*/


    public MyFragment(String content){
        this.mcontent=content;
    }
   /* public void setContent(String content) {
        this.mcontent=content;
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fg_content,container,false);
       mTvTitle=view.findViewById(R.id.txt_content);
       mTvTitle.setText(mcontent);
        Log.d("Myfragment","111111");
        return view;

    }
}
