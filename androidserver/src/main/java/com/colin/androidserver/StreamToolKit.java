package com.colin.androidserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by libin on 2016/8/23.
 */
public class StreamToolKit {
//    读取一行
    public static String readLine(InputStream ips) throws IOException {
        StringBuilder sb=new StringBuilder();
        int c1 = 0;
        int c2 = 0;
        while (c2 != -1 && !(c1 == '\r' && c2 == '\n')) {
            c1 = c2;
            c2 = ips.read();
            sb.append((char) c2);
        }

        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    //将输入流变成输出流
    public static byte[] readRawFromSteam(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];//10k的大小
        int nReaded;
        while ((nReaded = inputStream.read(buffer)) > 0) {
            bos.write(buffer,0,nReaded);
        }
        return bos.toByteArray();
    }
}
