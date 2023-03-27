package com.onetool.common.response;

/**
 * @author: zh
 * @date: 2023/3/26 23:48
 * @description:
 */
public class ResponseData {

    /****
     * 成功响应Msg中的信息
     **/
    private final static String SUCCESS = "success";


    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<T>().setCode(ApiResultCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult<T>().setCode(ApiResultCode.SUCCESS).setMsg(SUCCESS).setData(null);
    }

    public static <T> ApiResult<T> success(int code, String message) {
        return new ApiResult<T>().setCode(code).setMsg(message).setData(null);
    }

//    public static ApiResult success() {
//        return new ApiResult().setCode(ApiResultCode.SUCCESS).setMsg(SUCCESS);
//    }

    public static <T> ApiResult<T> error(ApiResultCode ApiResultsCode) {
        return new ApiResult<T>().setCode(ApiResultsCode.code).setMsg(ApiResultsCode.message);
    }


    public static <T> ApiResult<T> error(String mesage, ApiResultCode ApiResultsCode) {
        return new ApiResult<T>().setMsg(mesage).setCode(ApiResultsCode.code);
    }


    
}
