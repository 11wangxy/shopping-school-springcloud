package com.shopping.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shopping.common.result.Result;
import com.shopping.model.sys.Ware;
import com.shopping.sys.service.WareService;
import com.shopping.vo.product.WareQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */


@RestController
@Api(tags = "仓库接口")
@RequestMapping("/admin/sys/ware")
public class WareController {

    @Resource
    private WareService wareService;

    @ApiOperation("分页查询")
    @GetMapping("{page}/{limit}")
    public Result page(@PathVariable Long page,
                       @PathVariable Long limit,
                       WareQueryVo wareQueryVo){
        IPage result = wareService.page(page,limit,wareQueryVo);
        return Result.ok(result);
    }
    @ApiOperation("根据id查询")
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable Long id){
        return Result.ok(wareService.getById(id));
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public Result save(@RequestBody Ware ware){
        wareService.save(ware);
        return Result.ok();
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public Result update(@RequestBody Ware ware){
        wareService.updateById(ware);
        return Result.ok();
    }

    @ApiOperation("单删")
    @DeleteMapping("/remove/{id}")
    public Result delete(@PathVariable Long id){
        wareService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("多删")
    @DeleteMapping("/batchRemove")
    public Result deleteIds(@RequestBody List<Long> wares){
        wareService.removeByIds(wares);
        return Result.ok();
    }

    @ApiOperation("查询所有的仓库列表")
    @GetMapping("/findAllList")
    public Result findAllList(){
        List<Ware> list = wareService.list();
        return Result.ok(list);
    }

}

