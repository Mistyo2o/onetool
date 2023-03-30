package com.onetool.spider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.onetool.common.config.QQReqHeadersConfig;
import com.onetool.common.config.RestTemplateConfig;
import com.onetool.common.exception.oneToolException;
import com.onetool.common.response.ApiResult;
import com.onetool.common.response.ApiResultCode;
import com.onetool.common.response.ResponseData;
import com.onetool.spider.dao.PlayListRepository;
import com.onetool.spider.dao.SongRepository;
import com.onetool.spider.entity.DissList;
import com.onetool.spider.entity.PlayList;
import com.onetool.spider.entity.Song;
import com.onetool.spider.entity.SongListParams;
import com.onetool.spider.utils.QqEncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zh
 * @date: 2023/3/24 10:38
 * @description: 爬取用户qq音乐网页相关信息
 */

@Service
public class QQMusicService {

    @Autowired
    private PlayListRepository playListRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private MinIoFileService minIoFileService;
    @Autowired
    private AudioService audioService;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private QQReqHeadersConfig qqReqHeadersConfig;
    @Autowired
    private RestTemplateConfig restTemplateConfig;
    @Value("${songplayurl}")
    private String songPlayUrl;
    @Value("${songlist}")
    private String songList;
    private static final String BUCK_NAME = "music";


    /**
     * @param qq qq账号
     * @return ApiResult<String>
     * @author: zh
     * @date: 2023/3/27 10:16
     * @description: 获取用户歌单列表 并保存
     */
    @Transactional
    public ApiResult<String> spiderSongPlay(String qq) {
        //请求参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        long r = Calendar.getInstance().getTimeInMillis();
        params.add("r", String.valueOf(r));
        params.add("format", "json");
        params.add("inCharset", "utf-8");
        params.add("outCharset", "utf-8");
        params.add("platform", "yqq.json");
        //qq账号
        params.add("uin", "2548635937");
        //需要查询的 qq账号歌单
        params.add("hostuin", qq);
        params.add("size", "50");
        //请求头
        HttpHeaders headers = qqReqHeadersConfig.songPlayListHeaders();
        RestTemplate restTemplate = restTemplateConfig.restTemplate();
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
        //拼接GET请求地址
        URI uri = UriComponentsBuilder.fromHttpUrl(songPlayUrl).queryParams(params).build().encode().toUri();
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        String resJson = exchange.getBody();
        //解析响应json
        JSONObject rootJson = JSONObject.parseObject(resJson);
        JSONObject data = JSONObject.parseObject(JSONObject.toJSONString(rootJson.get("data")));
        if (data == null) {
            //直接抛出异常
            String message = JSONObject.toJSONString(rootJson.get("message"));
            oneToolException.errMsg(message);
        }
        List<DissList> disslist = JSONObject.parseArray(JSONObject.toJSONString(data.get("disslist")), DissList.class);
        //保存之前 根据qq账号查询歌单记录并作废 然后保存新数据
        List<PlayList> byCUserAndDel = playListRepository.findBycUserAndDel(qq, 0);
        if (byCUserAndDel.size() > 0) {
            //作废
            playListRepository.upDateDelBycUser(qq);
        }
        //保存新数据
        batchInset(getBatchInsetPlaySql(disslist, qq));
        return ResponseData.success(ApiResultCode.UPDATA_PLAYLIST_SUCCESS.code, ApiResultCode.UPDATA_PLAYLIST_SUCCESS.message);
    }

