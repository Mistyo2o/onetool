package com.onetool.wxplat.service;

import com.onetool.common.response.ApiResult;
import com.onetool.common.response.ResponseData;
import com.onetool.wxplat.dao.PlayRepository;
import com.onetool.wxplat.dao.SongRepository;
import com.onetool.wxplat.entity.Play;
import com.onetool.wxplat.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zh
 * @date: 2023/4/14 9:40
 * @description:
 */
@Service
public class MusicService {

    @Autowired
    private PlayRepository playRepository;

    @Autowired
    private SongRepository songRepository;


    /**
     * @param qq qq号
     * @return ApiResult<List < Play>>
     * @author: zh
     * @date: 2023/4/14 15:33
     * @description: 根据qq查询歌单列表
     */
    public ApiResult<List<Play>> playList(String qq) {
        List<Play> byCUserAndDel = playRepository.findBycUserAndDel(qq, 0);
        return ResponseData.success(byCUserAndDel);
    }

    /**
     * @param playId 歌单id
     * @return ApiResult<List < Song>>
     * @author: zh
     * @date: 2023/4/16 17:21
     * @description: 根据歌单id 查询歌曲列表
     */
    public ApiResult<List<Song>> songList(String playId) {
        List<Song> byPlayId = songRepository.findByPlayId(playId);
        return ResponseData.success(byPlayId);
    }

}
