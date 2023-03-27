package com.onetool.spider.common;

/**
 * @author: zh
 * @date: 2023/3/19 17:00
 * @description:
 */
public enum YouTubeDataApiEnum {

    SEARCH("search?");

    private String value;

    YouTubeDataApiEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
