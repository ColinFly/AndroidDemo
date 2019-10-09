package com.colin.library.base;

/**
 * Created by colin on 16-5-3.
 */
public abstract class BaseEvent {
    private int code;
    private String msg;

    public BaseEvent(int code) {
        this.code = code;
    }

    public BaseEvent(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
