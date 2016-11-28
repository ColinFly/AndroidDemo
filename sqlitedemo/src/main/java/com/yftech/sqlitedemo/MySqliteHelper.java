package com.yftech.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by colin on 16-11-28.
 * 提供了数据库对象的获取函数
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    private static final String TAG = "MySqliteHelper";
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context) {
        super(context,Constant.DB_NAME,null,Constant.DB_VERSION);
    }

    /**
     * @param db　创建的数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");
        String sql = "create table "+Constant.TABLE_NAME+"("+Constant._ID+" Integer primary key,"+Constant.NAME+" varchar(10),"+Constant.AGE+" Integer)";
        db.execSQL(sql);
    }

    /**
     * 版本更新的回调
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: ");

    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.d(TAG, "onOpen: ");
        super.onOpen(db);
    }
}
