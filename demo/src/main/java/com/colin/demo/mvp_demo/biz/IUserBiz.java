package com.colin.demo.mvp_demo.biz;


/**
 * 用户的业务逻辑（登录，注册）
 */
public interface IUserBiz {
    void login(String userName,String password,OnLoginListener listener);
}
