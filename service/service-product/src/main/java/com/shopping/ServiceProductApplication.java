package com.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 20:43
 * @description: shopping-parent
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProductApplication.class);
    }
}
