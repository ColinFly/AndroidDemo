package com.colin.library.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public final class SharedPreferencesUtils {
    /**
     * 保存的文件名字
     */
    public static final String PREFERENCE_NAME = "BDMAP";
    private static final int DEFALUT_VALUE_INT = -1;

    private SharedPreferencesUtils() {
        throw new AssertionError();
    }

    /**
     * 存放字符串
     *
     * @param context app的context
     * @param key 键
     * @param value 值
     * @return true:存放成功
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 获取string
     *
     * @param context cotext上下文
     * @param key key 字符串
     * @return 不存在返回null
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * 取出字符串
     *
     * @param context app的context
     * @param key 键
     * @param defaultValue 默认的值
     * @return 存储的字符串
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
  }

  /**
   *
   * @param context app的context
   * @param key key 字符串
   * @param value 要存储的值
   * @return true:存放成功
   */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        return editor.commit();
  }

  /**
   *
   * @param context app的context
   * @param key key 字符串
   * @return 对应的值
   */
    public static int getInt(Context context, String key) {
        return getInt(context, key, DEFALUT_VALUE_INT);
  }

  /**
   *
   * @param context app的context
   * @param key key 字符串
   * @param defaultValue 默认返回值
   * @return 对应的值
   */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
  }

  /**
   *
   * @param context app的context
   * @param key key  字符串
   * @param value 要存储的值
   * @return true:存放成功
   */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        return editor.commit();
  }

  /**
   *
   * @param context app的context
   * @param key key 字符串
   * @return 存放的值
   */
    public static long getLong(Context context, String key) {
        return getLong(context, key, DEFALUT_VALUE_INT);
  }

  /**
   *
   * @param context app的context
   * @param key key 字符串
   * @param defaultValue 默认返回值
   * @return 存放的值
   */
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
  }

  /**
   *
   * @param context app的context
   * @param key key 字符串
   * @param value 要存储的值
   * @return true:存成功
   */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        return editor.commit();
  }

  /**
   *
   * @param context app的context
   * @param key key 字符串
   * @return 存放的值
   */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, DEFALUT_VALUE_INT);
  }

  /**
   *
   * @param context app的context
   * @param key 存储的key字符串
   * @param defaultValue 默认返回值
   * @return 存放的值
   */
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, defaultValue);
  }

  /**
   *
   * @param context app的context
   * @param key key 字符串
   * @param value 存储的值
   * @return true:存成功
   */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        return editor.commit();
  }

  /**
   *
   * @param context app的context
   * @param key key 字符串
   * @return 存放的值
   */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
  }

  /**
   *
   * @param context app的context
   * @param key key 字符串
   * @param defaultValue 默认返回值
   * @return 存放的值
   */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context app的context
     * @param key 
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     *
     * @param context app的context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context app的context
     * @param key key 字符串
     * @return true:存在
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context app的context
     * @return 存放的键值对集合
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }
}
