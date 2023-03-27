package com.onetool.common.handler;

import com.onetool.common.exception.ErrorResponse;
import com.onetool.common.exception.oneToolException;
import com.onetool.common.response.ApiResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author: zh
 * @date: 2023/3/26 17:31
 * @description:
 */
@ControllerAdvice
public class BaseExceptionHandler {


    /**
     * @author: zh
     * @date: 2023/3/26 22:30
     * @description: 项目自定义异常处理
     */
    @ResponseBody
    @ExceptionHandler(oneToolException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handler(oneToolException e) {

        //记录异常日志

        //返回统一异常类
       return new ErrorResponse(e.getMessage());
    }


    /**
     * @author: zh
     * @date: 2023/3/26 22:30
     * @description: 项目异常处理
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handler(Exception e) {

        //记录异常日志

        //返回统一异常类
        return new ErrorResponse(ApiResultCode.UNKOWN_ERROR.message);
    }
}
