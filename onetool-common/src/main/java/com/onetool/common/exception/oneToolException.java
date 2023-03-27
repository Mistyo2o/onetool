package com.onetool.common.exception;

import com.onetool.common.response.ApiResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: zh
 * @date: 2023/3/26 17:35
 * @description: 系统自定义异常类型
 */
public class oneToolException extends RuntimeException{


    public oneToolException() {
        super();
    }

    public oneToolException(String message) {
        super(message);
    }

    public static void errMsg(String message){
        throw new oneToolException(message);
    }

    public static void errMsg(ApiResultCode apiResultCode){
        throw new oneToolException(apiResultCode.getMessage());
    }


}
