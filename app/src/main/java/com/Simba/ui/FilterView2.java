package com.Simba.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.Simba.Utils.UtilsLog;
import com.example.think.myapp.R;

import java.lang.reflect.Type;


public class FilterView2 extends View {
    Paint mPaint;
    RectF rectF;
    RectF rectF1;
    Bitmap bitmap;
    public FilterView2(Context context) {
        super(context);
        UtilsLog.log("zhm","init");
        initData();
    }

    private void initData() {
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.xyjy2);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        rectF=new RectF(10,10,bitmap.getWidth(),bitmap.getHeight());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        UtilsLog.log("zhm","onDraw");
        setLayerType(View.LAYER_TYPE_SOFTWARE,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawBitmap(bitmap,10,10,mPaint);
        mPaint.reset();
        mPaint.setColor(Color.RED);
//      @param radius 阴影的半径
//   @param style  NORMOL -- 整个图像都被模糊掉
//    SOLID -- 图像边界外产生一层与Paint颜色一致阴影效果，不影响图像的本身
//    OUTER -- 图像边界外产生一层阴影，并且将图像变成透明效果
//    INNER -- 在图像内部边沿产生模糊效果
  //      mPaint.setMaskFilter(new BlurMaskFilter(100,BlurMaskFilter.Blur.NORMAL));

        /**
         * Create an emboss maskfilter
         *
         * @param direction  指定光源的位置，长度为xxx的数组标量[x,y,z]
         * @param ambient    环境光的因子 （0~1），越接近0，环境光越暗
         * @param specular   镜面反射系数 越接近0，镜面反射越强
         * @param blurRadius 模糊半径 值越大，模糊效果越明显
         */
        mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{10,10,10},0.2f,60,800));
        canvas.drawBitmap(bitmap,10,bitmap.getHeight(),mPaint);
    }

    public FilterView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FilterView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
