package com.onetool.wxplat.service;


import com.onetool.common.response.ApiResult;
import com.onetool.common.response.ApiResultCode;
import com.onetool.common.response.ResponseData;
import com.onetool.wxplat.dao.WeChatUserRepository;
import com.onetool.wxplat.entity.WeChatUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author zh
 * @version 1.0
 * @date 2023/4/22 21:01
 */

@Service
public class WeChatUserService {

    @Autowired
    private WeChatUserRepository weChatUserRepository;

    /**
     * @param weChatUserInfo 微信小程序用户登录信息
     * @author: zh
     * @description: 根据qq查询歌单列表
     */
    public ApiResult saveWeChatUserInfo(WeChatUserInfo weChatUserInfo) {
        WeChatUserInfo save = weChatUserRepository.save(weChatUserInfo);
        //保存失败
        //if (StringUtils.hasText(save.getId())) {}
        return ResponseData.success();
    }

    /**
     * @param openId 微信登录的openId
     * @author: zh
     * @description: 根据qq查询歌单列表
     */
    public ApiResult getWeChatUserInfoByOpenId(String openId){
        WeChatUserInfo byOpenId = weChatUserRepository.findByOpenId(openId);
        return ResponseData.success(byOpenId);
    }
}
