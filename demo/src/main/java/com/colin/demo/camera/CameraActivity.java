package com.colin.demo.camera;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.colin.demo.R;

public class CameraActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }
}
