package com.colin.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.colin.library.base.BaseActivity;
import com.colin.recyclerviewdemo.adapter.MultiItemAdapter;
import com.colin.recyclerviewdemo.adapter.NormalAdapter;

import butterknife.InjectView;

/**
 * Created by colin on 16-11-2.
 */

public class LineAty extends BaseActivity {
    @InjectView(R.id.line_recycler)
    RecyclerView mRecyclerView;
    private NormalAdapter mNormalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_line);
        mNormalAdapter=new NormalAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,OrientationHelper.VERTICAL));
//        mRecyclerView.setAdapter(mNormalAdapter);
        mRecyclerView.setAdapter(new MultiItemAdapter(mContext));
    }

}
