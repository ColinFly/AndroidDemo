package com.colin.demo.data_binding.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.colin.demo.data_binding.recyclerview.IBindingAdapterItem;

/**
 * 使用DataBinding下的RecyclerView的ViewHolder
 */

public class BaseBindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;
    
    public ViewDataBinding getBinding(){
        return binding;
    }

    /**
     * @param binding 可以看作是这个holder代表的布局的马甲，getRoot()方法会返回整个holder的最顶层的view
     */
    public BaseBindingViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * 绑定数据
     *
     * @param item
     */
    public void bindData(IBindingAdapterItem item) {
//        binding.setVariable(BR.item, item);
    }
}
