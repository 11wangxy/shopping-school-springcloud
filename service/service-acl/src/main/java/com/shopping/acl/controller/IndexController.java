package com.shopping.acl.controller;

import com.shopping.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 12:35
 * @description: shopping-parent
 */
@RestController
@RequestMapping("/admin/acl/index")
@CrossOrigin     //跨域
@Api(tags = "登录接口")
public class IndexController {

    /**
     * 1、请求登陆的login
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public Result login() {
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }

    /**
     * 2 获取用户信息 get info
     */
    @ApiOperation("获取用户信息")
    @GetMapping("info")
    public Result info(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","atguigu");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    /**
     * 3 退出 logout
     */
    @ApiOperation("退出")
    @PostMapping("logout")
    public Result logout(){
        return Result.ok(null);
    }
}
