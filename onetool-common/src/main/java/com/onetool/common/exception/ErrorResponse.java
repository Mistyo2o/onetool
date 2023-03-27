package com.onetool.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zh
 * @date: 2023/3/26 22:23
 * @description: 异常信息
 */
@Data
@AllArgsConstructor
public class ErrorResponse implements Serializable {

    private String errMessage;

}
