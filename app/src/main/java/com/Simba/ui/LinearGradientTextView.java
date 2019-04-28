package com.Simba.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.Simba.Utils.UtilsLog;

/**线性渲染
 * 类似跑马灯、霓虹灯效果，个别字循环高亮展示
 */

public class LinearGradientTextView extends AppCompatTextView {
    //线性渲染
    LinearGradient mLinearGradient;
    Paint mPaint;
    int curRow;
    int row;
    float mTranslate;
    float DELTAX=20;
    Matrix matrix;

    public LinearGradientTextView(Context context) {
        super(context);
    }

    public LinearGradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearGradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    float width;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        curRow=1;
        mPaint=getPaint();
        String s=getText().toString();
        width=mPaint.measureText(s);
        Paint.FontMetrics fontMetrics=mPaint.getFontMetrics();
        float height=fontMetrics.top-fontMetrics.bottom;
        float size=getTextSize();
        row= (int) (height/size);
        int gradientW= (int) (width/s.length()*3);
        UtilsLog.log("zhm","width=="+width+"==size=="+size+"==gradientW=="+gradientW+"+s.length="+s.length());
        mLinearGradient=new LinearGradient(-gradientW,size * curRow,0,size * curRow,new int[]{
                0x22ffffff, 0xffffffff, 0x22ffffff},null,Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
        matrix = new Matrix();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTranslate += DELTAX;
        //到底部进行返回
        if(mTranslate > width + 1 || mTranslate < 1){
            DELTAX = -DELTAX;
            curRow++;
            if(curRow > row){
                curRow = 0;
            }
        }
        matrix = new Matrix();
        matrix.setTranslate(mTranslate, 0);
        mLinearGradient.setLocalMatrix(matrix);
        postInvalidateDelayed(50);
        UtilsLog.log("zhm","mTranslate=="+mTranslate);


    }
}
