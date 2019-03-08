package com.colin.demo.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.colin.demo.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAty extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RecyclerViewAty";
    RecyclerView mRecyclerView;
    private List<RoadInfo> mDataList;
    private ViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = findViewById(R.id.recyclerView_route);
        mDataList = new ArrayList<>();

        mDataList.add(new OneRoadInfo("3小时55分钟", "500公里", "红绿灯11个", "常规路线"));

        mDataList.add(new RoadInfo("3小时55分钟", "500公里", "红绿灯11个", "常规路线"));
        mDataList.add(new RoadInfo("3小时55分钟", "500公里", "红绿灯11个", "常规路线"));
        mAdapter = new ViewAdapter(mDataList, this);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.btn_add_route).setOnClickListener(this);
        findViewById(R.id.btn_delete_route).setOnClickListener(this);
        findViewById(R.id.btn__layout_orientation).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_route:
                mDataList.add(new RoadInfo("3小时55分钟", "500公里", "红绿灯11个", "常规路线"));
                LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_delete_route:
                if (mDataList.size() > 0) {
                    mDataList.remove(mDataList.get(0));
                }
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.btn__layout_orientation:
                mDataList.add(new RoadInfo("3小时55分钟", "500公里", "红绿灯11个", "常规路线"));
                LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(layoutManager2);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();


                break;
        }

    }
}
