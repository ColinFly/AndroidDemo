package com.colin.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.colin.library.base.BaseActivity;
import com.colin.recyclerviewdemo.adapter.NormalAdapter;

import butterknife.InjectView;

/**
 * Created by colin on 16-11-2.
 */

public class LineAty extends BaseActivity {
    private static final String TAG = "LineAty";
    @InjectView(R.id.line_recycler)
    RecyclerView mRecyclerView;
    @InjectView(R.id.add)
    Button add;
    @InjectView(R.id.remove)
    Button remove;
    private NormalAdapter mNormalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_line);
        mNormalAdapter = new NormalAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        mRecyclerView.setAdapter(mNormalAdapter);
//        mRecyclerView.setAdapter(new MultiItemAdapter(mContext));
        mNormalAdapter.setOnItemClickListener(new NormalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                mNormalAdapter.addNewItem();
                Log.d(TAG, "onItemClick: "+position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

//    @OnClick({R.id.add, R.id.remove})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.add:  mNormalAdapter.addNewItem();return;
//            case R.id.remove: mNormalAdapter.removeItem();
//        }
//    }


    //    public void add(View view) {
//        mNormalAdapter.addNewItem();
//    }
//
//    public void remove(View view) {
//        mNormalAdapter.removeItem();
//    }
}
