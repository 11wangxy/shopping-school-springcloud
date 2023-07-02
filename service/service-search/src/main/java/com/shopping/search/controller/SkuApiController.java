package com.shopping.search.controller;

import com.shopping.common.result.Result;
import com.shopping.model.search.SkuEs;
import com.shopping.search.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-19 1:11
 * @description: shopping-parent
 */
@RequestMapping("/apiFeign/search/sku")
@Api(tags = "上下架")

@RestController
public class SkuApiController {
    @Resource
    private SkuService skuService;

    @ApiOperation("上架")
    @GetMapping("/inner/upperSku/{skuId}")
    public Result upperSku(@PathVariable Long skuId){
        skuService.upperSku(skuId);
        return Result.ok();
    }

    @ApiOperation("下架")
    @GetMapping("/inner/lowerSku/{skuId}")
    public Result lowerSku(@PathVariable Long skuId){
        skuService.lower(skuId);
        return Result.ok();
    }

    @ApiOperation("查询爆款商品")
    @GetMapping("/inner/findHotSkuList")
    public List<SkuEs> findHotSkuList(){
        List<SkuEs> list=skuService.findSkuHotList();
        return list;
    }
}
