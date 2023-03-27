package com.onetool.common.response;

import java.io.Serializable;

/**
 * @author: zh
 * @date: 2023/3/26 23:35
 * @description:
 */
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public int code;

    private String message;

    private T data;

    public void ApiResult() {
        this.code = ApiResultCode.SUCCESS.code;
        this.message = ApiResultCode.SUCCESS.message;
    }

    public ApiResult<T> setCode(ApiResultCode apiResultCode) {
        this.code = apiResultCode.code;
        return this;
    }


    public int getCode() {
        return code;
    }

    public ApiResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return message;
    }

    public ApiResult<T> setMsg(String msg) {
        this.message = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiResult<T> setData(T data) {
        this.data = data;
        return this;
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
