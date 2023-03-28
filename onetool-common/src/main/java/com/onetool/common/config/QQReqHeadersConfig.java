package com.onetool.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * @author: zh
 * @date: 2023/3/24 11:43
 * @description: qq音乐接口请求头配置类
 */
@Configuration
@ConfigurationProperties(prefix = "qqmusicheader")
public class QQReqHeadersConfig {

    private String acceptEncoding;
    private String connection;
    private String cookie;
    private String userAgent;
    private String accept;
    private String referer;
    private String origin;


    public HttpHeaders songPlayListHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept-Encoding", acceptEncoding);
        httpHeaders.add("Connection",connection);
        httpHeaders.add("cookie",cookie);
        httpHeaders.add("user-agent",userAgent);
        httpHeaders.add("accept",accept);
        httpHeaders.add("referer",referer);
        httpHeaders.add("origin",origin);
        httpHeaders.add("Content-Type", "text/javascript;charset:utf8");
        return httpHeaders;
    }
    public HttpHeaders songListHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept-Encoding", acceptEncoding);
        httpHeaders.add("Connection",connection);
        httpHeaders.add("cookie",cookie);
        httpHeaders.add("user-agent",userAgent);
        httpHeaders.add("accept",accept);
        httpHeaders.add("referer",referer);
        httpHeaders.add("origin",origin);
        httpHeaders.add("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        return httpHeaders;
    }


    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
