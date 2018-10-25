package com.colin.photowall;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class ImageDetailAty extends Activity {
    private static final String TAG = "ImageDetailAty";
    private ZoomImageView mZoomImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_details);
        mZoomImageView = findViewById(R.id.zoom_image_view);
        String imgPath = getIntent().getStringExtra("image_path");
        Log.i(TAG, "onCreate: image_path:"+imgPath);
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
        mZoomImageView.setBitmap(bitmap);
    }
}
