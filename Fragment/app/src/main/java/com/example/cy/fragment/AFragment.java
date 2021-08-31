package com.example.cy.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



/**
 * Created by cy on 21-1-18.
 */

public class AFragment extends Fragment {
    private TextView mTvTitle;
   // 1.private Activity mActivity;
    private Button mBtnChange,mBtnReset,mBtnMessage;
    private Fragment bFragment;
    private IOnMessageClick listener;

    public static AFragment newInstance(String title){//2.实例化一个有参构造
        AFragment fragment=new AFragment();
        Bundle bundle=new Bundle();
        bundle.putString("title",title);
        fragment.setArguments(bundle);
        return fragment;
    }

    public interface IOnMessageClick{//5.声明这么个接口，让Activity去实现他
        void onClick(String text);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 重写onCreateView()方法  方法中调用:inflater.inflate()方法加载Fragment的布局文件
        View view=inflater.inflate(R.layout.fragment_a,container,false);
        Log.d("AFragment","---onCreateView---");
        return view;//返回加载的view对象
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        mTvTitle=view.findViewById(R.id.tv_title);
        //3
        mBtnChange=view.findViewById(R.id.btn_change);
        mBtnReset=view.findViewById(R.id.btn_reset);

        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bFragment==null){
                    //实例化Afragment
                    bFragment=new BFragment();
                }
                Fragment fragment=getFragmentManager().findFragmentByTag("a");
                if (fragment!=null){//调用addToBackStack可以从bfragment返回activity
                    getFragmentManager().beginTransaction().hide(fragment).add(R.id.fl_container,bFragment).addToBackStack(null).commitAllowingStateLoss();

                }else {
                    //把BFragment替换到Activity中
                    getFragmentManager().beginTransaction().replace(R.id.fl_container,bFragment).addToBackStack(null).commitAllowingStateLoss();
                }

            }
        });
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvTitle.setText("这是一个新文字");
            }
        });

        mBtnMessage=view.findViewById(R.id.btn_message);
        mBtnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //4.调用Activity,给Activity传递信息
//                ((ContainerActivity)getActivity()).setData("hi");
                listener.onClick("hi");//5.点击按钮，调用接口onclick方法把数据传出去
            }
        });

        if(getArguments()!=null){//2.getArguments得到bundle
            mTvTitle.setText(getArguments().getString("title"));
        }
    }

    @Override
    public void onAttach(Context context) {//5.给listener赋值
        super.onAttach(context);
        try {
            listener= (IOnMessageClick) context;
        }catch(ClassCastException e){
            throw new ClassCastException("Activity 必须实现 IOMessageClick接口");
        }

    }
    /*1.   @Override
    public void onAttach(Context context) {//和Activity建立关系
        super.onAttach(context);
  //      mActivity= (Activity) context;
    }

    @Override
    public void onDetach() {//和Activity脱离关系
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消异步
    }*/
}
