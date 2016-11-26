package com.colin.androidserver;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by libin on 2016/8/25.
 * 客户端访问App服务器，App返回内置网页功能
 */
public class ResourceInAssetHandler implements IResourseUriHandler {
    private String acceptPrfix = "/static/";
    private Context context;

    public ResourceInAssetHandler(Context context) {
        this.context = context;
    }

    @Override
    public boolean accept(String uri) {
        return uri.startsWith(acceptPrfix);

    }

    @Override
    public void handle(String uri, HttpContext httpContext) throws IOException {
//        OutputStream outputStream=httpContext.getUnderlySocket().getOutputStream();
//        PrintWriter writer = new PrintWriter(outputStream);
//        writer.println("HTTP/1.1 200 OK");
//        writer.println();
//        writer.println("from resource in assets handler");
//        writer.flush();
        int startIndex = acceptPrfix.length();
        String assetPath = uri.substring(startIndex);
        InputStream inputStream = context.getAssets().open(assetPath);
        byte[] raw = StreamToolKit.readRawFromSteam(inputStream);//读到byte数组里面
        inputStream.close();
        OutputStream outputStream = httpContext.getUnderlySocket().getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        printStream.println("HTTP/1.1 200 OK");
        printStream.println("Content_Length" + raw.length);
        if (assetPath.endsWith("html")) {
            printStream.println("Content-Type:text/html");

        } else if (assetPath.endsWith("js")) {
            printStream.println("Content-Type:text/js");
        } else if (assetPath.endsWith("css")) {
            printStream.println("Content-Type:text/css");
        } else if (assetPath.endsWith("jpg")) {
            printStream.println("Content-Type:text/jps");
        } else if (assetPath.endsWith("png")) {
            printStream.println("Content-Type:text/png");
        }
        printStream.println();
        //body部分
        printStream.write(raw);
    }
}