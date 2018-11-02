package com.colin.demo.mvp.contract;


import com.colin.demo.mvp.presenter.BasePresenter;
import com.colin.demo.mvp.presenter.IPresenter;
import com.colin.demo.mvp.view.IView;


public interface Contract {

    interface ITalkView<P extends IPresenter> extends IView<P> {

        void showAndDismissKeyboard(boolean isHide);

        void showAndHideWarning(boolean needShow);

        void setPrivateModeUI(boolean isMicEnable);

        void setCallModeUI(boolean isPhoneMode);

        void updateTalkingTime(String time);

        void setContactName(String contactsName);

        void updateViewStates(int state, String phoneNum, String contactName, String textStatus);
    }

    abstract class BaseTalkPresenter<V extends IView> extends BasePresenter<V> {

        public abstract void onActivityCreate();

        public abstract void hangup();

        public abstract void pick();

        public abstract void call(String num);

        public abstract void sendDTMF(String signal);

        public abstract void setContactName(String name);

        public abstract void updateTalkingTime(String time);

        public abstract void switchCallMode();

        public abstract void switchPrivateMode();

        public abstract void updateViewStates(int state, String phoneNum, String contactName, String textStatus);

        public abstract void backToOsd();
    }

}