    /**
     * @return ApiResult<String>
     * @author: zh
     * @date: 2023/3/27 10:17
     * @description: 获取歌单中歌曲数据 并保存
     */
    @Transactional
    public ApiResult<String> spiderSong(String qq) throws Exception {
        //根据qq 查询歌单
        List<PlayList> byCUserAndDel = playListRepository.findBycUserAndDel(qq, 0);
        long l = System.currentTimeMillis();
        //url参数
        MultiValueMap<String, String> reqParams = new LinkedMultiValueMap<>();
        reqParams.add("_", String.valueOf(l));
        HttpHeaders headers = qqReqHeadersConfig.songListHeaders();
        RestTemplate restTemplate = restTemplateConfig.restTemplateAddMediaType();
        List<Song> list = new ArrayList<>();
        for (PlayList playList : byCUserAndDel) {
            //除去背景音乐歌单 TODO 需要请求单独接口
            if ("0".equals(playList.getTid())) {
                continue;
            }
            //生成请求参数
            SongListParams signParams = SongListParams.init(playList.getTid(), 99);
            //生成加密sign
            String jsonBody = JSON.toJSONString(signParams);
            String sign = QqEncryptUtils.getSign(jsonBody);
            reqParams.remove("sign");
            reqParams.add("sign", sign);
            //拼接请求参数
            HttpEntity<Object> httpEntity = new HttpEntity<>(jsonBody, headers);
            URI uri = UriComponentsBuilder.fromHttpUrl(songList).queryParams(reqParams).build().encode().toUri();
            //响应encoding类型是gzip 使用byte接收进行转码
            ResponseEntity<byte[]> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, byte[].class);
            byte[] bytes = restTemplateConfig.unGzip(new ByteArrayInputStream(Objects.requireNonNull(exchange.getBody())));
            String resJsonStr = new String(bytes, StandardCharsets.UTF_8);
            //解析响应数据
            JSONObject rootJson = JSONObject.parseObject(resJsonStr);
            JSONObject req_1 = JSONObject.parseObject(JSONObject.toJSONString(rootJson.get("req_1")));
            JSONObject data = JSONObject.parseObject(JSONObject.toJSONString(req_1.get("data")));
            JSONArray songList = JSONObject.parseArray(JSONObject.toJSONString(data.get("songlist")));

            for (Object item : songList) {
                Song song = new Song();
                song.setQq(qq);
                song.setPlayId(playList.getTid());
                JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(item));
                song.setName(JSON.toJSONString(obj.get("name")));
                song.setSongId(JSON.toJSONString(obj.get("id")));
                if (obj.get("subtitle") != null) {
                    song.setSubTitle(JSON.toJSONString(obj.get("subtitle")));
                }
                //获取作者
                JSONArray singer = JSONObject.parseArray(JSONObject.toJSONString(obj.get("singer")));
                JSONObject singerItem = JSONObject.parseObject(JSONObject.toJSONString(singer.get(0)));
                song.setAuthor(JSON.toJSONString(singerItem.get("name")));
                //获取专辑名称
                JSONObject album = JSONObject.parseObject(JSONObject.toJSONString(obj.get("album")));
                song.setAlbum(JSONObject.toJSONString(album.get("name")));
                String previewUrl = minIoFileService.getFilePreviewUrl(BUCK_NAME, song.getName() + song.getAuthor());
                song.setPreviewUrl(previewUrl);
                list.add(song);
            }
        }
        //TODO 查询文件列表 比较是否有歌名相同的文件数据 相同不新增
        //先删除 后保存歌曲信息
        List<Song> byQqAndDel = songRepository.findByQqAndDel(qq, 0);
        if (byQqAndDel.size() > 0) {
            songRepository.upDateDelByQq(qq);
        }
        batchInset(getBatchInsetSong(list));
        //下载无预览地址的歌曲
        List<Song> playSong = list.stream().filter(item -> !StringUtils.hasText(item.getPreviewUrl())).collect(Collectors.toList());
//        List<Song> playSong = songRepository.findByPlayId("8121073639");
        audioService.youTubeVideo(playSong);
        return ResponseData.success();
    }

    private String getBatchInsetSong(List<Song> list) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append("INSERT INTO song(name,song_id, sub_title, author, album, play_id, preview_url, qq, c_time, del) VALUES ");
        String regexStr = "([\"'])";
        for (int i = 0; i < list.size(); i++) {
            Song song = list.get(i);
            String nameRegex = song.getName().replaceAll(regexStr, "");
            String subTitleRegex = song.getSubTitle().replaceAll(regexStr, "");
            String albumRegex = song.getAlbum().replaceAll(regexStr, "");
            String authorRegex = song.getAuthor().replaceAll(regexStr, "");
            sb.append("(").append("'").append(nameRegex).append("'").append(",")
                    .append("'").append(song.getSongId()).append("'").append(",")
                    .append("'").append(subTitleRegex).append("'").append(",")
                    .append("'").append(authorRegex).append("'").append(",")
                    .append("'").append(albumRegex).append("'").append(",")
                    .append("'").append(song.getPlayId()).append("'").append(",")
                    .append("'").append(song.getPreviewUrl()).append("'").append(",")
                    .append("'").append(song.getQq()).append("'").append(",")
                    .append("'").append(dateFormat.format(new Date())).append("'").append(",")
                    .append("'").append(0).append("'").append(")");
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private String getBatchInsetPlaySql(List<DissList> list, String qq) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO play_list(play_name, play_cover, song_cnt, tid, c_user, c_time, del) VALUES ");
        for (int i = 0; i < list.size(); i++) {
            sb.append("(").append("'").append(list.get(i).getDiss_name()).append("'")
                    .append(",").append("'").append(list.get(i).getDiss_cover()).append("'")
                    .append(",").append("'").append(list.get(i).getSong_cnt()).append("'")
                    .append(",").append("'").append(list.get(i).getTid()).append("'")
                    .append(",").append("'").append(qq).append("'")
                    .append(",").append("'").append(dateFormat.format(new Date())).append("'")
                    .append(",").append("'").append(0).append("'").append(")");
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }


    private void batchInset(String sql) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        //执行语句
        nativeQuery.executeUpdate();
    }

}
