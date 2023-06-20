package com.shopping.product.Api;

import com.shopping.common.result.Result;
import com.shopping.model.product.Category;
import com.shopping.model.product.SkuInfo;
import com.shopping.product.service.CategoryService;
import com.shopping.product.service.SkuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-19 1:20
 * @description: shopping-parent
 */
@RestController
@RequestMapping("/api/product")
@CrossOrigin
@Api(tags = "远程调用根据skuId查询")
public class ProductInnerController {
    @Resource
    private CategoryService categoryService;

    @Resource
    private SkuInfoService skuInfoService;

    @ApiOperation(value = "根据分类id获取分类信息")
    @GetMapping("/inner/getCategory/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        return categoryService.getById(categoryId);
    }

    @ApiOperation(value = "根据skuId获取sku信息")
    @GetMapping("/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable Long skuId) {
        return skuInfoService.getById(skuId);
    }

    @ApiOperation(value = "根据SkuId删除sku信息")
    @DeleteMapping("/inner/removeById/{skuId}")
    public void delete(@PathVariable Long skuId){skuInfoService.getById(skuId);}
}
