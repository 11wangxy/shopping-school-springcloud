package com.shopping;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 16:27
 * @description: Default (Template) Project
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceSysApplication{
    public static void main(String[] args) {
        SpringApplication.run(ServiceSysApplication.class,args);
    }
}