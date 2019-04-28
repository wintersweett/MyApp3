package com.Simba.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.Simba.Utils.UtilsLog;
import com.example.think.myapp.R;

/**
 * 利用组合，将一个空心的心图片，填满颜色
 */

public class MyGradient extends View {
    Paint mpaint;
    public MyGradient(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mpaint=new Paint();


    }

    public MyGradient(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGradient(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyGradient(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.heart);
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();
        UtilsLog.log("zhm","wid="+width+"=hei="+height);
        BitmapShader bitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        LinearGradient linearGradient=new LinearGradient(0,0,width,height,new int[]{Color.GREEN,Color.BLUE},null,Shader.TileMode.CLAMP);
        ComposeShader composeShader=new ComposeShader(bitmapShader,linearGradient, PorterDuff.Mode.MULTIPLY);
        mpaint.setShader(composeShader);
        canvas.drawRect(0,0,width,height,mpaint);
    }
}
