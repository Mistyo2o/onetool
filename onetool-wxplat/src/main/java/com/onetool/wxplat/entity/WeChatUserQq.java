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
    private String id;

    /**
     * 微信用户openid
     */
    private String openId;
    /**
     * qq号
     */
    private String qq;
    /**
     * 是否选中当前账号 0否1是
     */
    private int option;
}
