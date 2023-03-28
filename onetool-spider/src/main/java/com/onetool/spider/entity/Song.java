package com.onetool.spider.entity;

/**
 * @author: zh
 * @date: 2023/3/24 9:21
 * @description:
 */
public class Song {

    /**
     * 歌曲名称
     */
    private String name;
    /**
     * 歌曲副标题
     */
    private String subTitle;
    /**
     * 歌曲作者
     */
    private String author;
    /**
     * 专辑
     */
    private String album;
    /**
     * 歌单id
     */
    private String playId;
    /**
     * 账号
     */
    private String qq;
    /**
     * youtube视频地址
     */
    private String youtubeVideoUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getYoutubeVideoUrl() {
        return youtubeVideoUrl;
    }

    public void setYoutubeVideoUrl(String youtubeVideoUrl) {
        this.youtubeVideoUrl = youtubeVideoUrl;
    }
}
