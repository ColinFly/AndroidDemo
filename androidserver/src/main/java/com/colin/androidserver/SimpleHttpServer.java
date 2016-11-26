package com.colin.androidserver;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by libin on 2016/8/23.
 * 监听远程连接，并解析Header的头数据
 * Accept Socket-->解析header-->URL路由
 */
public class SimpleHttpServer {
    private final NetConfig netConfig;
    private final ExecutorService threadPool;
    private boolean isEnable;
    private ServerSocket serverSocket;
    private static final String TAG = "SimpleHttpServer";
    private HashSet<IResourseUriHandler> resourceHandlers;

    public SimpleHttpServer(NetConfig netConfig) {
        this.netConfig = netConfig;
        threadPool = Executors.newCachedThreadPool();
        resourceHandlers=new HashSet<>();
    }

    public void startAsync() {
        isEnable = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                doProcSync();//同步监听
            }
        }).start();

    }

    private void doProcSync() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(netConfig.getPort());
        try {

            serverSocket = new ServerSocket();
            serverSocket.bind(inetSocketAddress);//这就是在手机上监听的一个端口
            while (isEnable) {
                final Socket remote = serverSocket.accept();//当有终端连接的时候会返回一个Socket的实例
                //投放到线程池中进行处理
                threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "a remote accept...." + remote.getInetAddress().toString());
                        onAcceptRemote(remote);

                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onAcceptRemote(Socket remote) {
        try {
            HttpContext httpContext = new HttpContext();
            httpContext.setUnderlySocket(remote);
//            remote.getOutputStream().write("connect success".getBytes());
            InputStream ins = remote.getInputStream();
            String headLine = null;
            String resourceUri = StreamToolKit.readLine(ins).split(" ")[1];
            Log.d(TAG, "onAcceptRemote: resourceUri: "+resourceUri);
            while ((headLine = StreamToolKit.readLine(ins)) != null) {
                if (headLine.equals("\r\n")) {
                    break;
                }

                headLine = headLine.substring(0, headLine.length() - 2);//去掉\r\n
                if (headLine.contains(":")) {
                    String[] pair = headLine.split(":");
                    httpContext.addRequestHeader(pair[0], pair[1]);
                }
                Log.d(TAG, "onAcceptRemote: headline: " + headLine);
            }

            for (IResourseUriHandler handler : resourceHandlers) {
                if (!handler.accept(resourceUri)) {
                    continue;
                }
                handler.handle(resourceUri,httpContext);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopAsync() throws IOException {
        if (isEnable) {
            isEnable = false;
            serverSocket.close();
            serverSocket = null;
        }

    }

    public void registerResourceHandler(IResourseUriHandler handler) {
        resourceHandlers.add(handler);
    }

}
