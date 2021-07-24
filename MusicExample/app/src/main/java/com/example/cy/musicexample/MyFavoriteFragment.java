package com.example.cy.musicexample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class MyFavoriteFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public MyFavoriteFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MyFavoriteFragment newInstance() {
        MyFavoriteFragment fragment = new MyFavoriteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_favorite, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        //返回到主Fragment
        view.findViewById(R.id.favorite_fragment_to_main_fragment)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainFragment mainFragment = new MainFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_layout, mainFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                });

   /*     ((ListView)view.findViewById(R.id.favorite_music_list)).setOnClickListener(
                //我的最爱
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mListener.onFavoriteFragmentInteraction(AppConstant.PlayerMsg.LIST_CLICK,position);
                    }
                });
          *//*
        更新列表的adapter
        给列表设置一个新的adapter
         *//*
        MainActivity.myDataBase.Update_adapter();
        MainActivity.myDataBase.SetAdapter((ListView)View.findViewById(R.id.favorite_music_list));
*/
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFavoriteFragmentInteraction(int msg,int position);
    }
}
