package com.shopping.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shopping.common.result.Result;
import com.shopping.model.product.AttrGroup;
import com.shopping.product.service.AttrGroupService;
import com.shopping.vo.product.AttrGroupQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 属性分组 前端控制器
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */

@RestController
@Api(tags = "")
@RequestMapping("/admin/product/attrGroup")
public class AttrGroupController {
    @Resource
    private AttrGroupService attrGroupService;

    @ApiOperation("平台属性分组列表查询")
    @GetMapping("/{page}/{limit}")
    public Result page(@PathVariable Long page,
                       @PathVariable Long limit,
                       AttrGroupQueryVo attrGroupQueryVo){
        IPage result =  attrGroupService.pages(page,limit,attrGroupQueryVo);
        return Result.ok(result);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        AttrGroup attrGroup = attrGroupService.getById(id);
        return Result.ok(attrGroup);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody AttrGroup attrGroup) {
        attrGroupService.save(attrGroup);
        return Result.ok();
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody AttrGroup attrGroup) {
        attrGroupService.updateById(attrGroup);
        return Result.ok();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        attrGroupService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        attrGroupService.removeByIds(idList);
        return Result.ok();
    }

    @ApiOperation(value = "获取全部属性分组，根据id进行排序")
    @GetMapping("findAllList")
    public Result findAllList() {
        return Result.ok(attrGroupService.findAllList());
    }
}

