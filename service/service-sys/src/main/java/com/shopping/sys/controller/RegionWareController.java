package com.shopping.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shopping.common.result.Result;
import com.shopping.model.sys.RegionWare;
import com.shopping.sys.service.RegionWareService;
import com.shopping.vo.sys.RegionWareQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 城市仓库关联表 前端控制器
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
@Api(tags = "开通区域接口")
@CrossOrigin
@RestController
@RequestMapping("/admin/sys/regionWare")
public class RegionWareController {
    @Resource
    private RegionWareService regionWareService;

    //开通区域列表
    @ApiOperation(value = "获取开通区域列表")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        RegionWareQueryVo regionWareQueryVo){
        IPage<RegionWare> pages = regionWareService.pages(page,limit,regionWareQueryVo);
        return Result.ok(pages);
    }

    @ApiOperation("添加开通区域")
    @PostMapping("save")
    public Result save(@RequestBody RegionWare regionWare){
        regionWareService.saveRegionWare(regionWare);
        return Result.ok();
    }

    @ApiOperation("根据id查询")
    @GetMapping("/get/{id}")
    public Result save(@PathVariable Long id){
        regionWareService.getById(id);
        return Result.ok();
    }

    //删除开通区域
    @ApiOperation(value = "单删")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        regionWareService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "取消开通区域")
    @PostMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id,@PathVariable Integer status) {
        regionWareService.updateStatus(id, status);
        return Result.ok();
    }

    @ApiOperation("多删")
    @DeleteMapping("/batchRemove")
    public Result deleteByIds(@RequestBody List<Long> idList){
        regionWareService.removeByIds(idList);
        return Result.ok();
    }
}

