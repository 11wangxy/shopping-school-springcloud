package com.shopping.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.acl.Permission;

import java.util.List;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 0:26
 * @description: shopping-parent
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> queryAllPermission();

    void removeChildById(Long id);
}
