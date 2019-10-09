package com.colin.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colin.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by colin on 16-11-2.
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.NormalHolder> {
    private static final String TAG = "NormalAdapter";
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mTitles=new ArrayList();

    public NormalAdapter(Context context) {
        this.mContext = context;
        String[] title= context.getResources().getStringArray(R.array.titles);
        mTitles = Arrays.asList(title);

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
    public void onBindViewHolder(final NormalHolder holder, int position) {
        holder.mTextView.setText(mTitles.get(position));

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder.mTextView,holder.getLayoutPosition());
                }
            }
        });

        holder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemLongClick(holder.mTextView,holder.getLayoutPosition());
                }
                //表示此事件已消费，不会触发单击事件
                return true;
            }
        });
    }

    /**
     * 返回该Adapter持有的Item数量
     */
    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    public static class NormalHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.text_view)
        TextView mTextView;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
//            mTextView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "onClick: position:"+getLayoutPosition());
//                    Log.d(TAG, "onClick: "+mTextView.getText());
//                }
//            });
        }
    }

    public void addNewItem() {
        if (mTitles == null) {
            mTitles=new ArrayList<>();
        }
        mTitles.add("new Item");
        notifyDataSetChanged();
    }

    public void removeItem() {
        if (mTitles == null||mTitles.isEmpty()) {
            return;
        }
        mTitles.remove(0);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
