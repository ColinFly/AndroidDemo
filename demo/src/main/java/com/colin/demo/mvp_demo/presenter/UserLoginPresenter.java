package com.colin.demo.mvp_demo.presenter;

import android.os.Handler;
import android.os.Looper;

import com.colin.demo.mvp_demo.model.User;
import com.colin.demo.mvp_demo.biz.IUserBiz;
import com.colin.demo.mvp_demo.biz.OnLoginListener;
import com.colin.demo.mvp_demo.biz.UserBiz;
import com.colin.demo.mvp_demo.view.IUserLoginView;

/**
* 面向Model和View的接口编程
 * 组合业务，这个例子中是登录和清除的按钮逻辑
 */
public class UserLoginPresenter  {
    private IUserLoginView mUserLoginView;
    private IUserBiz mUserBiz;
    private Handler mHandler=new Handler(Looper.myLooper());

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.mUserLoginView = userLoginView;
        this.mUserBiz = new UserBiz();//是面向接口的，但是这里有复杂的逻辑，于是有了实现类
    }

    public void login() {
        mUserLoginView.showLoading();
        //看这里都是通过接口来的，所以接口隔离了与View的耦合
        mUserBiz.login(mUserLoginView.getUserName(), mUserLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mUserLoginView.hideLoading();
                        mUserLoginView.toMainActivity();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mUserLoginView.hideLoading();
                        mUserLoginView.showFailedError();
                    }
                });
            }
        });

    }

    public void clear() {
        mUserLoginView.clearUserName();
        mUserLoginView.clearPassword();

    }
}
