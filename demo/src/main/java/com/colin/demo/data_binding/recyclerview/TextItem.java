package com.colin.demo.data_binding.recyclerview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.colin.demo.R;

public class TextItem extends BaseObservable implements IBindingAdapterItem{
    private String text;

    public TextItem(String text) {
        this.text = text;
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
//        notifyPropertyChanged(BR.text);
    }

    @Override
    public int getItemViewType() {
        return R.layout.data_bind_item_text;
    }
}
