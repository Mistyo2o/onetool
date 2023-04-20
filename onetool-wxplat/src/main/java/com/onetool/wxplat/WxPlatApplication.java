package com.onetool.wxplat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: zh
 * @date: 2023/4/3 10:13
 * @description: wx小程序相关应用
 */

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.onetool.wxplat", "com.onetool.common"})
public class WxPlatApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxPlatApplication.class);
    }
}
