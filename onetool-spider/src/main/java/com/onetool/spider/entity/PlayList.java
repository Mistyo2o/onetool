package com.onetool.spider.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: zh
 * @date: 2023/3/26 18:18
 * @description:
 */
@Entity
@Table(name = "play_list")
@Data
public class PlayList implements Serializable {

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
     * 创建人(qq号)
     */
    private String cUser;
    /**
     * 创建时间
     */
    private String cTime;
    /**
     * 更新人(qq号)
     */
    private String uUser;
    /**
     * 更新时间
     */
    private String uTime;
    /**
     * 是否作废(0否1是)
     */
    private int del;

}
