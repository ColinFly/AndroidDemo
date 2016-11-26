package com.colin.androidserver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by libin on 2016/8/25.
 * 客户端wifi传图给App服务器，App显示在Activity并存储
 */
public class UploadImgHandler implements IResourseUriHandler {
    private String acceptPrfix = "/upload_image/";

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
//        writer.println("from upload image handler");
//        writer.flush();
//        long totalLength = Long.parseLong(httpContext.getRequstHeaderValue("Content-Length"));
        String tmpPath = "/mnt/sdcard/test_image.jpg";
        FileOutputStream fileOutputStream = new FileOutputStream(tmpPath);
        InputStream inputStream = httpContext.getUnderlySocket().getInputStream();
        byte[] buffer=new byte[10240];
        int nReaded = 0;
//        long nLeftLength = totalLength;
        while ( ((nReaded = inputStream.read(buffer))> 0) ) {
            fileOutputStream.write(buffer,0,nReaded);
        }
        fileOutputStream.close();
        OutputStream outputStream = httpContext.getUnderlySocket().getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        printStream.println("HTTP/1.1 200 OK");
        printStream.println();

        onImageLoaded(tmpPath);
    }

    protected void onImageLoaded(String path) {
        //默认一个空的实现，可以通过重载这个方法去完成对路径的回调
    }
}
