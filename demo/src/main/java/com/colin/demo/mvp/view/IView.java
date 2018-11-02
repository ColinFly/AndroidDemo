package com.colin.demo.mvp.view;


import com.colin.demo.mvp.presenter.IPresenter;



public interface IView<P extends IPresenter> {

    P initPresenter();
}
