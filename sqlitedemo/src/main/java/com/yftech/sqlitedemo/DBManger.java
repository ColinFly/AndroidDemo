package com.yftech.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

/**
 * Created by colin on 16-11-28.
 *操作数据库的工具类
 */

public class DBManger {
    private static MySqliteHelper helper;

    public static MySqliteHelper getInstance(Context context) {
        if (helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    public static void execSQL(SQLiteDatabase db, String sql) {
        if (db != null) {
            if (!TextUtils.isEmpty(sql)) {
                db.execSQL(sql);
            }
        }

    }

}
