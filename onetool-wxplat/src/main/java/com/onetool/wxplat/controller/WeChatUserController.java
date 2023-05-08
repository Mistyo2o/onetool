package com.onetool.wxplat.controller;

import com.onetool.common.response.ApiResult;
import com.onetool.wxplat.entity.WeChatUserInfo;
import com.onetool.wxplat.entity.WeChatUserQq;
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


    @GetMapping("/getOpenId/{code}")
    @ResponseBody
    public ApiResult getOpenId(@PathVariable("code") String code) {
        return weChatUserService.getOpenIdByLoginCode(code);
    }

    @GetMapping("/getInfo/{openId}")
    @ResponseBody
    public ApiResult getInfo(@PathVariable("openId") String openId) {
        return weChatUserService.getUserInfoByOpenId(openId);
    }

    @GetMapping("/getQq/{openId}")
    @ResponseBody
    public ApiResult getQq(@PathVariable("openId") String openId) {
        return weChatUserService.getWeChatUserQqByOpenId(openId);
    }


    @PostMapping("saveUserQq")
    @ResponseBody
    public ApiResult saveUserQq(@RequestBody WeChatUserQq weChatUserQq){
        return weChatUserService.saveWeChatUserQq(weChatUserQq);
    }

    @GetMapping("/uDef/{qq}")
    @ResponseBody
    public ApiResult updateQqDef(@PathVariable("qq") String qq){
        return weChatUserService.updateQqDet(qq);
    }
}
