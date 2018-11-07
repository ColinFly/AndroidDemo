package com.colin.demo.data_binding.recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.colin.demo.R;
import com.colin.demo.data_binding.base.BaseBindRecyclerViewAdapter;
import com.colin.demo.databinding.DataBindItemFruitBinding;
import com.colin.demo.databinding.DataBindItemTextBinding;


import java.util.List;

public class MultiItemAdapter extends BaseBindRecyclerViewAdapter<IBindingAdapterItem> {

    public MultiItemAdapter(Context context, List<IBindingAdapterItem> mList) {
        super(context, mList);
    }


    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemViewType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.data_bind_item_fruit:
                DataBindItemFruitBinding itemFruitBinding = DataBindingUtil.inflate(inflater, R.layout.data_bind_item_fruit, parent, false);
                return new FruitViewHolder(itemFruitBinding);
            case R.layout.data_bind_item_text:
                DataBindItemTextBinding itemTextBinding = DataBindingUtil.inflate(inflater, R.layout.data_bind_item_text, parent, false);
                return new TextViewHolder(itemTextBinding);
            default:
                DataBindItemFruitBinding binding = DataBindingUtil.inflate(inflater, R.layout.data_bind_item_fruit, parent, false);
                return new FruitViewHolder(binding);

        }
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FruitViewHolder) {
            FruitItem fruitItem = (FruitItem) mList.get(position);
            ((FruitViewHolder) holder).getBindItemFruitBinding().setItem(fruitItem);
            ((FruitViewHolder) holder).getBindItemFruitBinding().executePendingBindings();//解决databinding闪烁问题
        } else if (holder instanceof TextViewHolder) {
            TextItem textItem = (TextItem) mList.get(position);
            ((TextViewHolder) holder).getBindItemTextBinding().setItem(textItem);
            ((TextViewHolder) holder).getBindItemTextBinding().executePendingBindings();
        }
    }


    class FruitViewHolder extends RecyclerView.ViewHolder {
        private DataBindItemFruitBinding bindItemFruitBinding;

        DataBindItemFruitBinding getBindItemFruitBinding() {
            return bindItemFruitBinding;
        }

        FruitViewHolder(DataBindItemFruitBinding binding) {
            super(binding.getRoot());
            this.bindItemFruitBinding = binding;
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        private DataBindItemTextBinding bindItemTextBinding;

        TextViewHolder(DataBindItemTextBinding bindItemTextBinding) {
            super(bindItemTextBinding.getRoot());
            this.bindItemTextBinding = bindItemTextBinding;
        }

        DataBindItemTextBinding getBindItemTextBinding() {
            return bindItemTextBinding;
        }
    }


}
