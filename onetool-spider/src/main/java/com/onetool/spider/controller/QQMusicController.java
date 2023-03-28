package com.onetool.spider.controller;

import com.onetool.common.response.ApiResult;
import com.onetool.spider.service.QQMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * @author: zh
 * @date: 2023/3/24 16:17
 * @description: 网页版qq音乐数据抓取
 */

@RequestMapping("/qqMusic")
@RestController
public class QQMusicController {

    @Autowired
    private QQMusicService qqMusicService;

    /**
     * @author: zh
     * @date: 2023/3/27 0:03
     * @param  qq QQ账号
     * @return ApiResult<String>
     * @description: 根据qq账号更新歌单（* 网页版qq音乐个人资料页面必须设置公开）
     */
    @GetMapping("/uPlay/v1/{qq}")
    @ResponseBody
    public ApiResult<String> loadSongPlay(@PathVariable("qq") String qq){
       return qqMusicService.spiderSongPlay(qq);
    }

    @GetMapping("/v1/{qq}")
    @ResponseBody
    public ApiResult<String> song(@PathVariable("qq") String qq) throws IOException {
        return qqMusicService.spiderSong(qq);
    }
}
