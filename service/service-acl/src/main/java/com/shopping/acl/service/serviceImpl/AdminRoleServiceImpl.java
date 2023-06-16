package com.shopping.acl.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.acl.mapper.AdminRoleMapper;
import com.shopping.acl.service.AdminRoleService;
import com.shopping.model.acl.AdminRole;
import org.springframework.stereotype.Service;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 19:54
 * @description: shopping-parent
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper,AdminRole> implements AdminRoleService {
}
