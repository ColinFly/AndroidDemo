package com.colin.demo.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpDemo {
    public static void main(String[] args) {
        OkHttpClient httpClient=new OkHttpClient();
        final Request request = new Request.Builder().get().url("https:www.baidu.com").build();
        Call call = httpClient.newCall(request);
        //同步的请求方式
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //异步的请求方式
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String res = response.body().string();
//                System.out.println(res);
//            }
//        });


    }
}
