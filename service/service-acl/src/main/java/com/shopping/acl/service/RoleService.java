package com.shopping.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.acl.Role;
import com.shopping.vo.acl.RoleQueryVo;

import java.util.Map;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 14:58
 * @description: shopping-parent
 */
public interface RoleService extends IService<Role> {
    IPage pages(Long current, Long limit, RoleQueryVo roleQueryVo);

    Map<String, Object> getRoleByAdminId(Long adminId);

    void saveAdminRole(Long adminId, Long[] roleId);
}
