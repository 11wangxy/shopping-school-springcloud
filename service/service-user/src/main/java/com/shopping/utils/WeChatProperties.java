package com.shopping.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat.open")
public class WeChatProperties {
    String app_id;
    String app_secret;
}
