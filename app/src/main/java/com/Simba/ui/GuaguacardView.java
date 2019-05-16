package com.Simba.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.Simba.Utils.UtilsLog;

public class GuaguacardView extends View {
    public GuaguacardView(Context context) {
        super(context);
    }

    public GuaguacardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GuaguacardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GuaguacardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        UtilsLog.log("zhm","onDraw");
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        UtilsLog.log("zhm","onTouchEvent");

        return super.onTouchEvent(event);
    }
}
