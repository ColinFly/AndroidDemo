package com.colin.demo.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colin.demo.R;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "ViewAdapter";

    private List<RoadInfo> mDataList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private int mSelectIndex = -1;

    public ViewAdapter(List<RoadInfo> dataList, Context context) {
        this.mDataList = dataList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case R.layout.item_recycler_view_one_route:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view_one_route, parent, false);
                view.setOnClickListener(this);
                return new OneRouteHolder(view);
            case R.layout.item_recycler_view_multi_route:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view_multi_route, parent, false);
                view.setOnClickListener(this);
                return new MultiRouteHolder(view);
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view_one_route, parent, false);
                view.setOnClickListener(this);
                return new OneRouteHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OneRouteHolder) {
            RoadInfo info = mDataList.get(position);
            holder.itemView.setTag(position);
            ((OneRouteHolder) holder).tvDistance.setText(info.getDistance());
            ((OneRouteHolder) holder).tvTime.setText(info.getTime());
            ((OneRouteHolder) holder).tvTraffic.setText(info.getTraffic());
            if (position == mSelectIndex) {
                ((OneRouteHolder) holder).setTextColor(true);
            } else {
                ((OneRouteHolder) holder).setTextColor(false);

            }
        } else if (holder instanceof MultiRouteHolder) {
            RoadInfo info = mDataList.get(position);
            holder.itemView.setTag(position);
            ((MultiRouteHolder) holder).tvDistance.setText(info.getDistance());
            ((MultiRouteHolder) holder).tvTime.setText(info.getTime());
            ((MultiRouteHolder) holder).tvTraffic.setText(info.getTraffic());
            ((MultiRouteHolder) holder).tvLine.setText(info.getLine());
            if (position == mSelectIndex) {
                ((MultiRouteHolder) holder).setTextColor(true);
            } else {
                ((MultiRouteHolder) holder).setTextColor(false);

            }
        }


    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getItemViewType();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onClick(View view) {
        mSelectIndex = (int) view.getTag();
        Log.i(TAG, "onClick: " + mSelectIndex);
        notifyDataSetChanged();
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick((Integer) view.getTag());
        }
    }

    class OneRouteHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvDistance, tvTraffic;


        public OneRouteHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_recycler_time);
            tvDistance = itemView.findViewById(R.id.tv_recycler_distance);
            tvTraffic = itemView.findViewById(R.id.tv_recycler_traffic);
        }

        public void setTextColor(boolean isSelected) {
            tvTime.setEnabled(isSelected);
            tvTraffic.setEnabled(isSelected);
            tvDistance.setEnabled(isSelected);
        }
    }

    class MultiRouteHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvDistance, tvTraffic, tvLine;
        View mItemView;


        public MultiRouteHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            tvTime = itemView.findViewById(R.id.tv_recycler_time);
            tvLine = itemView.findViewById(R.id.tv_recycler_line);
            tvDistance = itemView.findViewById(R.id.tv_recycler_distance);
            tvTraffic = itemView.findViewById(R.id.tv_recycler_traffic);
        }

        void setTextColor(boolean isSelected) {
            tvTime.setEnabled(isSelected);
            tvTraffic.setEnabled(isSelected);
            tvDistance.setEnabled(isSelected);
            tvLine.setEnabled(isSelected);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(int position);
    }
}
