package com.company.project.core;

import com.alibaba.fastjson.JSON;

/**
 * 缁熶竴API鍝嶅簲缁撴灉灏佽
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
