package com.example.cy.test0;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "GridOptionsHelper";
    private Button mbt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.class.getPackage().getName();
        LinearLayout ll = findViewById(R.id.box);
        TextView tv  = new TextView(this);

        tv.setText("2222");
        Log.d("a","v"+tv );
        ll.addView(tv);
        Log.d(TAG,"asd"+ll+"asd"+tv);
        Log.d("asd"+ll,"asd"+tv+"ccc"+ll);
       /* LinearLayout ll = findViewById(R.id.box);
        TextView textView = findViewById(R.id.first);
        ll.removeView(textView);
        for (int i = 0; i < 10; i++) {
            TextView tv  = new TextView(this);
            tv.setText(""+i);
            ll.addView(tv);
        }*/
       RuntimeException e = new RuntimeException();
       e.printStackTrace();
        mbt1=setContentView(R.id.bt);
    }
}
