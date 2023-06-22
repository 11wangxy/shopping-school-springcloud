package com.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-22 16:29
 * @description: shopping-parent
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.shopping.mapper")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class,args);
    }
}
