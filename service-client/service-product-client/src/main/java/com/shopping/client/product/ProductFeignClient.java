package com.shopping.client.product;

import com.shopping.model.product.Category;
import com.shopping.model.product.SkuInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-19 1:31
 * @description: shopping-parent
 */
@FeignClient(value = "service-product")
public interface ProductFeignClient {

    @GetMapping("/api/product/inner/getCategory/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId);

    @GetMapping("/api/product/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable("skuId") Long skuId);

    @PostMapping("/api/product/inner/findSkuInfoList")
    public List<SkuInfo> findSkuInfoList(@RequestBody List<Long> skuIdList);

    @GetMapping("/api/product/inner/findByKeyword/{keyword}")
    public List<SkuInfo> findByKeyword(@PathVariable String keyword);

    @PostMapping("/api/product/inner/findCategoryList")
    public List<Category> findCategoryList(@RequestBody List<Long> categoryList);

    @GetMapping("/api/product/inner/findAllCategoryList")
    public List<Category> findAllCategoryList();

    @GetMapping("/api/product/inner/findIsNewPersonSkuList")
    public List<SkuInfo> findIsNewPersonSkuList();
}
