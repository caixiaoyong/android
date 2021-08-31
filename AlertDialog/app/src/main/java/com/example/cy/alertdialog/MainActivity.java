package com.example.cy.alertdialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mBtndialog1, mBtndialog2, mBtndialog3, mBtndialog4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtndialog1 = findViewById(R.id.btn_dialog1);
        mBtndialog2 = findViewById(R.id.btn_dialog2);
        mBtndialog3 = findViewById(R.id.btn_dialog3);
        mBtndialog4 = findViewById(R.id.btn_dialog4);
        OnClick onClick = new OnClick();
        mBtndialog1.setOnClickListener(onClick);
        mBtndialog2.setOnClickListener(onClick);
        mBtndialog3.setOnClickListener(onClick);
        mBtndialog4.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_dialog1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("请回答").setMessage("你觉得笨猪还是猫咪？")
                            .setIcon(R.drawable.rubby)
                            .setPositiveButton("她是仙女！", new DialogInterface.OnClickListener() {
                                @Override 
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(MainActivity.this, "你很诚实", Toast.LENGTH_SHORT).show();
                                        }
                                    }).setNeutralButton("是哪个都一样", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "你再瞅瞅～", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("是笨猪没错了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "说啥大实话", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    break;
                case R.id.btn_dialog2:
                    final String[] array2=new String[]{"蔡勇是个大帅比！！","蔡勇超级无敌帅！！！"};
                    AlertDialog.Builder builder2=new AlertDialog.Builder(MainActivity.this);
                    builder2.setTitle("请你良心选择").setIcon(R.drawable.fox)
                            .setSingleChoiceItems(array2, 1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "你的选项真是赞呢！", Toast.LENGTH_SHORT).show();
                            //dialogInterface.dismiss();.setCancelable(false)
                        }
                    }).show();
                    break;
                case R.id.btn_dialog3:
                    final String[] array3=new String[]{"山药脊骨汤","酱烧茄子烧鸭","西兰花炒香肠","青橄榄炖鸽子","梅干菜烧鸡腿","辣炒嫩笋干","无骨泡椒秘制凤爪"};
                    final boolean[] isSelected=new boolean[]{false,false,false,false,false,false,true};
                    AlertDialog.Builder builder3=new AlertDialog.Builder(MainActivity.this);
                    builder3.setTitle("下列哪些是最喜欢吃的").setIcon(R.drawable.pizaa)
                            .setMultiChoiceItems(array3, isSelected, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            //Toast.makeText(MainActivity.this, "想吃？吃不到！哈哈哈～", Toast.LENGTH_SHORT).show();
                            isSelected[i]=b;
                        }
                    }).setPositiveButton("秒哉", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String result="";
                            for(int j=0;j<isSelected.length;j++){
                                if(isSelected[j])
                                    result+=array3[j]+",";
                            }
                            Toast.makeText(MainActivity.this, "客官你点了："+result, Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("噫吁嚱", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "客官慢走", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    break;
                case R.id.btn_dialog4:
                    //用setView()将我们的布局加载到 AlertDialog上
                    AlertDialog.Builder builder4=new AlertDialog.Builder(MainActivity.this);
                    builder4.setIcon(R.drawable.connection);
                    View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_dialog,null);
                    EditText etUserName=view.findViewById(R.id.et_1);
                    EditText etPassWord=view.findViewById(R.id.et_2);
                    Button btLogin=view.findViewById(R.id.bt_login);
                    btLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Uri uri=Uri.parse("https://home.meishichina.com/recipe.html");
                            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                            startActivity(intent);
                        }
                    });
                    builder4.setTitle("请先登录").setView(view).show();
                    break;
            }
        }
    }
}
