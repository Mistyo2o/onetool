package com.onetool.wxplat.controller;

import com.onetool.common.response.ApiResult;
import com.onetool.wxplat.entity.Play;
import com.onetool.wxplat.entity.Song;
import com.onetool.wxplat.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: zh
 * @date: 2023/4/3 10:14
 * @description: 小程序 歌单 歌曲相关服务
 */

@RequestMapping("/music")
@RestController
public class MusicController {

    @Autowired
    private MusicService musicService;


    /**
     * @param qq qq号
     * @return ApiResult<List < Play>>
     * @author: zh
     * @date: 2023/4/14 15:46
     * @description: 根据qq获取歌单
     */
    @GetMapping("/getPlay/{qq}")
    @ResponseBody
    public ApiResult playList(@PathVariable("qq") String qq) {
        return musicService.playList(qq);
    }



    /**
     * @author: zh
     * @date: 2023/4/16 17:25
     * @param  playId 歌单id
     * @return ApiResult<List<Play>>
     * @description: 根据歌单id获取歌曲列表
     */
    @GetMapping("/getSong/{playId}")
    @ResponseBody
    public ApiResult songList(@PathVariable("playId") String playId) {
        return musicService.songList(playId);
    }

}
