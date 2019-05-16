package com.Simba.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.Simba.Utils.UtilsLog;
import com.example.think.myapp.R;

/**
 * 圆中的波浪（动态的波浪）
 */

public class IregularWaveView extends View {
    Paint mPaint;
    int waveLength=0;
    int dx=0;
    Bitmap bitmapCircle;//圆的
    Bitmap bitmapWave;//波浪的
    public IregularWaveView(Context context) {
        super(context);
        UtilsLog.log("zhm","构造函数");
        initData();
        startAnima();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        UtilsLog.log("zhm","onDraw");
        //先画上圆形
        canvas.drawBitmap(bitmapCircle,0,0,mPaint);
        //再画上结果
        int layerId=canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bitmapWave,new Rect(dx,0,dx+bitmapCircle.getWidth(),bitmapCircle.getHeight()),new Rect(0,0,bitmapCircle.getWidth(),bitmapCircle.getHeight()),mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmapCircle,0,0,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }

    private void startAnima() {
        ValueAnimator valueAnimator=ValueAnimator.ofInt(waveLength);
        valueAnimator.setDuration(4000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx= (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    private void initData() {
        mPaint=new Paint();
        bitmapCircle= BitmapFactory.decodeResource(getResources(), R.drawable.circle_shape);
        bitmapWave=BitmapFactory.decodeResource(getResources(),R.drawable.wav);
        waveLength=bitmapWave.getWidth();
    }

    public IregularWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IregularWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public IregularWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
