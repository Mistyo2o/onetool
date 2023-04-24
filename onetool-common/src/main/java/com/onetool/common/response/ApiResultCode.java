package com.onetool.common.response;

/**
 * @author: zh
 * @date: 2023/3/26 23:37
 * @description: 响应数据枚举
 */
public enum ApiResultCode {

    SUCCESS(200, "成功"),
    SAVE_DADA_ISNULL(-1, "保存异常数据为空!"),
    UPDATA_ERROR(-1, "更新数据失败!"),
    UPDATA_PLAYLIST_SUCCESS(2000, "更新歌单完成"),
    UNKOWN_ERROR(500, "未知异常! 请联系管理员");

    public int code;

    public String message;

    ApiResultCode() {
    }

    ApiResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

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
}
