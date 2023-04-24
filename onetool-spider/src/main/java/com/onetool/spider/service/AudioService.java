package com.onetool.spider.service;

import com.mpatric.mp3agic.*;
import com.onetool.spider.common.YtDlpEnum;
import com.onetool.spider.dao.SongRepository;
import com.onetool.spider.entity.Song;
import com.onetool.spider.utils.BashUtils;

import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: zh
 * @date: 2023/3/19 17:14
 * @description: 根据关键词查询出相关视频链接 通过ffmpeg + yt-dlp 解析地址 保存音频数据 并上传minio
 */

@Service
public class AudioService {

    @Value("${ytdlp.oPath}")
    private String ytDlpOutPath;
    @Value("${ytdlp.musicPath}")
    private String musicPath;
    @Autowired
    private YouTubeDataApiService youTubeDataApiService;
    @Autowired
    private MinIoFileService minIoFileService;
    @Autowired
    private SongRepository songRepository;


    /**
     * @param songList 搜索内容
     * @return void
     * @author: zh
     * @date: 2023/3/19 17:17
     * @description: 根据关键词搜索youTube视频信息
     * 根据相关信息利用第三方网站 爬取视频或者音频数据 并保存
     */
    public void youTubeVideo(List<Song> songList) throws Exception {
        //根据关键词搜索出的视频id集合 并拼接为视频播放地址返回
//        List<Song> search = youTubeDataApiService.search(songList);
//        //使用ffmpeg + yt-dlp 解析地址 并下载音频文件
//        String value = YtDlpEnum.AUDIO_FORMAT_MP3.getValue();
//        //清空文件夹
//        delFile();
//        for (Song song : search) {
//            if (StringUtils.hasText(song.getPreviewUrl())){
//                continue;
//            }
//            //保存路径 /歌名+作者
//            String oPath = ytDlpOutPath + song.getName() + song.getAuthor();
//            String videoUrl = MessageFormat.format(value, song.getYoutubeVideoUrl(), oPath);
//            //执行yt-dlp命令提取音频数据
//            BashUtils.execCommand(videoUrl, "");
//        }
        //下载完成之后 上传文件至minio
        //minIoFileService.upload();
        //获取文件预览地址 更新数据
        for (Song song : songList) {
            String previewUrl = minIoFileService.getFilePreviewUrl("music", song.getName() + song.getAuthor() + ".mp3");
            song.setPreviewUrl(previewUrl);
            songRepository.save(song);
        }
    }


    private void delFile() throws IOException {
        Stream<Path> walk = Files.walk(Paths.get(musicPath), 1);
        //获取目录下所有.mp3文件
        walk.map(Path::toString)
                .filter(f -> f.endsWith(".mp3"))
                .forEach(path -> {
                    try {
                        //创建文件对象 获取文件名称
                        File file = new File(path);
                        boolean delete = file.delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
