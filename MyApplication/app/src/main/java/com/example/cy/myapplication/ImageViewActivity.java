package com.example.cy.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageViewActivity extends AppCompatActivity {

    private ImageView mIv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        mIv1=findViewById(R.id.iv_3);
       /* Glide.with(this).load("https://iconfont.alicdn.com/t/e309cb06-d26d-441c-8ea0-4d763fe2acab.png").into(mIv1);*/

    }
}
