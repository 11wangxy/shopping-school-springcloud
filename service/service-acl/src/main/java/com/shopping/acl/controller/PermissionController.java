package com.shopping.acl.controller;

import com.shopping.acl.service.PermissionService;
import com.shopping.common.result.Result;
import com.shopping.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 19:52
 * @description: shopping-parent
 */

@RestController
@Api(tags = "菜单管理")
@RequestMapping("/admin/acl/permission")

public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @ApiOperation("查询所有菜单")
    @GetMapping
    public Result list(){
        List<Permission> list = permissionService.queryAllPermission();
        return Result.ok(list);
    }

    @ApiOperation("保存菜单")
    @PostMapping("/save")
    public Result save(@RequestBody Permission permission){
        permissionService.save(permission);
        return Result.ok(null);
    }

    @ApiOperation("菜单更新")
    @PutMapping("/update")
    public Result update(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return Result.ok(null);
    }

    @ApiOperation("递归删除菜单")
    @DeleteMapping("/remove/{id}")
    public Result delete(@PathVariable Long id){
        permissionService.removeChildById(id);
        return Result.ok(null);
    }

    @ApiOperation("查询所有权限和已有的权限列表")
    @GetMapping("/toAssign/{roleId}")
    public Result getAssign(@PathVariable Long roleId){
        Map<String,Object> map= permissionService.getAssign(roleId);
        return Result.ok(map);
    }

    @ApiOperation("分配权限")
    @PostMapping("/doAssign")
    public Result setAssign(@RequestParam Long roleId,
                            @RequestParam Long[] permissionId){
        permissionService.setAssign(roleId,permissionId);
        return Result.ok();
    }
}
