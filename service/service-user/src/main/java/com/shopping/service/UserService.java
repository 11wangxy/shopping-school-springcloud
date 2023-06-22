package com.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.user.User;
import com.shopping.vo.user.LeaderAddressVo;

import java.util.Map;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-22 19:26
 * @description: shopping-parent
 */
public interface UserService extends IService<User> {
    Map<String, Object> login(String code);

    LeaderAddressVo getLeaderAddressByUserId(Long userId);
}
