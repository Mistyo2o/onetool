package com.onetool.wxplat.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zh
 * @version 1.0
 * @date 2023/4/22 21:02
 */
@Entity
@Table(name = "wx_user_info")
@Data
public class WeChatUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private String id;
    /**
     * weChat登录openId
     */
    private String openId;
    /**
     * weChat登录sessionKey
     */
    private String sessionKey;
    /**
     * 微信名称
     */
    private String nickName;
    /**
     * 微信头像
     */
    private String avatarUrl;

}
