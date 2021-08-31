package com.example.cy.mediakey;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.os.Handler;


/**
 * Created by cy on 21-3-26.
 */

public class TestActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        h();
        //mHandler.post(()->5);
    }
    public void h(){
        AlertDialog alert=new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("显示内容")
                .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     finish();
                     overridePendingTransition(0,0);
                    }
                })
                .create();
        alert.show();
    }
public Boolean mIsShowShutdownSysui(){
    Log.d("","");
        return true;
}
private String a=getString(R.string.app_name);

private void handleAtThisTime(){

}

private  Handler mHandler=new Handler(){
   /* @Override
    public void handleMessage(Message msg) {
        if (msg.what>10){
            Log.d("","");
        }
    }*/

};


}
