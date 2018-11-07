package com.colin.demo.data_binding.double_bind;

import android.databinding.ObservableField;

/**
 * 实现方式二，通过ObservableFields来实现，如果开发者想节省时间或者属性比较少，推荐使用这种方式
 * 这是双向绑定时JavaBean的实现方式，不用这种方式，当数据源改变时，UI界面不会自动更新
 */

public class DoubleBindBean2 {

    public final ObservableField<String> username=new ObservableField<>();

    public DoubleBindBean2(String username) {
        this.username.set(username);

    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
}

