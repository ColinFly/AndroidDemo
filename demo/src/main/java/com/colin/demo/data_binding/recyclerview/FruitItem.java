package com.colin.demo.data_binding.recyclerview;

import android.databinding.BaseObservable;

public class FruitItem extends BaseObservable implements IBindingAdapterItem{
    private int picId;
    private String describe;

    public FruitItem(int picId, String describe) {
        this.picId = picId;
        this.describe = describe;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public int getItemViewType() {
        return 0;
    }
}
