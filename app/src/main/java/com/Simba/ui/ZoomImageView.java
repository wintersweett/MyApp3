package com.Simba.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.Simba.Utils.UtilsLog;
import com.example.think.myapp.R;
/*

水波纹、放大镜效果
 */


public class ZoomImageView extends View {
    //原图
    Bitmap bitmap;
    //放大后的图片
    Bitmap bigbitmap;
    private static final int FACTOR=2;//放大倍数
    private static final int RADIUS=100;//放大镜的半径
    //制作圆形的图片，放大后 盖在canvas上
    ShapeDrawable shapeDrawable;
    Matrix matrix;
    public ZoomImageView(Context context) {
        super(context);
        init(context);

    }

    private void init(Context context) {
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.xyjy3);
        bigbitmap= (Bitmap) Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()*FACTOR,bitmap.getHeight()*FACTOR,true);
        BitmapShader bitmapShader=new BitmapShader(bigbitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        shapeDrawable=new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(bitmapShader);
        matrix=new Matrix();
    }

    public ZoomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ZoomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        UtilsLog.log("zhm","onDraw");
        //画原图
        canvas.drawBitmap(bitmap,0,0,null);
        //画放大镜图
        shapeDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        UtilsLog.i("zhm","onTouchEvent");
        int x= (int) event.getX();
        int y= (int) event.getY();
        //将放大镜图片往相反方向挪动
        matrix.setTranslate(RADIUS-x*FACTOR,RADIUS-y*FACTOR);
        shapeDrawable.getPaint().getShader().setLocalMatrix(matrix);
        //切出手势区域点位置的图片
        shapeDrawable.setBounds(x-RADIUS,y-RADIUS,x+RADIUS,y+RADIUS);
      //  postInvalidate();
        invalidate();
        return true;

    }
}
