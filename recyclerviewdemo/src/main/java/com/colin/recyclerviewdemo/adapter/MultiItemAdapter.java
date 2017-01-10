package com.colin.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.colin.recyclerviewdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by colin on 16-11-2.
 */

public class MultiItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MultiItemAdapter";
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mTitles;


    public enum ITEM_TYPE {
        IMAGE, TEXT
    }
    public MultiItemAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mTitles = mContext.getResources().getStringArray(R.array.titles);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.TEXT.ordinal()) {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        } else {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).mTextView.setText(mTitles[position]);
        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).mTextView.setText(mTitles[position]);
            ((ImageViewHolder) holder).mImageView.setImageResource(R.mipmap.ic_launcher);
        }

    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.IMAGE.ordinal() : ITEM_TYPE.TEXT.ordinal();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.text_view)
        TextView mTextView;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: " + getLayoutPosition());
                }
            });
        }


    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.text_view)
        TextView mTextView;
        @InjectView(R.id.image_view)
        ImageView mImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: "+getLayoutPosition());
                }
            });
        }
    }
}
