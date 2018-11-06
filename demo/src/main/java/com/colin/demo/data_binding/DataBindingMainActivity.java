package com.colin.demo.data_binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.colin.demo.R;
import com.colin.demo.databinding.DataBindingActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBindingMainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingActivityMainBinding binding=DataBindingUtil
                .setContentView(this, R.layout.data_binding_activity_main);

        //对象
        UserBean bean=new UserBean();
        bean.setAge(21);
        bean.setName("张三斤");
        binding.setUser(bean);

        //List
        List<String> list=new ArrayList<>();
        list.add("list1");
        list.add("list2");
        binding.setList(list);

        //Map
        HashMap<String,Object> map=new HashMap<>();
        map.put("key0", "map_value0");
        map.put("key1", "map_value1");
        binding.setMap(map);

        //String[]
        String[] arrays = {"字符串1", "字符串2"};
        binding.setArray(arrays);


        binding.setClickListener(this);

        binding.setHandler(new OnClickHandler());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.click_btn) {
            Toast.makeText(this, "点击了1", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.click2_btn) {
            Toast.makeText(this, "点击了2", Toast.LENGTH_SHORT).show();
        }
    }




}
