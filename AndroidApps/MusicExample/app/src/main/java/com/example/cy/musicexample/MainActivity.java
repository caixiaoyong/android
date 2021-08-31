package com.example.cy.musicexample;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cy
 */
public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener
        , MyMusicFragment.OnFragmentInteractionListener {
    private String music_url;                  //记录音乐的路径
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private MainFragment mainFragment;
    private MyMusicFragment myMusicFragment;
    private boolean isPause;                   //记录当前播放器的状态
    private boolean isChangToNext;             //下一首按钮
    private FindSongs finder;                  //查找歌曲的类
    public List<Mp3Info> mp3Infos;             //歌曲列表

    public static int music_position;          //音乐的位置
    private int current_position;              //当前进度条的位置
    private int current_position_bar;
    private int play_mode;                     //播放模式

    private TextView music_info_textView;      //显示歌曲信息的textview
    private TextView singer_info_textView;     //显示歌手信息的textview
    private SeekBar seek_bar;                  //进度条控件
    private MsgReceiver msgReceiver;           //service发过来的广播接收器
    private BarReceiver barReceiver;           //进度条的接收器

    private static final int PERMISSION_REQUEST = 1;// 检查权限
    private RotateAnimation animation;
private ImageView img0;
    private ImageButton play_button;            //播放按钮控件
    private ImageButton next_song_button, previous_song_button, play_mode_button;
    private PopupWindow popupPlayModeWindow;    //播放模式下拉窗口

    private Intent intent_to_service;           //向service发送广播的intent
    private Intent progress_change_intent_to_service;    //先来一个发送广播的Intent
    private Intent intent_to_changeMusic;                //换歌曲

    String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    List<String> unPermissionList = new ArrayList<>();
    private void checkPermission() {
      /*  unPermissionList.clear();
        //判断哪些权限未授予
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                unPermissionList.add(permissions[i]);
                Log.d("MainActivity","unPermissionList"+unPermissionList);
            }
        }
        *//**
         * 判断是否为空
         *//*
        if (!unPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            String[] permissions = unPermissionList.toArray(new String[unPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(MainActivity.this, permissions, PERMISSION_REQUEST);
        }
        else
        {
            mp3Infos=finder.getMp3Infos(getContentResolver());
        }*/
        //判断写入权限是否未授予
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        else
        {

            mp3Infos=finder.getMp3Infos(getContentResolver());

        }
    }
    /**
     * 响应授权
     * 这里不管用户是否拒绝，都进入首页，不再重复申请权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                for (String per : permissions) {
                    Log.d("aaaa", "per = " + per);
                }
                for (int result : grantResults) {
                    Log.d("aaaa", "result = " + result);
                }
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 权限被用户同意，可以做你要做的事情了。
                    mp3Infos=finder.getMp3Infos(getContentResolver());
                    Log.d("MainActivity","getContentResolver"+finder.getMp3Infos(getContentResolver()));
                } else {
                    // 权限被用户拒绝了，可以提示用户,关闭界面等等。
                    finish();
                    Toast.makeText(this,"denied！",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
        Log.d("aaaa","aaaaa");
    }


    public static boolean isFavorite = false;            //当前音乐是否为我的最爱

    /*
    这个方法是activity和fragment通信的一种方法
    在MainFragment中调用这个方法,可以在activity中做出相应的反应
     */
    @Override
    public void onFragmentInteraction(Uri uri) {


    }

    @Override
    public void onMainFragmentInteraction(int msg) {



        /*
        参数msg做出判断,如果为CHANGE_TO_MY_MUSIC_FRAGMENT
        则执行跳转
         */
        if (msg == AppConstant.PlayerMsg.CHANGE_TO_MY_MUSIC_FRAGMENT) {

            /*
            在这里并没有直接切换Fragment
            而是调用了activity实现MyMusicFragment的那个接口
            对后面的开发能带来一点便利之处
             */
            onMyMusicFragmentInteraction(AppConstant.PlayerMsg.CHANGE_TO_MY_MUSIC_FRAGMENT);
        }
    }

    @Override
    public void onMyMusicFragmentInteraction(int msg) {
        /**
         * 实现两个fragment之间的自由切换
         * 增删替换Fragment的话，需要借助FragmentTransaction对象，最后操作完成后再使用commit（）方法提交事物。
         */

        //创建了MyMusicFragment的实例
        myMusicFragment = new MyMusicFragment();
        //获得FragmentManager对象
        FragmentManager fragmentManager = getFragmentManager();
        //获得FragmentTransaction对象
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (msg == AppConstant.PlayerMsg.CHANGE_TO_MY_MUSIC_FRAGMENT) {

            fragmentTransaction.replace(R.id.fragment_layout, myMusicFragment);
            //将被替换的MainFragment加入到一个专门存放fragment的栈中
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
           //indatae();
            Log.d("MainActivity","切换到MusicFragment...");
        }


        if (msg == AppConstant.PlayerMsg.BACK_TO_MAIN_FRAGMENT) {

            fragmentTransaction.replace(R.id.fragment_layout, mainFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            indatae();
            Log.d("MainActivity","返回MainFragment```");
        }
    }

    /**
     * 接收MusciFragment的回调方法列表里哪项被点击
     * @param msg
     * @param position
     */

    @Override
    public void onMyMusicFragmentInteraction(int msg, int position) {
        if (msg == AppConstant.PlayerMsg.LIST_CLICK) {
            if (mp3Infos != null) {
                isPause = false;
                initService(position);

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //IntentFilter声明匹配的广播类型
        IntentFilter intentMsgFilter = new IntentFilter();
        IntentFilter intentBarFilter = new IntentFilter();

        //注册歌曲信息的广播接收器
        intentMsgFilter.addAction("com.example.communication.RECEIVER");
        //3.对IntentFilter操作,并将其与第一步定义的接收器绑定在一起.
        registerReceiver(msgReceiver, intentMsgFilter);

        //注册进度条的广播接收器
        intentBarFilter.addAction("com.example.communication.BAR");
        registerReceiver(barReceiver, intentBarFilter);
        //MainFragment mainFragment=new MainFragment();
        //img0 = mainFragment.getView().findViewById(R.id.img_1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        finder = new FindSongs();
        checkPermission();

        progress_change_intent_to_service = new Intent("com.example.communication.PROGRESS_BAR");

        seek_bar=findViewById(R.id.process_bar);
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //#进度条拖动响应
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //获取拖动条当前值
                current_position_bar = seekBar.getProgress();
                progress_change_intent_to_service.putExtra("current_position", current_position_bar);
                sendBroadcast(progress_change_intent_to_service);
            }
        });
        play_mode = AppConstant.PlayerMsg.LOOP_MODE;
        next_song_button = findViewById(R.id.next_song_button);
        next_song_button.setOnClickListener(new View.OnClickListener() {
            //下一首按钮的监听器
            @Override
            public void onClick(View v) {

                changeMusic(play_mode, AppConstant.PlayerMsg.NEXT_MUSIC, mp3Infos);
                indatae();
                Log.d("MainActivity","切换到下一首，旋转");
            }
        });
        previous_song_button = findViewById(R.id.previous_song_button);
        previous_song_button.setOnClickListener(new View.OnClickListener() {
            //上一首按钮监听
            @Override
            public void onClick(View v) {
                changeMusic(play_mode, AppConstant.PlayerMsg.PREVIOUS_MUSIC, mp3Infos);
                indatae();
                Log.d("MainActivity","切换到上一首，旋转");

            }
        });


        play_mode_button = findViewById(R.id.play_mode_button);
        play_mode_button.setImageResource(R.drawable.play_mode_photo);

        play_mode_button.setOnClickListener(new View.OnClickListener() {
            //播放模式按钮监听器
            @Override
            public void onClick(View v) {

                //如果弹出式窗口显示了,点击之后就关闭弹出式窗口
                if (popupPlayModeWindow.isShowing()) {
                    //获取isShowing显示状态 ，dismiss隐藏
                    popupPlayModeWindow.dismiss();
                } else {
                /*直接改变记录播放模式的变量值*/
                    if (play_mode == AppConstant.PlayerMsg.LOOP_MODE) {
                        Toast.makeText(getApplicationContext(), "当前模式为循环播放模式", Toast.LENGTH_SHORT).show();
                    }

                    if (play_mode == AppConstant.PlayerMsg.RANDOM_MODE) {
                        Toast.makeText(getApplicationContext(), "当前模式为随机播放模式", Toast.LENGTH_SHORT).show();
                    }
                    // 相对某个控件的位置（正左下方），无偏移
                    popupPlayModeWindow.showAsDropDown(v);
                }
            }
        });



        View play_mode_window = this.getLayoutInflater().inflate(R.layout.popup_window_layout, null);
        popupPlayModeWindow = new PopupWindow(play_mode_window, 280, 360);


        music_position = 0;
        current_position = 0;
        finder = new FindSongs();
        msgReceiver = new MsgReceiver();
        barReceiver = new BarReceiver();

        //fragmentManager.findFragmentById(R.id.img_1).getView().findViewById(R.id.img_1);

        animation = new RotateAnimation(0, 360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);

      //  mp3Infos = finder.getMp3Infos(getContentResolver());
        intent_to_service = new Intent("com.example.communication.PLAY");
        //向service发送了切换歌曲的广播
        intent_to_changeMusic = new Intent("com.example.communication.ChANGE_MUSIC");
        seek_bar = findViewById(R.id.process_bar);
        play_button = findViewById(R.id.play_button);
        play_button.setImageResource(R.drawable.play_photo);

        play_button.setOnClickListener(new View.OnClickListener() {
            /**
             * 将音乐播放到了的当前位置和当前的状态,以广播的形式发送给service
             * 让service可以接着刚才暂停的位置开始播放,而不是重新播放
             * @param v
             */
            @Override
            public void onClick(View v) {
                if (isPause) {
                    isPause = false;
                    play_button.setImageResource(R.drawable.pause_photo);
                    indatae();
                } else {
                    isPause = true;
                    play_button.setImageResource(R.drawable.play_photo);
                    animation.cancel();
                }
                intent_to_service.putExtra("position", current_position);
                intent_to_service.putExtra("isPause", isPause);
                sendBroadcast(intent_to_service);
            }
        });

        //创建了刚才定义的MainFragment实例
        mainFragment = new MainFragment();
        //得到FragmentManager
        fragmentManager = getFragmentManager();
        //得到fragmentTransaction,用于管理fragment的切换
        fragmentTransaction = fragmentManager.beginTransaction();
        //将MainActivity里的布局模块fragment_layout替换为mainFragment
        fragmentTransaction.replace(R.id.fragment_layout, mainFragment).commit();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initService(int position) {
        music_position = position;
        Mp3Info mp3Info = mp3Infos.get(position);
        //获取当前播放歌曲的长度,设置进度条的最大值
        seek_bar.setMax((int) mp3Info.getDuration());
        Log.d("MainActivity", "mp3Info->" + mp3Info);

        /*
        这里新建了一个Intent
        里面存放各种即将传给Service的数据
        要启动自定义PlayerService类
        还需要在AndroidManifest中加入如下代码
        <service
            android:name="com.example.dada.myapplication.PlayerService"
            android:exported="false"
            >
        </service>
         */

        Intent intent = new Intent("com.example.communication.MSG_ACTION");
        play_button.setImageResource(R.drawable.pause_photo);
        intent.putExtra("url", mp3Info.getUrl());
        intent.putExtra("title", mp3Info.getTitle());
        intent.putExtra("artist", mp3Info.getArtist());
        intent.putExtra("album", mp3Info.getAlbum());
        intent.putExtra("album_id", mp3Info.getAlbum_id());
        intent.putExtra("MSG", AppConstant.PlayerMsg.PLAY_MSG);
        intent.setClass(MainActivity.this, PlayerService.class);
        startService(intent);
        Log.d("MainActivity", "startService:" + intent);
    }

    private class MsgReceiver extends BroadcastReceiver {
        //定义广播的接收器,有三个步骤,
        @Override
        //1.自定义Receiver类,需要继承Broadcast类
        public void onReceive(Context context, Intent intent) {
            /**
             * 从接收到的广播的intent中取出发送过来的信息
             * 取出了歌曲名和艺术家名
             */
            music_info_textView = findViewById(R.id.music_info_textView);
            singer_info_textView = findViewById(R.id.singer_info_textView);

            music_info_textView.setText(intent.getStringExtra("title"));
            singer_info_textView.setText(intent.getStringExtra("artist"));
        }
    }

    private class BarReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
                /*
     * 接受从service发过来的广播
     * 更新进度条的当前位置
     */
            if (seek_bar.getMax() - current_position <= 1100) {
                changeMusic(play_mode, AppConstant.PlayerMsg.NEXT_MUSIC, mp3Infos);
            } else {
                current_position = intent.getIntExtra("position", 0);
                seek_bar.setProgress(current_position);
            }
        }
    }

    private ImageView getImg0() {
        //if (img0 == null) {
        if (mainFragment.isVisible()){
            img0 = mainFragment.getView().findViewById(R.id.img_1);
        }

        //}
        return img0;
    }

    /**
     * 设置旋转动画
     */
    private void indatae() {

        //不停顿
       animation.setInterpolator(new LinearInterpolator());
        //设定转一圈的时间
        animation.setDuration(5000);
        //设定无限循环
        animation.setRepeatCount(Animation.INFINITE);
        //重复的模式
        animation.setRepeatMode(Animation.RESTART);
        //开启动画
        getImg0().startAnimation(animation);
        //动画停留在结束的位置
        animation.setFillAfter(true);
    }


    /*
    * 自定义函数
    * 用来切歌
    * 传入的参数包含：当前播放模式(mode)、上一首还是下一首(msg)，歌曲的列表(List<Mp3Info> mp3Infos)
    */
    private void changeMusic(int mode, int msg, List<Mp3Info> mp3Infos) {
        isChangToNext = true;
        isPause = false;
        current_position = 0;
        play_button.setImageResource(R.drawable.pause_photo);
        switch (mode) {
            //对当前的播放模式作出判断,进行不同的处理
            case AppConstant.PlayerMsg.LOOP_MODE:
                switch (msg) {
                    //循环模式就按照是上一曲还是下一曲,切换当前播放音乐在音乐列表中的位置
                    case AppConstant.PlayerMsg.NEXT_MUSIC:
                        //随便在列表中取出一个位置的歌曲来播放,注意不要出界就好啦

                        if (music_position < mp3Infos.size() - 1)
                        {
                            music_position++;
                        }
                        else
                        {
                            music_position = 0;
                        }

                        break;

                    case AppConstant.PlayerMsg.PREVIOUS_MUSIC:
                        if (music_position >= 1)
                        {
                            music_position--;
                        }
                        else
                        {
                            music_position = mp3Infos.size() - 1;
                        }
                        break;
                }
                break;

            case AppConstant.PlayerMsg.RANDOM_MODE:
                music_position = (int) (Math.random() * (mp3Infos.size() - 1));
                break;
        }

        try {
            //重新初始化一个service,将切换到的歌曲信息传给service
            initService(music_position);
            Mp3Info mp3_Info = mp3Infos.get(music_position);
            isFavorite = mp3_Info.getFavorite();
            isChangToNext = false;

            music_info_textView.setText(mp3_Info.getTitle());
            singer_info_textView.setText(mp3_Info.getArtist());
            seek_bar.setMax((int) mp3_Info.getDuration());

            intent_to_service.putExtra("isPause", isPause);
            intent_to_changeMusic.putExtra("music_title", mp3_Info.getTitle());
            intent_to_changeMusic.putExtra("music_artist", mp3_Info.getArtist());
            intent_to_changeMusic.putExtra("music_url", mp3_Info.getUrl());
            intent_to_changeMusic.putExtra("isChangeToNext", isChangToNext);

            sendBroadcast(intent_to_service);
            sendBroadcast(intent_to_changeMusic);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
  * 播放模式的popupwindow里按键监听器
  * 在布局文件里面注册的监听器
  */
    public void loop_play_mode_listener(View v){
        Toast.makeText(getApplicationContext(),"更改为循环播放模式",Toast.LENGTH_SHORT).show();
        play_mode = AppConstant.PlayerMsg.LOOP_MODE;
        play_mode_button.setImageResource(R.drawable.play_mode_photo);
        popupPlayModeWindow.dismiss();
    }

    /*
     * 播放模式的popupwindow里按键监听器
     * 在布局文件里面注册的监听器
     */

    public void random_play_mode_listener(View v){
        Toast.makeText(getApplicationContext(),"更改为随机播放模式",Toast.LENGTH_SHORT).show();
        play_mode = AppConstant.PlayerMsg.RANDOM_MODE;
        play_mode_button.setImageResource(R.drawable.random_play_mode);
        popupPlayModeWindow.dismiss();
    }

}
