package com.kuaima;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.UUID;

import cn.etouch.ecalendar.common.libs.EcalendarLib;
import okhttp3.Call;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by colin on 17-6-8.
 */

public class NetWorkManager {
    private static final String TAG = "NetWorkManager";
    private static final String authToken = "eyJhY2N0ayI6IjAuNzMwMDE5Njg3MTcyMTMyNTo5ZmFmYzhqby4xNDk3MjcxNTQ4MDQ0IiwidXAiOiJBTkRST0lEIiwiZGV2aWNlIjoiQW5kcm9pZFNES2J1aWx0Zm9yeDg2MDAwMDAwMDAwMDAwMDAwIiwidWlkIjoxMDAxMDYxODk0MTJ9";
    private static final String ua1 = "Mozilla/5.0 (Linux; Android 6.0; Android SDK built for x86 Build/MASTER; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.119 Mobile Safari/537.36 ssy={Android;KuaiMaBrowser;V1.3.4;tencent;;MOBILE}";
    private static final String ua = "Dalvik/2.1.0 (Linux; U; Android 6.0; Android SDK built for x86 Build/MASTER)";


    public static void test() {
//        NetWorkManager.get("https://api.github.com", null, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.i(TAG, "onError: ");
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Log.i(TAG, "onResponse: "+response);
//
//            }
//        });
    }

    public static void get(String url, HashMap<String, String> map, StringCallback callback) {
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("User-Agent",ua)
                .params(map)
                .build()
                .execute(callback);
    }

    public static void get1(String url, HashMap<String, String> map, StringCallback callback) {
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("User-Agent",ua1)
                .params(map)
                .build()
                .execute(callback);
    }

    public static void post(String url, HashMap<String,String> map, StringCallback callback) {
        OkHttpUtils
                .post()
                .url(url)

                .params(map)
                .build()
                .execute(callback);
    }


