package com.example.cy.layout_six;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by cy on 21-1-9.
 */

public class PlaneView extends View {
    public float bitmapX;
    public float bitmapY;
    public PlaneView(Context context) {
        //2.新建一个继承View类的PlaneView自定义组件类,在构造方法中初始化view的初始坐标
        super(context);
        //设置起始坐标
        bitmapX=0;
        bitmapY=200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //3.重写onDraw()方法,实例化一个空的画笔类Paint
        super.onDraw(canvas);
        //创建,并且实例化Paint的对象
        Paint paint = new Paint();
        //4:调用BitmapFactory.decodeResource()生成位图对象
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.drawable.pop);
        //5:调用canvas.drawBitmap()绘制气球的位图对象
        canvas.drawBitmap(bitmap,bitmapX,bitmapY,paint);
        if(bitmap.isRecycled())
        {//6.判断图片是否回收,木有回收的话强制收回图片
            bitmap.recycle();
        }
    }
}
