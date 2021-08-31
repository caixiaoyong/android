package com.example.cy.mediakey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by cy on 21-4-7.
 */

public class Bactivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取数据
        Intent it2=getIntent();
        Bundle bd=it2.getExtras();
        int n=bd.getInt("a");
        String m=bd.getString("bbb");
        System.out.println(n+ m);
        Log.d("aaa","aaa"+m);

    }
}
