package com.onetool.wxplat.controller;

import com.onetool.common.response.ApiResult;
import com.onetool.wxplat.entity.WeChatUserInfo;
import com.onetool.wxplat.service.WeChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zh
 * @version 1.0
 * @date 2023/4/22 20:59
 * @description: weChat登录用户信息
 */
@RequestMapping("/weChatUser")
@RestController
public class WeChatUserController {

    @Autowired
    private WeChatUserService weChatUserService;

    @PostMapping("/save")
    @ResponseBody
    public ApiResult save(@RequestBody WeChatUserInfo weChatUserInfo) {
        return weChatUserService.saveWeChatUserInfo(weChatUserInfo);
    }


    @GetMapping("/getInfo/{openId}")
    @ResponseBody
    public ApiResult getInfo(@PathVariable("openId") String openId) {
        return weChatUserService.getWeChatUserInfoByOpenId(openId);
    }
}
