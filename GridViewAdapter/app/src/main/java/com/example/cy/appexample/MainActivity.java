package com.example.cy.appexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    private GridView mGv;
    private Button mBtnGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGv=findViewById(R.id.gv);
        mBtnGridView=findViewById(R.id.btn_grid);
        mBtnGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,GridViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
