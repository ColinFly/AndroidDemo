package com.yf.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.yf.customview.Position;

import java.util.List;

/**
 * Created by colin on 18-1-29.
 */

public class MyView extends View {
    private Paint mPaint;
    private Paint mPaint2;
    private int mDesiredWidth=1280;
    private int mDesiredHeight=720;

    public MyView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        mPaint.setStrokeWidth(5);

        mPaint2 = new Paint();
        mPaint2.setStrokeCap(Paint.Cap.ROUND);
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        mPaint2.setStrokeWidth(5);

        rectF=new RectF(0f, 0f, 1280f, 720f);
    }

    private RectF rectF;
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private List<Position> mList;
    private List<Position> mRightList;

    public void setLeftList(List<Position> mList) {
        this.mList = mList;
    }

    public void setRightList(List<Position> mList) {
        this.mRightList = mList;
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

    private static final String TAG = "MyView";
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawPoint(0,0,mPaint);
//        canvas.drawPoint(0,600,mPaint);
//        canvas.drawPoint(1280, 0, mPaint);
//        canvas.drawPoint(1280, 400, mPaint);
//        canvas.drawRect(rectF,mPaint );
        if (mList != null) {
            for (int i = 0; i < mList.size(); i++) {
                Position position = mList.get(i);
                canvas.drawPoint((float) (position.x),(float)(position.y),mPaint);
            }
        }

        if (mRightList != null) {
            for (int i = 0; i < mRightList.size(); i++) {
                Position position = mRightList.get(i);
                canvas.drawPoint(position.x, position.y, mPaint2);
            }
        }
    }

    public void refresh() {
        invalidate();
    }
}
