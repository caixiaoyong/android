package com.example.cy.musicexample;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyMusicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyMusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyMusicFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FindSongs finder;           //查找歌曲的类的实例
    private Activity MyActivity;
    private List<Mp3Info> mp3Infos;
    private MusicListAdapter musicListAdapter;


    private OnFragmentInteractionListener mListener;

    public MyMusicFragment() {
        // Required empty public constructor
    }

    public static MyMusicFragment newInstance(String param1, String param2) {
        MyMusicFragment fragment = new MyMusicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //获取ListView的实例之后,设置adapter
        super.onCreate(savedInstanceState);
        MyActivity = getActivity();
        finder = new FindSongs();
        mp3Infos = finder.getMp3Infos(MyActivity.getContentResolver());
        musicListAdapter = new MusicListAdapter(MyActivity.getApplicationContext(),mp3Infos);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_music, container, false);
        finder = new FindSongs();
        //切换至我的音乐Fragment
        rootView.findViewById(R.id.top_layout_right_ImageView).
                setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMyMusicFragmentInteraction(AppConstant.PlayerMsg.BACK_TO_MAIN_FRAGMENT);
                Log.d("MyMusicFragment","返回MainFragment!!!");
            }
        });
        /*
        音乐列表的点击监听器
        点击后调用的方法,是一个回调方法,用来告诉activity
        列表里面的哪个项被点击了
        让activity做出反应
         */
        ((ListView)rootView.findViewById(R.id.music_list)).
                setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (mp3Infos != null) {
                            mListener.onMyMusicFragmentInteraction(AppConstant.PlayerMsg.LIST_CLICK, position);
                            Log.d("MyMusicFragment","11111");
                        }
                    }
                });

        //方法为setListAdapter,用来给一个ListView设置adapter
        finder.setListAdpter(MyActivity.getApplicationContext(),
                mp3Infos,(ListView)rootView.findViewById(R.id.music_list));
        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name

        public void onMyMusicFragmentInteraction(int msg);
        //这个方法要在MainActivity中再次重写一遍

       public void onMyMusicFragmentInteraction(int msg,int position);

       // void onFragmentInteraction(Uri uri);

       // void onMyMusicFragmentInteraction(int backToMainFragment);
    }
}
