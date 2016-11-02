package com.colin.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colin.recyclerviewdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by colin on 16-11-2.
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.NormalHolder> {
    private static final String TAG = "NormalAdapter";
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] mTitles;

    public NormalAdapter(Context context) {
        this.mContext = context;
        mTitles = context.getResources().getStringArray(R.array.titles);
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 创建Item视图，并返回相应的ViewHolder
     */
    @Override
    public NormalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    /**
     * 绑定数据到正确的Item视图上
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(NormalHolder holder, int position) {
        holder.mTextView.setText(mTitles[position]);
    }

    /**
     * 返回该Adapter持有的Item数量
     */
    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class NormalHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.text_view)
        TextView mTextView;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: position:"+getLayoutPosition());
                }
            });
        }
    }
}
