package com.onetool.spider.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;



/**
 * @author: zh
 * @date: 2023/3/19 16:24
 * @description: 组成youtubeApiUrl公共类
 */
@Component
public class YouTubeDataApiCommon {

    @Value("${youtube.apiKey}")
    private String youTubeApiKey;

    @Value("${youtube.apiUrl}")
    private String youTubeApiUrl;


    /**
     * @author: zh
     * @date: 2023/3/19 16:25
     * @return String
     * @description: 根据传入的请求参数组成url
     */
    public String getUrl(YouTubeDataApiEnum dataApiEnum, Map<String, Object> apiParams){
        StringBuilder url = new StringBuilder(youTubeApiUrl + dataApiEnum.getValue());
        for (String key : apiParams.keySet()) {
            //拼接参数
            String value = apiParams.get(key).toString();
            url.append(key).append("=").append(value).append("&");
        }
        return url + youTubeApiKey;
    }
}
