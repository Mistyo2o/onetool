package com.onetool.spider.service;

import com.onetool.spider.common.YtDlpEnum;
import com.onetool.spider.entity.Song;
import com.onetool.spider.utils.BashUtils;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    @Autowired
    private YouTubeDataApiService youTubeDataApiService;
    @Autowired
    private MinioClient minioClient;

    /**
     * @param songList 搜索内容
     * @return void
     * @author: zh
     * @date: 2023/3/19 17:17
     * @description: 根据关键词搜索youTube视频信息
     * 根据相关信息利用第三方网站 爬取视频或者音频数据 并保存
     */
    public void youTubeVideo(List<Song> songList) throws IOException, InterruptedException {
        //根据关键词搜索出的视频id集合 并拼接为视频播放地址返回
        List<Song> search = youTubeDataApiService.search(songList);
        //使用ffmpeg + yt-dlp 解析地址 并下载音频文件
        String value = YtDlpEnum.AUDIO_FORMAT_MP3.getValue();
        for (Song song : search) {
            //保存路径
            String oPath = ytDlpOutPath + song.getName();
            String videoUrl = MessageFormat.format(value, song.getYoutubeVideoUrl(), oPath);
            //执行yt-dlp命令提取音频数据
            BashUtils.execCommand(videoUrl, "");
        }
        Stream<Path> walk = Files.walk(Paths.get(ytDlpOutPath));
        //获取目录下所有.mp3文件
        walk.map(Path::toString)
                .filter(f -> f.endsWith(".mp3"))
                .forEach(path -> {
                    try {
                        //创建文件对象 获取文件名称
                        File file = new File(path);
                        //上传minio
                        minioClient.putObject(PutObjectArgs.builder().bucket("music").object(file.getName())
                                .stream(Files.newInputStream(Paths.get(path)), Files.size(Paths.get(path)), -1).build());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
