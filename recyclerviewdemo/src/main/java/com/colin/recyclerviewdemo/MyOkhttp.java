package com.colin.recyclerviewdemo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by colin on 16-11-2.
 * 响应get请求
 */

public class MyOkhttp {
    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    public static String get(String url) {
        mOkHttpClient.newBuilder().connectTimeout(10 * 1000, TimeUnit.MILLISECONDS);
        Request request = new Request.Builder().url(url).build();

        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().toString();
            } else {
                throw new IOException("Unexpected code: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
