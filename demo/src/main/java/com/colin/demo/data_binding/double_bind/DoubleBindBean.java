package com.colin.demo.data_binding.double_bind;

import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.colin.demo.BR;

/**
 * * 实现方式一，继承BaseObservable
 * 这是双向绑定时JavaBean的实现方式，不用这种方式，当数据源改变时，UI界面不会自动更新
 */
public class DoubleBindBean extends ObservableField {

    private String content; //内容

    public DoubleBindBean(String content) {
        this.content = content;
    }

    //BR 是编译阶段生成的一个类，功能与 R.java 类似，用 @Bindable 标记过 getter 方法会在 BR 中生成一个 entry，当我们
    //通过代码可以看出，当数据发生变化时还是需要手动发出通知。 通过调用notifyPropertyChanged(BR.firstName)来通知系统 BR.firstName 这个 entry 的数据已经发生变化，需要更新 UI。
    @Bindable
    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content); //通知系统数据源发生变化，刷新UI界面
    }
}

