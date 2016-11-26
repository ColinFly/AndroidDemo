package com.colin.androidserver;

import java.net.Socket;
import java.util.HashMap;

/**
 * Created by libin on 2016/8/24.
 * Http请求信息的存储类，主要存键值对和socket
 */
public class HttpContext {

    private final HashMap<String, String> requestHeaders;
    private Socket underlySocket;

    public HttpContext() {
        requestHeaders = new HashMap<String, String>();
    }

    public void setUnderlySocket(Socket underlySocket) {
        this.underlySocket = underlySocket;
    }

    public Socket getUnderlySocket() {
        return underlySocket;
    }

    public void addRequestHeader(String headName, String headValue) {
        requestHeaders.put(headName, headValue);
    }

    public String getRequstHeaderValue(String name) {
        return requestHeaders.get(name);
    }
}
