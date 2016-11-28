package com.yftech.sqlitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MySqliteHelper mMySqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMySqliteHelper = DBManger.getInstance(this);
    }


    public void createDb(View view) {
        //getReadableDatabase和getWriteableDatabase都是创建或打开数据库
        SQLiteDatabase database=mMySqliteHelper.getReadableDatabase();
    }


    public void insert(View view) {
        SQLiteDatabase db = mMySqliteHelper.getWritableDatabase();
        String sql = "insert into " + Constant.TABLE_NAME + " values(1,'zhangsan',20)";
        DBManger.execSQL(db, sql);
        db.close();
    }
}
