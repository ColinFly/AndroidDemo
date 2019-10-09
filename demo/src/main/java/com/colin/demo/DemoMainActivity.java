package com.colin.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.colin.demo.android_room_with_view.WordMainActivity;
import com.colin.demo.constraint_layout.ConstraintAty;
import com.colin.demo.data_binding.DataBindingMainActivity;
import com.colin.demo.data_binding.double_bind.DoubleBindActivity;
import com.colin.demo.data_binding.recyclerview.RecyclerViewActivity;
import com.colin.demo.mvp.view.BaseActivity;
import com.colin.demo.mvp_demo.view.LoginActivity;
import com.colin.demo.okhttp.OkHttpActivity;
import com.colin.demo.opengl.AirHockeyRenderActivity;
import com.colin.demo.recyclerview.RecyclerViewAty;
import com.colin.demo.videoview.VideoViewAty;
import com.colin.demo.websocket.WebSocketAty;

import butterknife.OnClick;

public class DemoMainActivity extends BaseActivity {
    @Override
    public int getViewLayout() {
        return R.layout.activity_demo_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState, Intent args) {

    }


    @OnClick({R.id.btn_open_camera2,R.id.btn_open_camera1,R.id.btn_uri, R.id.btn_video_view, R.id.btn_open_gl, R.id.btn_web_socket, R.id.btn_recycler_view, R.id.btn_okhttp, R.id.btn_android_room_with_view, R.id.btn_mvp, R.id.btn_data_binding,
            R.id.btn_data_bind_recycler_view, R.id.btn_double_bind, R.id.btn_constraint_view})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_constraint_view:
                intent = new Intent(this, ConstraintAty.class);
                break;
            case R.id.btn_recycler_view:
                intent = new Intent(this, RecyclerViewAty.class);
                break;
            case R.id.btn_android_room_with_view:
                intent = new Intent(this, WordMainActivity.class);
                break;
            case R.id.btn_mvp:
                intent = new Intent(this, LoginActivity.class);
                break;
            case R.id.btn_data_binding:
                intent = new Intent(this, DataBindingMainActivity.class);
                break;
            case R.id.btn_data_bind_recycler_view:
                intent = new Intent(this, RecyclerViewActivity.class);
                break;
            case R.id.btn_double_bind:
                intent = new Intent(this, DoubleBindActivity.class);
                break;
            case R.id.btn_okhttp:
                intent = new Intent(this, OkHttpActivity.class);
                break;
            case R.id.btn_web_socket:
                intent = new Intent(this, WebSocketAty.class);
                break;
            case R.id.btn_open_gl:
                intent = new Intent(this, AirHockeyRenderActivity.class);
                break;
            case R.id.btn_video_view:
                intent = new Intent(this, VideoViewAty.class);
                break;
            case R.id.btn_uri:
//                intent = makeIntent("wmmapauto://map/openFavorite");

                //语音发起导航
                intent = makeIntent("wmmapauto://map/navi?src=com.iflytek.cutefly.speechclient&destname=东方体育中心&destlat=31.1534473113927&destlng=121.48035206401129&coordtype=gcj02");
                break;
            case R.id.btn_open_camera1:
                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            break;
            case R.id.btn_open_camera2:

                break;
        }
//        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
//        Bitmap bp = (Bitmap) data.getExtras().get("data");
//        imgFavorite.setImageBitmap(bp);
    }



    @NonNull
    private Intent makeIntent(String url) {
        Intent intent;
        Uri uri = Uri.parse(url);
        intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setPackage("com.ivi.map");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(uri);
        return intent;
    }


}
