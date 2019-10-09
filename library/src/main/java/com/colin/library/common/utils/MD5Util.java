package com.colin.library.common.utils;

import java.security.MessageDigest;

public class MD5Util {

    // 字符数组，用来存放十六进制字符
    private static final char[] HEX_REFER_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
        '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String toMd5(String str) {
        String md5Str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            // 完成计算，返回结果数组
            byte[] b = md.digest();
            md5Str = byteArrayToHex(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    public static String byteArrayToHex(byte[] bytes) {
        // 一个字节占8位，一个十六进制字符占4位；十六进制字符数组的长度为字节数组长度的两倍
        char[] hexChars = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) {
            // 取字节的高4位
            hexChars[index++] = HEX_REFER_CHARS[b >>> 4 & 0xf];
            // 取字节的低4位
            hexChars[index++] = HEX_REFER_CHARS[b & 0xf];
        }
        return new String(hexChars);
    }

}
