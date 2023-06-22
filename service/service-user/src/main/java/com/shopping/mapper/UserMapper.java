package com.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.model.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-22 19:27
 * @description: shopping-parent
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
