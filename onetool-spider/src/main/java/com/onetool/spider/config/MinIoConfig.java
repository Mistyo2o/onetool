package com.onetool.spider.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zh
 * @date: 2023/3/23 16:53
 * @description: minIo 文件相关操作
 */
@Configuration
public class MinIoConfig {

    @Value("${minio.url}")
    private String url;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;


    @Bean
    public MinioClient minioClient(){
       return MinioClient.builder().endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }


}
