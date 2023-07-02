package com.shopping.Api;

import com.shopping.service.UserService;
import com.shopping.vo.user.LeaderAddressVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-23 1:41
 * @description: shopping-parent
 */

@Api(tags = "团长接口")
@RestController
@RequestMapping("/apiFeign/user/leader")

public class LeaderAddressAPIController {
    @Resource
    private UserService userService;

    @GetMapping("/inner/getUserAddressByUserId/{userId}")
    public LeaderAddressVo getUserAddressByUserId(@PathVariable(value = "userId") Long userId) {
        return userService.getLeaderAddressByUserId(userId);
    }
}

