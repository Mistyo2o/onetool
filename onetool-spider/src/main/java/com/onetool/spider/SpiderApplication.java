package com.onetool.spider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.onetool.spider", "com.onetool.common"})
public class SpiderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class);
    }

}
