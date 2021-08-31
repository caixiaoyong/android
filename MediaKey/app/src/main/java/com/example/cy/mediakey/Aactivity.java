package com.example.cy.mediakey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by cy on 21-4-7.
 */

public class Aactivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //存数据
        Intent it1=new Intent(Aactivity.this,Bactivity.class);
        Bundle bd=new Bundle();
        bd.putInt("a",1);
        bd.putString("b","bbb");
        it1.putExtras(bd);
        startActivity(it1);
    }


}
