package com.shopping.sys.controller;


import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.shopping.common.result.Result;
import com.shopping.model.sys.Region;
import com.shopping.sys.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */


@RestController
@RequestMapping("/admin/sys/region")
@Api(tags = "地区接口")
public class RegionController {
    @Resource
    private RegionService regionService;

    @ApiOperation("根据区域关键字查询地区")
    @GetMapping("/findRegionByKeyword/{keyword}")
    public Result getByKeyword(@PathVariable String keyword){
        List<Region> list= regionService.getRegionByKeyword(keyword);
        return Result.ok(list);
    }

    @ApiOperation("根据parentId查询")
    @GetMapping("/findByParentId/{parentId}")
    public Result findByParentId(@PathVariable Long parentId){
        List<Region> list =  regionService.query().eq("parent_id", parentId).list();
        return Result.ok(list);
    }
}

