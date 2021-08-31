package com.example.cy.fragment.mainActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.cy.fragment.R;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup rg_tab;
    private RadioButton rb_channel;

    private MyFragment Afragment,Bfragment,Cfragment,Dfragment;
    private FragmentManager fManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager=getFragmentManager();
        rg_tab=findViewById(R.id.rg_tab_bar);
        rg_tab.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb_channel=findViewById(R.id.rb_channel);
        rb_channel.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction fTransaction=fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (i){
            case R.id.rb_channel:
                if (Afragment==null){
                    //对MyFragment进行判空，如果为空，初始化，并添加到容器中
                    Afragment=new MyFragment("第一个Fragment");
                    fTransaction.add(R.id.fl_activity,Afragment);
                }else{//如果MyFragment不为空，我们就将MyFragment显示出来；
                    fTransaction.show(Afragment);
                }
                break;
            case R.id.rb_message:
                if(Bfragment == null){
                    Bfragment = new MyFragment("第二个Fragment");
                    fTransaction.add(R.id.fl_activity,Bfragment);
                }else{
                    fTransaction.show(Bfragment);
                }
                break;
            case R.id.rb_better:
                if(Cfragment == null){
                    Cfragment = new MyFragment("第三个Fragment");
                    fTransaction.add(R.id.fl_activity,Cfragment);
                }else{
                    fTransaction.show(Cfragment);
                }
                break;
            case R.id.rb_setting:
                if(Dfragment == null){
                    Dfragment = new MyFragment("第四个Fragment");
                    fTransaction.add(R.id.fl_activity,Dfragment);
                }else{
                    fTransaction.show(Dfragment);
                }
                break;
        }
        fTransaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fTransaction1) {
        //如果MyFragment不为空，我们就将MyFragment显示出来；
        if (Afragment!=null)fTransaction1.hide(Afragment);
        if (Bfragment!=null)fTransaction1.hide(Bfragment);
        if (Cfragment!=null)fTransaction1.hide(Cfragment);
        if (Dfragment!=null)fTransaction1.hide(Dfragment);
    }
}
