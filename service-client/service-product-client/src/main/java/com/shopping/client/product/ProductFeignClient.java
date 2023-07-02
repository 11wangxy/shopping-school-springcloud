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

    @GetMapping("/apiFeign/product/inner/getCategory/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId);

    @GetMapping("/apiFeign/product/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable("skuId") Long skuId);

    @PostMapping("/apiFeign/product/inner/findSkuInfoList")
    public List<SkuInfo> findSkuInfoList(@RequestBody List<Long> skuIdList);

    @GetMapping("/apiFeign/product/inner/findByKeyword/{keyword}")
    public List<SkuInfo> findByKeyword(@PathVariable String keyword);

    @PostMapping("/apiFeign/product/inner/findCategoryList")
    public List<Category> findCategoryList(@RequestBody List<Long> categoryList);

    @GetMapping("/apiFeign/product/inner/findAllCategoryList")
    public List<Category> findAllCategoryList();

    @GetMapping("/apiFeign/product/inner/findIsNewPersonSkuList")
    public List<SkuInfo> findIsNewPersonSkuList();
}
