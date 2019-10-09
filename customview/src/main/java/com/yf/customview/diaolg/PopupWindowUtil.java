package com.yf.customview.diaolg;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.yf.customview.R;

/**
 * Created by colin on 18-4-16.
 */

public class PopupWindowUtil implements View.OnClickListener {
    private static final String TAG = "PopupWindowUtil";

    private static PopupWindowUtil windowUtil=new PopupWindowUtil();

    public static PopupWindowUtil getInstance() {
        return windowUtil;
    }

    View layout;
    void init(Context context,ViewGroup parent) {
        layout = LayoutInflater.from(context).inflate(R.layout.layout_popwindow, parent);
    }

    public void showPop(Context context, View parent) {

        PopupWindow popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
//        WindowManager.LayoutParams lp = mWindow.getAttributes();
//        lp.alpha = alpha;
//        mWindow.setAttributes(lp);
        layout.findViewById(R.id.btn_left).setOnClickListener(this);
        layout.findViewById(R.id.btn_right).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                Log.i(TAG, getClass().toString()+"onClick: left");
                break;
            case R.id.btn_right:
                Log.i(TAG, getClass().toString()+"onClick: right");
                break;
        }
    }
}
