package com.shopping.controller;

import com.alibaba.fastjson2.JSONObject;
import com.shopping.common.auth.AuthContextHolder;
import com.shopping.common.exception.shoppingException;
import com.shopping.common.result.Result;
import com.shopping.common.result.ResultCodeEnum;
import com.shopping.model.user.User;
import com.shopping.service.UserService;
import com.shopping.utils.HttpClientUtils;
import com.shopping.utils.WeChatProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-22 19:00
 * @description: shopping-parent
 */
@CrossOrigin
@RestController
@Api(tags = "微信登录")
@RequestMapping("/api/user/weixin")
public class WechatController {

    @Resource
    private UserService userService;
    @ApiOperation(value = "微信登录获取openid(小程序)")
    @GetMapping("/wxLogin/{code}")
    public Result login(@PathVariable String code){
        return Result.ok(userService.login(code));
    }

    @PostMapping("/auth/updateUser")
    @ApiOperation(value = "更新用户昵称与头像")
    public Result updateUser(@RequestBody User user) {
        User user1 = userService.getById(AuthContextHolder.getUserId());
        //把昵称更新为微信用户
        user1.setNickName(user.getNickName().replaceAll("[ue000-uefff]", "*"));
        user1.setPhotoUrl(user.getPhotoUrl());
        userService.updateById(user1);
        return Result.ok(null);
    }
}
