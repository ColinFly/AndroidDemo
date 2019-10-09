package com.colin.demo.mvp_demo.biz;

import com.colin.demo.mvp_demo.model.User;

public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
