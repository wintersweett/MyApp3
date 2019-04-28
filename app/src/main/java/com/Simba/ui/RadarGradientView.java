package com.Simba.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.Simba.Utils.UtilsLog;

/**梯度渲染
 * 类似雷达样效果
 */

public class RadarGradientView extends View {
    private  Runnable run=new Runnable() {
        @Override
        public void run() {
            UtilsLog.log("zhm","run()");
            scanAngle=(scanAngle+scanSpeed)%360;
            matrix.postRotate(scanSpeed,width/2,height/2);//旋转矩阵
            invalidate();//通知view重绘
            postDelayed(run,50);//调用自身，重复绘制
        }
    };
    int scanAngle;//扫描旋转的角度
    int scanSpeed=5;//扫描的速度
    Matrix matrix=new Matrix();//旋转需要的矩阵

    Paint mPaint;//画圆用的画笔
    Paint scanPaint;//扫描用的画笔
    float[] pots={0.05f,0.1f,0.15f,0.2f,0.25f};
    SweepGradient sweepGradient;
    public RadarGradientView(Context context) {
        super(context);
        UtilsLog.log("zhm","一个参数");
        setWillNotDraw(false);
        mPaint=new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAlpha(100);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#B0C4DE"));

        scanPaint=new Paint();
        scanPaint.setAntiAlias(true);
        scanPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        post(run);

    }

    public RadarGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        UtilsLog.log("zhm","2个参数");
    }

    public RadarGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        UtilsLog.log("zhm","3个参数");
    }

    int height=0;
    int width=0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height=MeasureSpec.getSize(heightMeasureSpec);
        width=MeasureSpec.getSize(widthMeasureSpec);
        height=width=Math.min(height,width);
        UtilsLog.log("zhm","onMeasure=="+"height=="+height+"==width=="+width);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        UtilsLog.log("zhm","onDraw");
        for (int i=0;i<pots.length;i++) {
            canvas.drawCircle(width/2,height/2,width*pots[i],mPaint);
        }

        canvas.save();
        sweepGradient=new SweepGradient(width/2,height/2,new int[]{Color.TRANSPARENT,Color.parseColor("#84B5CA")},null);
        scanPaint.setShader(sweepGradient);
        canvas.concat(matrix);
        canvas.drawCircle(width/2,height/2,width*pots[4],scanPaint);
        canvas.restore();

    }
}
