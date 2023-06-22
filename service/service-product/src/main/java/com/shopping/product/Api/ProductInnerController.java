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
import java.util.List;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-19 1:20
 * @description: shopping-parent
 */
@CrossOrigin
@RestController
@RequestMapping("/api/product")
@Api(tags = "远程调用根据skuId查询")
public class ProductInnerController {
    @Resource
    private CategoryService categoryService;

    @Resource
    private SkuInfoService skuInfoService;


    @GetMapping("/inner/getCategory/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        return categoryService.getById(categoryId);
    }


    @GetMapping("/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable Long skuId) {
        return skuInfoService.getById(skuId);
    }

    @PostMapping("/inner/findSkuInfoList")
    public List<SkuInfo> findSkuInfoList(@RequestBody List<Long> skuIdList) {
        return skuInfoService.findSkuList(skuIdList);
    }

    @GetMapping("/inner/findByKeyword/{keyword}")
    public List<SkuInfo> findByKeyword(@PathVariable String keyword) {
        return skuInfoService.findByKeyword(keyword);
    }

    @PostMapping("/inner/findCategoryList")
    public List<Category> findCategoryList(@RequestBody List<Long> categoryList) {
        return categoryService.listByIds(categoryList);
    }

    //获取所有分类
    @GetMapping("/inner/findAllCategoryList")
    public List<Category> findAllCategoryList(){
        List<Category> list = categoryService.list();
        return list;
    }

    /**
     * 显示新人专享商品
     * @return
     */
    @GetMapping("/inner/findIsNewPersonSkuList")
    public List<SkuInfo> findIsNewPersonSkuList(){
        List<SkuInfo> list = skuInfoService.selectIsNewPersonSkuList();
        return list;
    }
}
