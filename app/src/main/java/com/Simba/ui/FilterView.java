package com.Simba.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.think.myapp.R;

/**
 * 滤镜
 *         mPaint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix("8")));
 */

public class FilterView extends View {
    Paint mPaint;
    Bitmap bitmap;
    public FilterView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.xyjy2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        //一个图片位置
        RectF rectF=new RectF(10,10,bitmap.getWidth()+10,bitmap.getHeight());
        mPaint.reset();
        mPaint.setColor(Color.RED);
        canvas.drawBitmap(bitmap,null,rectF,mPaint);
        //另一个图片位置
        RectF rectF1=new RectF(10,bitmap.getHeight()+10,bitmap.getWidth()+10,bitmap.getHeight()*2+10);
        mPaint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix("6")));
        canvas.drawBitmap(bitmap,null,rectF1,mPaint);

    }

    /**
     * 不同的ColorMatrix实现不同的效果
     * @param s
     * @return
     */

    public ColorMatrix getColorMatrix(String s) {
        //RGBA这个行顺序（Red，Green，Blue，Alpha），第五列是颜色的偏移量
        ColorMatrix colorMatrix = null;
        if ("1".equals(s)) {
            colorMatrix=new ColorMatrix(new float[]{
                    1,0,0,0,0,
                    0,1,0,0,0,
                    0,0,1,0,0,
                    0,0,0,1,0,
            });
        } else if ("2".equals(s)) {//        // 平移运算---加法
            colorMatrix=new ColorMatrix(new float[]{
                    1,0,0,0,0,
                    0,1,0,0,100,
                    0,0,1,0,0,
                    0,0,0,1,0,
            });
        } else if ("3".equals(s)) {//        // 反相效果 -- 底片效果
            colorMatrix=new ColorMatrix(new float[]{
                    -1,0,0,0,255,
                    0,-1,0,0,255,
                    0,0,-1,0,255,
                    0,0,0,1,0,
            });
        } else if ("4".equals(s)) {
          // 缩放运算---乘法 -- 颜色增强
            colorMatrix = new ColorMatrix(new float[]{
                  1.2f, 0,0,0,0,
                0,1.2f,0,0,0,
                0,0,1.2f,0,0,
                0,0,0,1.2f,0,
        });

        } else if ("5".equals(s)) {
            // 黑白照片
            //是将我们的三通道变为单通道的灰度模式
            // 去色原理：只要把R G B 三通道的色彩信息设置成一样，那么图像就会变成灰色，
            // 同时为了保证图像亮度不变，同一个通道里的R+G+B =1
            //
        colorMatrix = new ColorMatrix(new float[]{
                0.213f, 0.715f,0.072f,0,0,
                0.213f, 0.715f,0.072f,0,0,
                0.213f, 0.715f,0.072f,0,0,
                0,0,0,1,0,
        });
        } else if ("6".equals(s)) {
            // 发色效果---（比如红色和绿色交换）
            colorMatrix = new ColorMatrix(new float[]{
                    1,0,0,0,0,
                    0, 0,1,0,0,
                    0,1,0,0,0,
                    0,0,0,0.5F,0,
            });
        } else if ("7".equals(s)) {
            // 复古效果
        colorMatrix = new ColorMatrix(new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f, 1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1,0,
        });
        } else if ("8".equals(s)) {
            // 颜色通道过滤
            //两个矩阵
            //本身颜色矩阵 A
            //过滤矩阵  c
            //a*c=out color
        colorMatrix = new ColorMatrix(new float[]{
                1.3F,0,0,0,0,
                0,1.3F,0,0,0,
                0,0,1.3F,0,0,
                0,0,0,1,0,
        });
        }



        return colorMatrix;
    }

    public FilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
