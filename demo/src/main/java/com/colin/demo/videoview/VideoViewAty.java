package com.colin.demo.videoview;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.MediaController;
import android.widget.VideoView;

import com.colin.demo.R;

public class VideoViewAty extends Activity {

    private VideoView mVideoView;
    private MediaController mMediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        mVideoView = findViewById(R.id.video_view);
        mMediaController=new MediaController(this);

        mVideoView.setMediaController(mMediaController);
        mMediaController.setMediaPlayer(mVideoView);

        mVideoView.setVideoURI(Uri.parse("http://10.1.30.150:8080/out2.mp4"));
        mVideoView.requestFocus();
        mVideoView.start();
    }
}
