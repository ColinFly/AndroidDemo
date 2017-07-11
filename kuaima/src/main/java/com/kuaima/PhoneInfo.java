package com.kuaima;

import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by colin on 17-6-9.
 */

public class PhoneInfo {
    public static String a = "info";
    private static final String b = (Environment.getExternalStorageDirectory().getPath() + "/system/");

    public static String a() {
        String obj = "";
        File file = new File(b + a);
        if (file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                obj = bufferedReader.readLine();
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TextUtils.isEmpty(obj) ? "" : obj;
    }
}
