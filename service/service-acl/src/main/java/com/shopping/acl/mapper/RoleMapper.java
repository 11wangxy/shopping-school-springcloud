package com.shopping.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.model.acl.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 14:59
 * @description: shopping-parent
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
