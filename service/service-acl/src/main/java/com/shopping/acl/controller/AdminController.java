package com.shopping.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shopping.acl.service.AdminService;
import com.shopping.acl.service.RoleService;
import com.shopping.common.result.Result;
import com.shopping.model.acl.Admin;
import com.shopping.vo.acl.AdminQueryVo;
import com.shopping.vo.acl.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 17:36
 * @description: shopping-parent
 */

@RestController
@Api(tags = "用户管理")
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private RoleService roleService;

    @ApiOperation("角色分页")
    @GetMapping("/{current}/{limit}")
    public Result pageList(@PathVariable Long current,
                           @PathVariable Long limit,
                           AdminQueryVo adminQueryVo) {
        IPage pages = adminService.pages(current,limit,adminQueryVo);
        return Result.ok(pages);
    }

    @ApiOperation("根据id查询")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id){
        Admin byId = adminService.getById(id);
        return Result.ok(byId);
    }

    @ApiOperation("保存用户")
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        //MD5加密
        String password = admin.getPassword();
        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        admin.setPassword(passwordMD5);
        boolean save = adminService.save(admin);
        if (save){
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        boolean update = adminService.updateById(admin);
        if (update){
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation("删除单个用户")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id){
        boolean remove = adminService.removeById(id);
        if (remove){
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation("删除多个用户")
    @DeleteMapping("/batchRemove")
    public Result removeBatch(@RequestBody List<Long> admins){
        boolean b = adminService.removeByIds(admins);
        if (b){
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation("获取用户角色")
    @GetMapping("/toAssign/{adminId}")
    public Result getAssign(@PathVariable Long adminId){
        //map集合中包含两部分数据：所有角色 为用户分配角色列表
        Map<String,Object> map = roleService.getRoleByAdminId(adminId);
        return Result.ok(map);
    }


    @ApiOperation("为用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long adminId,
                           @RequestParam Long[] roleId){
        roleService.saveAdminRole(adminId,roleId);
        return Result.ok(null);
    }
}
