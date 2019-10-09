package com.yf.customview;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

/**
 * 倒计时View管理器
 * 1.应该和View绑定
 * 2.倒计时要有onTick和onComplete的接口来使UI更新
 * 3.UI的点击要能使当前任务cancel掉
 */
public class CountDownManager {
    private static final CountDownManager ourInstance = new CountDownManager();

    public static CountDownManager getInstance() {
        return ourInstance;
    }

    private CountDownManager() {
    }

    private CountDownTimer mBtnCountDownTimer;
    public void showBtn(final Button btn) {
        mBtnCountDownTimer=new CountDownTimer(10*1000+500,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (btn.getVisibility() == View.GONE) {
                    btn.setVisibility(View.VISIBLE);
                }
                btn.setText(String.format("取消(%ds)", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                btn.setVisibility(View.GONE);

            }
        }.start();
    }

    public void hideBtn(Button btn) {
        if (mBtnCountDownTimer != null) {
            mBtnCountDownTimer.cancel();
            mBtnCountDownTimer = null;
        }
        btn.setVisibility(View.GONE);
    }
}
