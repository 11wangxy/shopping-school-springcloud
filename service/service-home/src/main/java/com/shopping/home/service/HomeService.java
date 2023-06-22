package com.shopping.home.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-23 1:24
 * @description: shopping-parent
 */
public interface HomeService {
    Map<String, Object> homeData(Long userId);
}
