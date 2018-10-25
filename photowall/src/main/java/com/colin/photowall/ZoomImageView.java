package com.colin.photowall;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 图片的大图展示和多点触控缩放的
 * 1.图片小于屏幕则居中显示
 * 2.图片>屏幕则区域显示
 * 3.单点移动，双指缩放
 */
public class ZoomImageView extends View {
    public static final int STATUS_INIT = 1;
    //放大图片
    public static final int STATUS_ZOOM_OUT = 2;
    //缩小图片
    public static final int STATUS_ZOOM_IN = 3;
    //移动图片
    public static final int STATUS_MOVE = 4;
    //当前的操作状态记录
    private int mCurrentStatus = STATUS_INIT;

    //对图片进行移动和缩放的矩阵
    private Matrix mMatrix = new Matrix();
    //待展示的bitmap对象
    private Bitmap mOriginBitmap;
    //ZoomView控件的宽高
    private int mZoomViewWidth, mZoomViewHeight;
    //双指触摸时的中间点横纵坐标
    private float mCenterPointX, mCenterPointY;
    //当前图片的宽高，图片缩放时这个宽高也会变化
    private float mCurBitmapWidth, mCurBitmapHeight;
    //上次手指移动时的横纵坐标
    private float mLastMoveX = -1, mLastMoveY = -1;
    //手指在坐标轴方向上的移动的距离
    private float mMovedDistanceX, mMovedDistanceY;
    //图片在矩阵上的偏移
    private float mTotalTranslateX, mTotalTranslateY;
    //手指移动的距离所造成的图片缩放比例
    private float scaledRatio;
    //图片在矩阵上的总缩放比例
    private float totalRatio;
    //图片在初始时的缩放比例
    private float initRatio;
    //上次两指间的距离
    private double mLastFingerDistance;


