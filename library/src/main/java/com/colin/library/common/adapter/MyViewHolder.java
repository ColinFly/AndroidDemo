package com.colin.library.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder {
	private final SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	private MyViewHolder(Context context, ViewGroup parent, int layoutId,
						 int position) {
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);

	}

	public static MyViewHolder get(Context context, View convertView,
								   ViewGroup parent, int layoutId, int position) {
		MyViewHolder holder = null;
		if (convertView == null) {
			holder = new MyViewHolder(context, parent, layoutId, position);
		} else {
			holder = (MyViewHolder) convertView.getTag();
			holder.mPosition = position;
		}
		return holder;
	}

	public View getConvertView() {
		return mConvertView;
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public MyViewHolder setText(int viewId, String text) {
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	public MyViewHolder setText(int viewId, CharSequence text) {
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}


	public MyViewHolder setTextBackground(int viewId, int resId) {
		TextView view = getView(viewId);
		view.setBackgroundResource(resId);
		return this;
	}
	public MyViewHolder setImageResource(int viewId, int drawableId) {
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);
		return this;
	}

	public MyViewHolder setImageBitmap(int viewId, Bitmap bm) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bm);
		return this;
	}


	public int getPosition() {
		return mPosition;
	}

	public MyViewHolder setTextColor(int viewId, int color) {
		TextView view = getView(viewId);
		view.setTextColor(color);
		return this;
	}

	public MyViewHolder setTextViewVisiable(int viewId, int visibility) {
		TextView view = getView(viewId);
		view.setVisibility(visibility);
		return this;
	}

	public MyViewHolder setChecked(int vieId, boolean value) {
		CheckBox view = getView(vieId);
		view.setChecked(value);
		return this;
	}


}
