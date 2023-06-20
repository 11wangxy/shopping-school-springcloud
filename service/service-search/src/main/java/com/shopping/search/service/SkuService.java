package com.shopping.search.service;

import com.shopping.model.search.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-19 1:12
 * @description: shopping-parent
 */
public interface SkuService{
    void upperSku(Long skuId);

    void lower(Long skuId);

}
