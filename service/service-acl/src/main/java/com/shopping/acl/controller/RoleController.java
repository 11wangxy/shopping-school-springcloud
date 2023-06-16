package com.shopping.acl.controller;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.shopping.acl.service.RoleService;
import com.shopping.common.result.Result;
import com.shopping.model.acl.Role;
import com.shopping.vo.acl.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Queue;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 14:56
 * @description: shopping-parent 用户表操作
 */
@Api(tags = "角色接口")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {

    @Resource
    private RoleService roleService;

    @ApiOperation("角色分页")
    @GetMapping("/{current}/{limit}")
    public Result pageList(@PathVariable Long current,
                           @PathVariable Long limit,
                           RoleQueryVo roleQueryVo) {
        IPage pages = roleService.pages(current, limit, roleQueryVo);
        return Result.ok(pages);
    }

    @ApiOperation("根据id查询")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

    @ApiOperation("添加角色")
    @PostMapping("/save")
    public Result save(@RequestBody Role role) {
        boolean save = roleService.save(role);
        if (save) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation("更新角色")
    @PutMapping("/update")
    public Result update(@RequestBody Role role) {
        boolean update = roleService.updateById(role);
        if (update) {
            return Result.ok(null);
        }
        return Result.fail(null);

    }

    @ApiOperation("删除一个角色")
    @DeleteMapping("/remove/{id}")
    public Result deldete(@PathVariable Long id) {
        boolean removeById = roleService.removeById(id);
        if (removeById) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation("删除多个角色")
    @DeleteMapping("/batchRemove")
    public Result deleteBatch(@RequestBody List<Long> role) {
        boolean removeByIds = roleService.removeByIds(role);
        if (removeByIds){
            return Result.ok(null);
        }
        return Result.fail(null);
    }

}
