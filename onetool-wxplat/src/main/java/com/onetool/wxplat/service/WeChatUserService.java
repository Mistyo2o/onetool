package com.onetool.wxplat.service;


import com.alibaba.fastjson.JSONObject;
import com.onetool.common.config.RestTemplateConfig;
import com.onetool.common.response.ApiResult;
import com.onetool.common.response.ResponseData;
import com.onetool.wxplat.dao.WeChatUserQqRepository;
import com.onetool.wxplat.dao.WeChatUserRepository;
import com.onetool.wxplat.entity.WeChatUserInfo;
import com.onetool.wxplat.entity.WeChatUserQq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zh
 * @version 1.0
 * @date 2023/4/22 21:01
 */

@Service
public class WeChatUserService {

    @Value("${weixin.getOpenIdUrl}")
    private String getOpenIdUrl;
    @Value("${weixin.appid}")
    private String appid;
    @Value("${weixin.secret}")
    private String secret;
    @Autowired
    private WeChatUserRepository weChatUserRepository;
    @Autowired
    private WeChatUserQqRepository weChatUserQqRepository;
    @Autowired
    private RestTemplateConfig restTemplateConfig;

    /**
     * @param weChatUserInfo 微信小程序用户登录信息
     * @author: zh
     * @description:
     */
    public ApiResult saveWeChatUserInfo(WeChatUserInfo weChatUserInfo) {
        WeChatUserInfo save = weChatUserRepository.save(weChatUserInfo);
        //保存失败
        //if (StringUtils.hasText(save.getId())) {}
        return ResponseData.success();
    }

    /**
     * @param loginCode 微信login函数响应的code
     * @author: zh
     * @description:
     */
    public ApiResult getWeChatUserInfoByCode(String loginCode){
        //根据微信小程序login函数返回的code值 获取openId
        MultiValueMap<String, String> reqParams = new LinkedMultiValueMap<>();
        reqParams.add("appid", appid);
        reqParams.add("secret", secret);
        reqParams.add("js_code", loginCode);
        reqParams.add("grant_type", "authorization_code");
        URI uri = UriComponentsBuilder.fromHttpUrl(getOpenIdUrl).queryParams(reqParams).build().encode().toUri();
        RestTemplate restTemplate = restTemplateConfig.restTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
        String body = forEntity.getBody();
        //{"errmsg": "invalid appid, rid: 644faa59-53fa8e14-4797c383"}
        //{"session_key":"vq7SAd+WANCssF7EscbBwQ==","openid":"oGqGX5colag-QQVWSDRW32bMfszA"}
        JSONObject resObj = JSONObject.parseObject(body);
        if (Objects.isNull(resObj.get("session_key")) || Objects.isNull(resObj.get("openid")) ){
            return ResponseData.error(body);
        }
        System.out.println(body);
        String sessionKey = resObj.get("session_key").toString();
        String openid = resObj.get("openid").toString();
        WeChatUserInfo byOpenId = weChatUserRepository.findByOpenId(openid);
        if(Objects.isNull(byOpenId)){
            //没有用户的openid信息

        }
        return ResponseData.success(byOpenId);
    }

    public ApiResult getWeChatUserQqByOpenId(String openId){
        List<WeChatUserQq> byOpenId = weChatUserQqRepository.findByOpenId(openId);
        return ResponseData.success(byOpenId);
    }

    public ApiResult saveWeChatUserQq(WeChatUserQq weChatUserQq) {
        WeChatUserQq save = weChatUserQqRepository.save(weChatUserQq);
        return ResponseData.success();
    }

    public ApiResult updateQqDet(String qq){
        WeChatUserQq byQq = weChatUserQqRepository.findByQq(qq);
        if(Objects.isNull(byQq)){
            return ResponseData.error("为查询到账号信息");
        }
        //全部重置为0
        weChatUserQqRepository.updateDefByOpenId(byQq.getOpenId());
        //根据id重置为1
        weChatUserQqRepository.updateDefById(byQq.getId());
        return ResponseData.success();
    }
}
