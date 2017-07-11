package com.kuaima;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.kuaima.bean.Article;
import com.kuaima.bean.Data;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ButterKnife.inject(this);
    }


    StringCallback callback = new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            Log.i(TAG, "onError: ");
        }

        @Override
        public void onResponse(String response, int id) {
            Log.i(TAG, "onResponse: " + response);
        }
    };


    private String articleId;

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                NetWorkManager.getArticles(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        Article article = gson.fromJson(response, Article.class);
                        List<Article.DataEntity> articles = article.getData();
                        articleId = String.valueOf(articles.get(articles.size()-1).getId());
                        Log.i(TAG, "current id: "+articleId);
//                        articleId = "542971";
                        for (int i = 0; i < articles.size(); i++) {
                            Log.i(TAG, "onResponse: articleId-->"+articles.get(i).getId());
                            Log.i(TAG, "onResponse: title-->"+articles.get(i).getTitle());
                        }
                    }
                });
                break;
            case R.id.btn2:
                //评论
                NetWorkManager.getArticleAddition(callback,articleId);
                break;
            case R.id.btn3:
                NetWorkManager.getArticleView(mContext,articleId,callback);
                break;
            case R.id.btn4:
                NetWorkManager.queryCoin(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse: "+response);
                        Gson gson=new Gson();
                        Data data = gson.fromJson(response,Data.class);
                        Log.i(TAG, "onResponse: coin-->"+data.getData().getToday_coin_income());
                    }
                });
                break;
            case R.id.btn5:
                NetWorkManager.getArticleDetail(callback,articleId);
                break;
            case R.id.btn6:
                break;
        }
    }

}
