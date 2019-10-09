package com.colin.demo.okhttp;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class HttpManager {
//    参考文章:https://www.jianshu.com/p/9aa969dd1b4d
    private static final String TAG = "HttpManager";
    private static final HttpManager ourInstance = new HttpManager();

    static HttpManager getInstance() {
        return ourInstance;
    }

    private HttpManager() {
        init();
    }


    private OkHttpClient mOkhttpClient;
    private void init() {
        Log.i(TAG, "init: ");
        mOkhttpClient=new OkHttpClient();
    }

    /**
     * 同步的方式调用
     */
    public void getInSync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //同步请求，主线程不能执行网络请求，否则error
                final Request request = new Request.Builder().get().url("https:www.baidu.com").build();
                Call call = mOkhttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    Log.i(TAG, "getInSync: "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 异步的方式调用
     */
    public void  getInAsync(){
        final Request request = new Request.Builder().get().url("https:www.baidu.com").build();
        Call call = mOkhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: "+response.body().string());
            }
        });
    }



    public void postValue() {
//         使用聚合数据的新闻接口,免费
//        请求地址：http://v.juhe.cn/toutiao/index
//        请求参数：type=top&key=3e1af04448fbab6af2f270c607ccfd15
//        请求方式：POST

        FormBody formBody = new FormBody.Builder()
                .add("type", "top")
                .add("key","3e1af04448fbab6af2f270c607ccfd15")
                .build();
        final Request request=new Request.Builder()
                .url("http://v.juhe.cn/toutiao/index")
                .post(formBody)
                .build();
        Call call = mOkhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: ");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: "+response.body().string());

            }
        });
    }


    /**
     * 提交一个string特别是json str
     */
    public void postString() {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"),
                "{username:admin;password:admin}");
//        下面的步骤就和上面的post一样了，这里主要是request的构造方式不一样
    }

    public void postFile() {
//        其实最主要的还是构架我们自己的RequestBody
        File file = new File(Environment.getExternalStorageDirectory(), "1.png");
        if (!file.exists()){
            Log.i(TAG, "postFile: 文件不存在");
        }else{
            RequestBody requestBody2 = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        }
//        application/octet-stream表示我们的文件是任意二进制数据流,当然你也可以换成更具体的image/png
    }

    public void postForm() {
// 我们在网页上经常会遇到用户注册的情况,需要你输入用户名,密码,还有上传头像,
// 这其实就是一个表单,那么接下来我们看看如何利用OkHttp来进行表单提交。
// 经过上面的学习,大家肯定也懂,主要的区别就在于构造不同的RequestBody传递给post方法即可.

// 这里我们会用到一个MuiltipartBody,这是RequestBody的一个子类,
// 我们提交表单就是利用这个类来构建一个RequestBody,
// 下面的代码我们会发送一个包含用户民、密码、头像的表单到服务端
        File file = new File(Environment.getExternalStorageDirectory(), "1.png");
        if (!file.exists()){
            return;
        }
        RequestBody muiltipartBody = new MultipartBody.Builder()
                //一定要设置这句
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", "admin")//
                .addFormDataPart("password", "admin")//
                .addFormDataPart("myfile", "1.png", RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();
    }

    public void getFile() {
        //下载一个文件，这里以图片为例，可以发现与一般的get差不多
//        一方面，通过字节流可以写到本地文件
//        另一方面，也可以通过字节流转化为bitmap,设置到view上显示出来
//        InputStream is = response.body().byteStream();
//
//        final Bitmap bitmap = BitmapFactory.decodeStream(is);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                imageView.setImageBitmap(bitmap);
//            }
//        });
//
//        is.close();


        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url("https://www.baidu.com/img/bd_logo1.png")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("moer", "onFailure: ");;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //拿到字节流
                InputStream is = response.body().byteStream();

                int len = 0;
                File file  = new File(Environment.getExternalStorageDirectory(), "n.png");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1){
                    fos.write(buf, 0, len);
                }

                fos.flush();
                //关闭流
                fos.close();
                is.close();
            }
        });


    }

}
