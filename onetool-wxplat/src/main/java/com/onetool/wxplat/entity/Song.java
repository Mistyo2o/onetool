package com.onetool.wxplat.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: zh
 * @date: 2023/4/16 17:20
 * @description: 歌曲
 */
@Entity
@Table(name = "song")
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private String id;

    /**
     * 歌曲名称
     */
    private String name;
    /**
     * 歌曲id
     */
    private String songId;
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
    /**
     * minio预览地址
     */
    private String previewUrl;
    /**
     * 创建时间
     */
    private String cTime;
    /**
     * 是否删除
     */
    private int del;
}
