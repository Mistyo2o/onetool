package com.onetool.spider.entity;

/**
 * @author: zh
 * @date: 2023/3/24 9:21
 * @description:
 */
public class Song {

    private String name;

    private String author;

    private String youtubeVideoUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYoutubeVideoUrl() {
        return youtubeVideoUrl;
    }

    public void setYoutubeVideoUrl(String youtubeVideoUrl) {
        this.youtubeVideoUrl = youtubeVideoUrl;
    }
}
