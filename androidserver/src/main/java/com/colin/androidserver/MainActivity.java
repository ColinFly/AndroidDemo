package com.colin.androidserver;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SimpleHttpServer httpServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetConfig netConfig=new NetConfig();
        netConfig.setPort(8088);
        netConfig.setMaxparallels(50);
        httpServer=new SimpleHttpServer(netConfig);
        httpServer.registerResourceHandler(new ResourceInAssetHandler(this));
        httpServer.registerResourceHandler(new UploadImgHandler(){//重载这个方法来回调图片路径到Activity
            @Override
            protected void onImageLoaded(String path) {
                super.onImageLoaded(path);
                showImage(path);
            }
        });
        httpServer.startAsync();
    }

    private void showImage(final String path) {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               ImageView imageView = (ImageView) findViewById(R.id.image);
               Bitmap bitmap=BitmapFactory.decodeFile(path);
               imageView.setImageBitmap(bitmap);
           }
       });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            httpServer.stopAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
