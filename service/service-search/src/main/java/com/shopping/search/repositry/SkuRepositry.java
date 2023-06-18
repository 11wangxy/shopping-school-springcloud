package com.shopping.search.repositry;

import com.shopping.model.search.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-19 1:15
 * @description: shopping-parent
 */
public interface SkuRepositry extends ElasticsearchRepository<SkuEs,Long> {
}
