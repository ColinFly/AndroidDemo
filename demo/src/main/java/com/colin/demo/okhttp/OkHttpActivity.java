package com.colin.demo.okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class OkHttpActivity extends AppCompatActivity {
    private static final String TAG = "OkHttpActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout=new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(generateButton(100,"Get请求(同步)",mClickListener));
        layout.addView(generateButton(101,"Get请求(异步)",mClickListener));
        layout.addView(generateButton(102,"Post提交键值对",mClickListener));
        layout.addView(generateButton(103,"Post提交字符串",mClickListener));
        layout.addView(generateButton(104,"Post请求上传文件",mClickListener));
        layout.addView(generateButton(105,"Post请求表单",mClickListener));
        layout.addView(generateButton(106,"Post请求下载文件",mClickListener));
        layout.addView(generateButton(107,"文件传输加进度条",mClickListener));
        setContentView(layout);
    }

    private View.OnClickListener mClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i(TAG, "onClick: "+view.getId());
            switch (view.getId()) {
                case 100:
                    HttpManager.getInstance().getInSync();
                    break;
                case 101:
                    HttpManager.getInstance().getInAsync();
                    break;
                case 102:
                    HttpManager.getInstance().postValue();
                    break;
            }

        }
    };

    public Button generateButton(int id, String text, View.OnClickListener listener) {
        Button button = new Button(this);
        button.setId(id);
        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        button.setText(text);
        if (listener != null) {
            button.setOnClickListener(listener);
        }
        return button;
    }
}
