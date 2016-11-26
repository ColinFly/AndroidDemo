package com.colin.androidserver;

/**
 * Created by libin on 2016/8/23.
 */
public class NetConfig {
    //端口
    private int port;
    //最大并发数
    private int maxparallels;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxparallels() {
        return maxparallels;
    }

    public void setMaxparallels(int maxparallels) {
        this.maxparallels = maxparallels;
    }
}
