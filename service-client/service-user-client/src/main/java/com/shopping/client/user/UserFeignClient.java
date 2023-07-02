package com.shopping.client.user;

import com.shopping.vo.user.LeaderAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-23 1:48
 * @description: shopping-parent
 */
@FeignClient(value = "service-user")
public interface UserFeignClient {
    @GetMapping("/apiFeign/user/leader/inner/getUserAddressByUserId/{userId}")
    public LeaderAddressVo getUserAddressByUserId(@PathVariable(value = "userId") Long userId);
}
