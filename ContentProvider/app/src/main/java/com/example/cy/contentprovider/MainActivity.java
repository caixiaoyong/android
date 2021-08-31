package com.example.cy.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTv1;
    private String columns= ContactsContract.Contacts.DISPLAY_NAME;//希望获得（通讯录中）姓名
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv1=findViewById(R.id.tv_1);
        mTv1.setText(getQueryData());//显示获取的通讯录信息

    }
    private CharSequence getQueryData(){
        StringBuilder Str=new StringBuilder();//用于保存获取的联系人
        ContentResolver resolver=getContentResolver();//来读取其他应用的信息
        Cursor cursor=resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);//查询记录
        int displayNameIndex=cursor.getColumnIndex(columns);//获取姓名记录的索引值
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToLast()){//每行的集合,获得的满足条件（就是我们query方法中传入的条件参数）的所有行
            String displayName=cursor.getString(displayNameIndex);
            Str.append(displayName+"\n");
        }
        cursor.close();//关闭记录集
        return Str.toString();//返回查询结果
    }
}
