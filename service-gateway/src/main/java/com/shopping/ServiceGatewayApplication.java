package com.shopping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-22 2:37
 * @description: shopping-parent
 */

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class, args);
    }
}
