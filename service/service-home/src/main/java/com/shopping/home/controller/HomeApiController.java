package com.shopping.home.controller;

import com.shopping.common.auth.AuthContextHolder;
import com.shopping.common.result.Result;
import com.shopping.home.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-23 1:22
 * @description: shopping-parent
 */
@Api(tags = "接口首页")
@RestController

@RequestMapping("/api/home")
public class HomeApiController {
    @Resource
    private HomeService homeService;
    @ApiOperation("首页数据显示")
    @GetMapping("/index")
    public Result index(HttpServletRequest request){
        Long userId = AuthContextHolder.getUserId();
        Map<String,Object> map=homeService.homeData(userId);
        return Result.ok(map);
    }
}
