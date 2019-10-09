package com.colin.library.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class JsonUtil {
    private static Gson gson=new Gson();
    private JsonUtil() {

    }

    public static String toJsonStr(Object object) {
        return gson.toJson(object);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> cls) {
        return gson.fromJson(jsonStr, cls);
    }

    public static <T> List<T> json2List(String str, Class<T> cls) {
        return gson.fromJson(str, new TypeToken<List<T>>() {
        }.getType());
    }

    public static <T> Map<String, T> jsonToMaps(String jsonString) {
        return gson.fromJson(jsonString, new TypeToken<Map<String, T>>() {
        }.getType());
    }

    public static <T> List<Map<String, T>> jsonToListMaps(String jsonString) {
        return gson.fromJson(jsonString,
                new TypeToken<List<Map<String, T>>>() {
                }.getType());
    }
}
