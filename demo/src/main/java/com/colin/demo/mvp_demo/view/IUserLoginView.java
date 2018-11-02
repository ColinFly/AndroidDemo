package com.colin.demo.mvp_demo.view;

/**
 * 登录界面View需要实现的接口(提供的功能)
 */
public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity();

    void showFailedError();

    void clearUserName();

    void clearPassword();

}
