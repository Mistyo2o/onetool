package com.onetool.common.response;

import java.io.Serializable;

/**
 * @author: zh
 * @date: 2023/3/26 23:35
 * @description: 响应数据格式
 */
public class ApiResult implements Serializable {

    private static final long serialVersionUID = 1L;

    public int code;

    public String message;

    public Object data;

    public ApiResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
