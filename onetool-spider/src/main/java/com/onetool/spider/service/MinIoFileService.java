package com.onetool.spider.service;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.Mp3File;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


/**
 * @author: zh
 * @date: 2023/3/29 11:00
 * @description: minio 文件操作
 */

@Service
public class MinIoFileService {

    @Autowired
    private MinioClient minioClient;
    @Value("${ytdlp.musicPath}")
    private String musicPath;



    /**
     * @author: zh
     * @date: 2023/3/30 11:21
     * @param  buckName 存储桶名称
     * @param  objName 文件名称
     * @return  文件预览地址
     * @description:
     */

    public String getFilePreviewUrl(String buckName, String objName) throws Exception{
        //校验存储桶是否存在
        boolean music = minioClient.bucketExists(BucketExistsArgs.builder().bucket(buckName).build());
        if (music){
            try {
                 minioClient.statObject(StatObjectArgs.builder().bucket(buckName).object(objName).build());
            }catch (Exception e){
                return "";
            }
            GetPresignedObjectUrlArgs music1 = GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(buckName)
                    .object(objName)
                    .build();
            return minioClient.getPresignedObjectUrl(music1);
        }
      return "";
    }


    public void upload() throws IOException {
        Stream<Path> walk = Files.walk(Paths.get(musicPath), 1);
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
