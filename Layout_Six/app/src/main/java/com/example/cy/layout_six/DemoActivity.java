package com.example.cy.layout_six;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.cy.layout_six.recyclerview.RecyclerViewActivity;

public class DemoActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView mTv1,mTv2;
    private ToggleButton tbtn_open;
    private Switch swh_status;
    private ImageView img_pgbar;
    private AnimationDrawable ad;
    private Button mBtnhandler,mBtnrecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mTv1=findViewById(R.id.tv_1);
        mTv2=findViewById(R.id.tv_2);

        tbtn_open=findViewById(R.id.tbtn_open);
        swh_status=findViewById(R.id.swh_status);
        tbtn_open.setOnCheckedChangeListener(this);
        swh_status.setOnCheckedChangeListener(this);

        img_pgbar=findViewById(R.id.img_1);//进度条
        ad= (AnimationDrawable) img_pgbar.getDrawable();
        img_pgbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                ad.start();
            }
        },100);//Handler中postDelayed(Runnable action, long delayMillis)

        //中划线
        mTv1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //下划线
        mTv2.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        mBtnhandler=findViewById(R.id.hander);
        mBtnhandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DemoActivity.this,HandlerActivity.class);
                startActivity(intent);
            }
        });
        mBtnrecycler=findViewById(R.id.btn_recyclerview);
        mBtnrecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DemoActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.tbtn_open:
                if(compoundButton.isChecked()) Toast.makeText(this,"关闭声音",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"打开声音",Toast.LENGTH_SHORT).show();
                break;
            case R.id.swh_status:
                if(compoundButton.isChecked()) Toast.makeText(this,"开关:OFF",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"开关:ON",Toast.LENGTH_SHORT).show();
                break;

        }
    }



}
