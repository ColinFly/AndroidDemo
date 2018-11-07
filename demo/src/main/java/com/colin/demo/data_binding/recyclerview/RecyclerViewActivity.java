package com.colin.demo.data_binding.recyclerview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.colin.demo.R;
import com.colin.demo.databinding.ActivityDataBindRecyclerViewBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private MultiItemAdapter mAdapter;
    private List<IBindingAdapterItem> mList = new ArrayList<>(); //数据源

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindRecyclerViewBinding binding=DataBindingUtil.setContentView(this, R.layout.activity_data_bind_recycler_view);
        initData();
        mAdapter = new MultiItemAdapter(this, mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mList.add(new TextItem("标题1"));
        mList.add(new FruitItem(R.mipmap.fruit, "苹果"));
        mList.add(new FruitItem(R.mipmap.fruit, "香蕉"));
        mList.add(new TextItem("标题2"));
        mList.add(new TextItem("标题3"));
        mList.add(new FruitItem(R.mipmap.fruit, "桃子"));
        mList.add(new TextItem("标题4"));
        mList.add(new FruitItem(R.mipmap.fruit, "梨"));
        mList.add(new TextItem("标题5"));
    }
}
