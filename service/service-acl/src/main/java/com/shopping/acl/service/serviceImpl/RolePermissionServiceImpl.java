package com.shopping.acl.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.acl.mapper.RolePermissionMapper;
import com.shopping.acl.service.RolePermissionService;
import com.shopping.model.acl.RolePermission;
import org.springframework.stereotype.Service;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 15:24
 * @description: shopping-parent
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
