package com.onetool.common.response;

/**
 * @author: zh
 * @date: 2023/3/26 23:48
 * @description: 响应数据类型
 */
public class ResponseData {

    /****
     * 成功响应Msg中的信息
     **/
    private final static String SUCCESS = "success";


    public static  ApiResult success(Object data) {
        return new ApiResult(ApiResultCode.SUCCESS.getCode(), ApiResultCode.SUCCESS.getMessage(), data);
    }


    public static ApiResult success() {
        return new ApiResult(ApiResultCode.SUCCESS.getCode(), ApiResultCode.SUCCESS.getMessage(), null);
    }

    public static ApiResult error(String msg) {
        return new ApiResult(ApiResultCode.UPDATA_ERROR.getCode(), msg, null);
    }

//    public static ApiResult success() {
//        return new ApiResult().setCode(ApiResultCode.SUCCESS).setMsg(SUCCESS);
//    }



    
}