    public static void login(Context context) {
//        Hashtable<String,String> hashtable = new Hashtable<>();
        HashMap<String,String> hashtable=new HashMap<>();
        JSONObject jSONObject = new JSONObject();
                long currentTimeMillis = System.currentTimeMillis();
        hashtable.put("city_key", "");
        hashtable.put("app_key", "27817749");
        hashtable.put("is_root", "0");
        hashtable.put("ver_code", "134");
        hashtable.put("city", "");
        hashtable.put("ver_name", "1.3.4");
        hashtable.put("phone", "18820243175");
        hashtable.put("timestamp", String.valueOf(currentTimeMillis));
        String url = "http://api.kuaima.cn/km_task/api/v1/login/dynamicPwd";
        hashtable.put("app_sign", getAppSign("POST", Uri.parse(url).getPath(),currentTimeMillis));
        hashtable.put("auth_token", getAuthToken());
        try {
            jSONObject.put("phone", "18820243175");
            jSONObject.put("pwd", "312709");
            jSONObject.put("platform", "ANDROID");
            jSONObject.put("idfa", "100106189412");
            String doTheEncrypt = EcalendarLib.getInstance().doTheEncrypt(context, jSONObject.toString(), 6);
            Log.i(TAG, "login: 加密: " + jSONObject.toString() + " x: " + doTheEncrypt);
            hashtable.put("x", doTheEncrypt);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        post("http://api.kuaima.cn/km_task/api/v1/login/dynamicPwd?", hashtable, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.i(TAG, "onResponse: "+response);
            }
        });
    }

    public static String getDeivceId(Context mActivity) {
        TelephonyManager telephonyManager = (TelephonyManager) mActivity.getSystemService(Context.TELEPHONY_SERVICE);
        final String deviceId = telephonyManager.getDeviceId();
        final String androidId = Settings.Secure.getString(mActivity.getContentResolver(), Settings.Secure.ANDROID_ID);
        String uuid = "";
        if (deviceId != null) {
            uuid = deviceId;
        } else {
            if (!"9774d56d682e549c".equals(androidId)) {
                uuid = androidId;
            } else {
                uuid = UUID.randomUUID().toString();
            }
        }
        return uuid;
    }


    public static void getArticleAddition(StringCallback callback, String articleId) {
        String url = "http://api.kuaima.cn/km_task/api/v2/article/addition/"+articleId;
        HashMap<String, String> map = new HashMap<>();
        addBasicParams(url, map);
        get(url+"?",map,callback);
    }


    public static void getArticles(StringCallback callback) {
        String url = "http://api.kuaima.cn/km_task/api/v1/articles/31";
        HashMap<String, String> map = new HashMap<>();
        addBasicParams(url, map);
        map.put("page_size", "20");
        map.put("page", "1");
        get(url+"?",map,callback);
    }

    public static void getArticleDetail(StringCallback callback,String articleId) {
        String url = "http://api.kuaima.cn/km_task/api/v2/article/details/"+articleId;
        HashMap<String, String> map = new HashMap<>();
        map.put("app_key", "27817749");
        map.put("auth_token", null);
        map.put("callback", "jsonp96");
        get(url+"?",map,callback);
    }

    public static void getArticleView(Context context, String articleId, StringCallback callback) {
        Log.i(TAG, "getArticleView:articleId "+articleId);
        String url = "http://api.kuaima.cn/km_task/api/v2/article/view";
        HashMap<String, String> map = new HashMap<>();
        addBasicParams(url, map);
        map.put("articleId", articleId);
        String doTheEncrypt = EcalendarLib.getInstance().doTheEncrypt(context, "", 6);
        Log.i(TAG, "doTheEncrypt: "+doTheEncrypt);
        map.put("rid",doTheEncrypt );
        get(url+"?",map,callback);
    }

    public static void queryCoin(StringCallback callback) {
        String url = "http://api.kuaima.cn/km_task/api/v1/userinfo/100106189412";
        HashMap<String, String> map = new HashMap<>();
        addBasicParams(url, map);
        get(url+"?",map,callback);
    }

    private static void addBasicParams(String url, HashMap<String, String> map) {
        long currentTimeMillis = System.currentTimeMillis();
        map.put("city_key", "");
        map.put("app_key", "27817749");
        map.put("is_root", "0");
        map.put("ver_code", "134");
        map.put("city", "");
        map.put("ver_name", "1.3.4");
        map.put("channel", "tencent");
        map.put("timestamp", String.valueOf(currentTimeMillis));
        map.put("app_sign", getAppSign("GET", Uri.parse(url).getPath(),currentTimeMillis));
        map.put("auth_token", authToken);
    }


    private static String getAppSign(String str, String str2, long l) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append("&").append(str2).append("&").append(String.valueOf(l)).append("&").append("363d33296f7f4263942dcb5fe8ae4a40");
//        Log.i(TAG, "getAppSign: "+b(hehe(stringBuffer.toString())));
        return b(hehe(stringBuffer.toString()));
    }

    private static byte[] hehe(String string) {
        return getMd5(string.getBytes());
    }

    private static String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        String str = "0123456789abcdef";
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append(str.charAt((bArr[i] >> 4) & 15));
            stringBuilder.append(str.charAt(bArr[i] & 15));
        }
        return stringBuilder.toString();
    }

    private static byte[] getMd5(byte[] bArr) {
        MessageDigest instance;
        try {
            instance = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            instance = null;
        }
        if (instance == null) {
            return null;
        }
        instance.update(bArr);
        return instance.digest();
    }

    private static String getAuthToken() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("acctk", "0.7415387345051501:9fafc8jo.1496885710700");
            jSONObject.put("up", "ANDROID");
            jSONObject.put("device", "AndroidSDKbuiltforx86000000000000000");
            jSONObject.put("uid", "100106189412");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String result = Md5Util.a(jSONObject.toString().getBytes());
        Log.i(TAG, "getAuthToken: "+result);
        return result;
    }

}
