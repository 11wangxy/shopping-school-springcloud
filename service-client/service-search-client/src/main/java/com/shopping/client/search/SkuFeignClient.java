package com.shopping.client.search;

import com.shopping.model.search.SkuEs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-23 12:28
 * @description: shopping-parent
 */
@FeignClient("service-search")
public interface SkuFeignClient {
    @GetMapping("/apiFeign/search/sku/inner/findHotSkuList")
    public List<SkuEs> findHotSkuList();
}
