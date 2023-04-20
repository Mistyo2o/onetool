package com.onetool.wxplat.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: zh
 * @date: 2023/4/14 15:20
 * @description: 歌单
 */
@Entity
@Table(name = "play_list")
@Data
public class Play implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Long id;

    /**
     * 歌单名称
     */
    private String playName;
    /**
     * 歌单封面地址
     */
    private String playCover;
    /**
     * 歌曲数量
     */
    private String songCnt;
    /**
     * 歌单id
     */
    private String tid;
    /**
     * 创建人
     */
    private String cUser;
    /**
     * 是否删除
     */
    private int del;
}
