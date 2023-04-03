package com.onetool.spider.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.onetool.common.config.RestTemplateConfig;
import com.onetool.spider.common.YouTubeDataApiCommon;
import com.onetool.spider.common.YouTubeDataApiEnum;
import com.onetool.spider.dao.SongRepository;
import com.onetool.spider.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zh
 * @date: 2023/3/19 16:54
 * @description: 获取YouTubeDataApi相关数据
 */

@Service
public class YouTubeDataApiService {

    @Autowired
    private YouTubeDataApiCommon youTubeDataApiCommon;
    @Autowired
    private RestTemplateConfig restTemplateConfig;
    @Value("${youtube.videoUrl}")
    private String videoUrl;
    @Autowired
    private SongRepository songRepository;

    /**
     * @param songList 搜索内容
     * @return List<String>
     * @author: zh
     * @date: 2023/3/19 16:58
     * @description: 批量搜索返回视频id
     */
    public List<Song> search(List<Song> songList) {
        RestTemplate restTemplate = restTemplateConfig.restTemplateProxy();
        Map<String, Object> apiParamsMap = new HashMap<>();
        apiParamsMap.put("part", "snippet");
        for (Song song : songList) {
            if (StringUtils.hasText(song.getYoutubeVideoUrl())){
                continue;
            }
            String name = song.getName();
            String author = song.getAuthor();
            apiParamsMap.put("q", name + author);
            String url = youTubeDataApiCommon.getUrl(YouTubeDataApiEnum.SEARCH, apiParamsMap);
            //发送请求
            ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
            //解析响应
            JSONObject jsonObject = JSONObject.parseObject(forEntity.getBody());
            JSONArray itemsArr = JSONArray.parseArray(JSONObject.toJSONString(jsonObject.get("items")));
            //默认使用第一条
            JSONObject item = JSONObject.parseObject(JSONObject.toJSONString(itemsArr.get(0)));
            JSONObject idObj = JSONObject.parseObject(JSONObject.toJSONString(item.get("id")));
            String videoUrl = this.videoUrl + idObj.get("videoId").toString();
            song.setYoutubeVideoUrl(videoUrl);
            songRepository.save(song);
        }
        return songList;
    }
}
