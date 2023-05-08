package com.onetool.wxplat.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zh
 * @version 1.0
 * @date 2023/4/24 21:42
 */

@Entity
@Table(name = "wx_user_qq")
@Data
public class WeChatUserQq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Long id;

    /**
     * 微信用户openid
     */
    @Column(name = "open_id")
    private String openId;
    /**
     * 是否选中当前账号 0否1是
     */
    @Column(name = "def")
    private int def;
    /**
     * qq号
     */
    @Column(name = "qq")
    private String qq;


    @Column(name = "name")
    private String name;
}
