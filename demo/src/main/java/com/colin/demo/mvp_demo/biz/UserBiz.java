package com.colin.demo.mvp_demo.biz;

import com.colin.demo.mvp_demo.model.User;

/**
 * 用户的具体业务
 */
public class UserBiz implements IUserBiz {
    @Override
    public void login(final String userName, final String password, final OnLoginListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if ("abc".equals(userName) && "123".equals(password)) {
                    User user = new User(userName, password);
                    listener.loginSuccess(user);
                } else {
                    listener.loginFailed();
                }
            }
        }).start();

    }
}
