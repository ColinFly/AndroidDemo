package com.colin.androidserver;

import java.io.IOException;

/**
 * Created by libin on 2016/8/25.
 * 处理uri资源的接口，逻辑是如果接受则处理
 */
public interface IResourseUriHandler {
    boolean accept(String uri);

    void handle(String uri, HttpContext httpContext) throws IOException;
}
