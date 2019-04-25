package ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.Simba.Utils.UtilsLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 不规则控件进行流式
 */

public class WaterFlowLayout extends ViewGroup {
    public WaterFlowLayout(Context context) {
        super(context);
    }

    public WaterFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left,top,right,bottom;
        int startX=getPaddingLeft();
        int startY=getPaddingTop();
        int measuredWidth=getMeasuredWidth();
        int measuredHeight=getMeasuredHeight();
        //每个子控件占据的宽度
        int childViewUserWidth=0;
        int childViewUserLineHeight=0;
        int childCount=getChildCount();
        for (int i=0;i<childCount;i++) {
            View view=getChildAt(i);
            if (view.getVisibility()==GONE) {
                continue;
            }
            //获取每个子控件的margin
            MarginLayoutParams params= (MarginLayoutParams) view.getLayoutParams();
            int childViewMeasuredWidth=view.getMeasuredWidth();
            int childViewMeasuredHeight=view.getMeasuredHeight();

            childViewUserWidth=childViewMeasuredWidth+params.leftMargin+params.rightMargin;
            if (startX+childViewUserWidth>measuredWidth-getPaddingRight()) {
                startX=getPaddingLeft();
                startY+=childViewUserLineHeight;
            }
            left=startX+params.leftMargin;
            top=startY+params.topMargin;
            right=left+childViewMeasuredWidth;
            bottom=top+childViewMeasuredHeight;
            view.layout(left,top,right,bottom);
            startX+=childViewUserWidth;
            childViewUserLineHeight=Math.max(childViewUserLineHeight,childViewMeasuredHeight+params.bottomMargin);
        }

    }


//只有重写此方法，onMeasure中的layoutParams 才会有margin值

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }


    //onMeasure 走了两遍
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//先进行自己的宽高测量，知道自己的显示模式
        int wideMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        //父容器宽高
        int wideSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        UtilsLog.log("zhm","wideSize=="+wideSize+"==heigtSize=="+heightSize);
        //当前控件宽高
        int measureWidth=0;
        int measureHeight=0;
        if (wideMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            measureWidth=wideSize;
            measureHeight=heightSize;
        } else {
            //当前控件宽高
            int ichildWidth=0;
            int ichildHeight=0;
            //当前行宽高
            int iCurWidth=0;
            int iCurHeight=0;
            //数量
            int childCount=getChildCount();
            UtilsLog.log("zhm","childCount=="+childCount);
            for (int i=0;i<childCount;i++) {
                UtilsLog.log("zhm","i=="+i);
            //当前行宽、当前行高
                View child=getChildAt(i);
                //测量自己
                measureChild(child,widthMeasureSpec,heightMeasureSpec);

                //当前margin
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                //获取实际行宽高
                ichildWidth=child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                ichildHeight=child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
                UtilsLog.log("zhm","ichildWidth==="+ichildWidth+"==ichildHeight==="+ichildHeight);
                //是否需要换行
                if (ichildWidth + iCurWidth > wideSize) {
                    //保存当前行信息(当前行宽与当前子控件的宽度，谁大用谁）
                    measureWidth = Math.max(measureWidth, ichildWidth);
                    measureHeight += ichildHeight;
                    UtilsLog.log("zhm","measureWidth==="+measureWidth+"===measureHeight==="+measureHeight);
                    //更新新的行信息
                    iCurWidth = measureWidth;
                    iCurHeight = ichildHeight;
                    UtilsLog.log("zhm","iCurWidth=="+iCurWidth+"==iCurHeight=="+iCurHeight);
                    //更新后需要将行view复位
                    //记录新行信息
                } else {
                    iCurWidth+=ichildWidth;
                    iCurHeight=Math.max(iCurHeight,ichildHeight);
                    measureWidth=iCurWidth;
                    measureHeight=iCurHeight;
                    UtilsLog.log("zhm","else中iCurWidth=="+iCurWidth+"==iCurHeight=="+iCurHeight);
                }
                if (i==childCount-1) {
                    measureWidth=Math.max(measureWidth,iCurWidth);
                    measureHeight+=iCurHeight;
                }
            }
        }
        UtilsLog.log("zhm","measureWidth=="+measureWidth+"==measurHeight=="+measureHeight);
        setMeasuredDimension(measureWidth,measureHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
