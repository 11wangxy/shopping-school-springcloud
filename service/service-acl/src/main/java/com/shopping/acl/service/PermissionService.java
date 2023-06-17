package com.shopping.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.acl.Permission;

import java.util.List;
import java.util.Map;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 0:26
 * @description: shopping-parent
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> queryAllPermission();

    void removeChildById(Long id);

    Map<String, Object> getAssign(Long roleId);

    void setAssign(Long roleId, Long[] permissionId);
}
