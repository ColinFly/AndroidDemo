package com.yf.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yf.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colin on 18-1-25.
 */

public class RectBarView extends View {
    private static final String TAG = "RectBarView";

    // px =  (dpi / 160)* dp
    private int mDesiredWidth = 38;
    private int mDesiredHeight = 32;
    private int mBarCount = 4;
    private int mOffsetX = 10;
    private int mBarWidth = 2;
    private Paint mPaint;
    private int mViewWidth;
    private int mViewHeight;

    class Line {
        private float delta;
        private boolean isDown;

        Line(float delta, boolean isDown) {
            this.delta = delta;
            this.isDown = isDown;
        }
    }

    private List<Line> mLineList;


    public RectBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setStyle(Paint.Style.FILL);

        mLineList = new ArrayList<>();
        mLineList.add(new Line(0.3f, false));
        mLineList.add(new Line(0.1f, false));
        mLineList.add(new Line(0.5f, false));
        mLineList.add(new Line(0.7f, false));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : mDesiredWidth,
                heightMode == MeasureSpec.EXACTLY ? heightSize : mDesiredHeight);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mBarWidth = (mViewWidth - (mBarCount - 1) * mOffsetX) / mBarCount;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mBarCount; i++) {
            canvas.drawRect(i * (mBarWidth + mOffsetX), mLineList.get(i).delta * mViewHeight, mBarWidth * (i + 1) + i * mOffsetX, mViewHeight, mPaint);
        }

        for (int i = 0; i < mLineList.size(); i++) {
            Line line = mLineList.get(i);
            if (line.isDown) {
                line.delta += 0.1f;
                if (line.delta >= 1f) {
                    line.isDown = false;
                }
            } else {
                line.delta -= 0.1f;
                if (line.delta <= 0f) {
                    line.isDown = true;
                }
            }
        }
        postInvalidateDelayed(50);
    }


}