    public ZoomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * @param bitmap 待展示的图片设置进来
     */
    public void setBitmap(Bitmap bitmap) {
        mOriginBitmap = bitmap;
        invalidate();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            mZoomViewWidth = getWidth();
            mZoomViewHeight = getHeight();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() == 2) {
                    //当两个手指在屏幕上的时候，测量两指间距
                    mLastFingerDistance = distanceBetweenFingers(event);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 1) {
                    //只有单指的时候，为图片拖动状态
                    handleDrag(event);
                } else if (event.getPointerCount() == 2) {
                    //双指的时候，为图片缩放状态
                    handleZoom(event);
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                if (event.getPointerCount() == 2) {
                    mLastMoveX = mLastMoveY = -1;
                }
                break;
            case MotionEvent.ACTION_UP:
                mLastMoveX = mLastMoveY = -1;
                break;

        }
        return true;
    }


    private double distanceBetweenFingers(MotionEvent event) {
        float disX = Math.abs(event.getX(0) - event.getX(1));
        float disY = Math.abs(event.getY(0) - event.getY(1));
        return Math.sqrt(disX * disX + disY * disY);
    }

    private void handleZoom(MotionEvent event) {
        centerPointBetweenFingers(event);
        double fingerDis = distanceBetweenFingers(event);
        if (fingerDis > mLastFingerDistance) {
            mCurrentStatus = STATUS_ZOOM_OUT;
        } else {
            mCurrentStatus = STATUS_ZOOM_IN;
        }
        // 进行缩放倍数检查，最大只允许将图片放大4倍，最小可以缩小到初始化比例
        if ((mCurrentStatus == STATUS_ZOOM_OUT && totalRatio < 4 * initRatio)
                || (mCurrentStatus == STATUS_ZOOM_IN && totalRatio > initRatio)) {
            scaledRatio = (float) (fingerDis / mLastFingerDistance);
            totalRatio = totalRatio * scaledRatio;
            if (totalRatio > 4 * initRatio) {
                totalRatio = 4 * initRatio;
            } else if (totalRatio < initRatio) {
                totalRatio = initRatio;
            }
            // 调用onDraw()方法绘制图片
            invalidate();
            mLastFingerDistance = fingerDis;
        }


    }

    /**
     * 计算两个手指之间中心点的坐标。
     *
     * @param event
     */
    private void centerPointBetweenFingers(MotionEvent event) {
        float xPoint0 = event.getX(0);
        float yPoint0 = event.getY(0);
        float xPoint1 = event.getX(1);
        float yPoint1 = event.getY(1);
        mCenterPointX = (xPoint0 + xPoint1) / 2;
        mCenterPointY = (yPoint0 + yPoint1) / 2;
    }


    private void handleDrag(MotionEvent event) {
        float xMove = event.getX();
        float yMove = event.getY();
        if (mLastMoveX == -1 && mLastMoveY == -1) {
            mLastMoveX = xMove;
            mLastMoveY = yMove;
        }
        mCurrentStatus = STATUS_MOVE;
        mMovedDistanceX = xMove - mLastMoveX;
        mMovedDistanceY = yMove - mLastMoveY;
        //进行边界检查,不允许将图片拖出边界
        if (mTotalTranslateX + mMovedDistanceX > 0) {
            mMovedDistanceX = 0;
        } else if (mZoomViewWidth - (mTotalTranslateX + mMovedDistanceX) > mCurBitmapWidth) {
            mMovedDistanceX = 0;
        }
        if (mTotalTranslateY + mMovedDistanceY > 0) {
            mMovedDistanceY = 0;
        } else if (mZoomViewHeight - (mTotalTranslateY + mMovedDistanceY) > mCurBitmapHeight) {
            mMovedDistanceY = 0;
        }
        //调用onDraw方法
        invalidate();
        mLastMoveX = xMove;
        mLastMoveY = yMove;

    }

    /**
     * @param canvas 根据当前状态来决定对图片进行怎样的绘制操作
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mCurrentStatus) {
            case STATUS_ZOOM_OUT:
            case STATUS_ZOOM_IN:
                zoom(canvas);
                break;
            case STATUS_MOVE:
                move(canvas);
                break;
            case STATUS_INIT:
                initBitmap(canvas);
                break;
        }
    }

    private void initBitmap(Canvas canvas) {
        if (mOriginBitmap != null) {
            mMatrix.reset();
            int bitmapWidth = mOriginBitmap.getWidth();
            int bitmapHeight = mOriginBitmap.getHeight();
            if (bitmapWidth > mZoomViewWidth || bitmapHeight > mZoomViewHeight) {
                //当图片宽度大于屏幕宽度，则等比例压缩使其可以完全显示
                if (bitmapWidth - mZoomViewWidth > bitmapHeight - mZoomViewHeight) {
                    float ratio = mZoomViewWidth / (bitmapWidth * 1.0f);
                    mMatrix.postScale(ratio, ratio);
                    float translateY = (mZoomViewHeight - (bitmapHeight * ratio)) / 2f;
                    // 在纵坐标方向上进行偏移，以保证图片居中显示
                    mMatrix.postTranslate(0, translateY);
                    mTotalTranslateY = translateY;
                    totalRatio = initRatio = ratio;
                } else {
                    // 当图片高度大于屏幕高度时，将图片等比例压缩，使它可以完全显示出来
                    float ratio = mZoomViewHeight / (bitmapHeight * 1.0f);
                    mMatrix.postScale(ratio, ratio);
                    float translateX = (mZoomViewWidth - (bitmapWidth * ratio)) / 2f;
                    // 在横坐标方向上进行偏移，以保证图片居中显示
                    mMatrix.postTranslate(translateX, 0);
                    mTotalTranslateX = translateX;
                    totalRatio = initRatio = ratio;
                }
                mCurBitmapWidth = bitmapWidth * totalRatio;
                mCurBitmapHeight = bitmapHeight * totalRatio;

            } else {
                // 当图片的宽高都小于屏幕宽高时，直接让图片居中显示
                float translateX = (mZoomViewWidth - mOriginBitmap.getWidth()) / 2f;
                float translateY = (mZoomViewHeight - mOriginBitmap.getHeight()) / 2f;
                mMatrix.postTranslate(translateX, translateY);
                mTotalTranslateX = translateX;
                mTotalTranslateY = translateY;
                totalRatio = initRatio = 1f;
                mCurBitmapWidth = bitmapWidth;
                mCurBitmapHeight = bitmapHeight;

            }
            canvas.drawBitmap(mOriginBitmap, mMatrix, null);
        }
    }

    private void move(Canvas canvas) {
        mMatrix.reset();
        //根据手指移动的距离计算出总偏移值
        float translateX = mTotalTranslateX + mMovedDistanceX;
        float translateY = mTotalTranslateY + mMovedDistanceY;
        //先按照已有的缩放比例对图片进行缩放
        mMatrix.postScale(totalRatio, totalRatio);
        //再根据移动距离进行偏移
        mMatrix.postTranslate(translateX, translateY);
        mTotalTranslateX = translateX;
        mTotalTranslateY = translateY;
        canvas.drawBitmap(mOriginBitmap, mMatrix, null);

    }

    private void zoom(Canvas canvas) {
        mMatrix.reset();
        // 将图片按总缩放比例进行缩放
        mMatrix.postScale(totalRatio, totalRatio);
        float scaledWidth = mOriginBitmap.getWidth() * totalRatio;
        float scaledHeight = mOriginBitmap.getHeight() * totalRatio;
        float translateX = 0f;
        float translateY = 0f;
        // 如果当前图片宽度小于屏幕宽度，则按屏幕中心的横坐标进行水平缩放。否则按两指的中心点的横坐标进行水平缩放
        if (mCurBitmapWidth < mZoomViewWidth) {
            translateX = (mZoomViewWidth - scaledWidth) / 2f;
        } else {
            translateX = mTotalTranslateX * scaledRatio + mCenterPointX * (1 - scaledRatio);
            // 进行边界检查，保证图片缩放后在水平方向上不会偏移出屏幕
            if (translateX > 0) {
                translateX = 0;
            } else if (mZoomViewWidth - translateX > scaledWidth) {
                translateX = mZoomViewWidth - scaledWidth;
            }
        }
        // 如果当前图片高度小于屏幕高度，则按屏幕中心的纵坐标进行垂直缩放。否则按两指的中心点的纵坐标进行垂直缩放
        if (mCurBitmapHeight < mZoomViewHeight) {
            translateY = (mZoomViewHeight - scaledHeight) / 2f;
        } else {
            translateY = mTotalTranslateY * scaledRatio + mCenterPointY * (1 - scaledRatio);
            // 进行边界检查，保证图片缩放后在垂直方向上不会偏移出屏幕
            if (translateY > 0) {
                translateY = 0;
            } else if (mZoomViewHeight - translateY > scaledHeight) {
                translateY = mZoomViewHeight - scaledHeight;
            }
        }
        // 缩放后对图片进行偏移，以保证缩放后中心点位置不变
        mMatrix.postTranslate(translateX, translateY);
        mTotalTranslateX = translateX;
        mTotalTranslateY = translateY;
        mCurBitmapWidth = scaledWidth;
        mCurBitmapHeight = scaledHeight;
        canvas.drawBitmap(mOriginBitmap, mMatrix, null);
    }


}
