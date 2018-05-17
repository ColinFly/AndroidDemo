package com.yf.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by colin on 18-4-18.
 */

public class RoadLineLayout extends LinearLayout {
    private Context mContext;

    public RoadLineLayout(Context context) {
        super(context);
        init(context);
    }

    public RoadLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RoadLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
    }

    public void show() {
        removeAllViews();
        for (int i = 0; i < 3; i++) {
            ImageView imageView=new ImageView(mContext);
            imageView.setImageResource(R.mipmap.ic_launcher);
            addView(imageView);
        }
    }
}
