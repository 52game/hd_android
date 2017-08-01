package com.movebeans.lib.base;

/**
 * 基础响应数据结构模型
 * <p>
 * Created by zhangfei on 2017/3/22.
 */

public class JsonEntity<T> {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
