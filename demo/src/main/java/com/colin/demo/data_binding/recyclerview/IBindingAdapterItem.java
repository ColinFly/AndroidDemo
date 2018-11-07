package com.colin.demo.data_binding.recyclerview;

/**
 * RecyclerView使用databinding时，如果多布局的情况下，需要设置item的type
 */
public interface IBindingAdapterItem {
    int getItemViewType();
}
