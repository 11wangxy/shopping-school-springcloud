package com.shopping.acl.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.acl.mapper.RoleMapper;
import com.shopping.acl.service.AdminRoleService;
import com.shopping.acl.service.RoleService;
import com.shopping.model.acl.AdminRole;
import com.shopping.model.acl.Role;
import com.shopping.vo.acl.RoleQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.management.relation.RoleUnresolved;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 14:59
 * @description: shopping-parent
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private AdminRoleService adminRoleService;

    /**
     * 角色分页查询
     * @param current
     * @param limit
     * @param roleQueryVo
     * @return
     */
    @Override
    public IPage pages(Long current, Long limit, RoleQueryVo roleQueryVo) {
        //1.创建page对象，传递当前页和每页记录数
        Page<Role> PageParam = new Page<>(current, limit);
        //2.实现分页查询,创建查询对象
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();

        //2.1获取条件值
        String roleName = roleQueryVo.getRoleName();
        //2.2判断条件值是否为空，不为则封装查询条件
        if (!StringUtils.isEmpty(roleName)){
            queryWrapper.like(Role::getRoleName,roleName);
        }
        //2.3调用方法实现条件分页查询
        IPage<Role> rolePage = baseMapper.selectPage(PageParam, queryWrapper);
        //返回分页对象3
        return rolePage;
    }

    /**
     * 查询所有角色 查询用户角色列表
     * @param adminId
     * @return
     */
    @Override
    public Map<String, Object> getRoleByAdminId(Long adminId) {
        //1. 查询所有角色
        List<Role> roles = baseMapper.selectList(null);
        //2.根据用户id查询用户分配角色列表
        //2.1根据用户id查询用户关系角色表admin_role，查询用户分配角色id列表
        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<>();
        //根据用户id查询
        queryWrapper.eq(AdminRole::getAdminId,adminId);
        List<AdminRole> adminRoleList = adminRoleService.list(queryWrapper);
        //2.2通过返回的集合，获取所有角色id的列表
        List<Long> roleCollections = adminRoleList.stream().map(AdminRole::getRoleId).collect(Collectors.toList());
        //2.3创建新的list结合，存储用户配置角色
        List<Role> assignRoleList = new ArrayList<>();
        //2.4遍历角色列表，得到每个角色
        //判断所有角色是否包含已经分配的id，封装到2.3里面新的list结合
        for (Role role:roles){
            if (roleCollections.contains(role.getId())){
                assignRoleList.add(role);
            }
        }
        //封装到map集合
        Map<String,Object> result = new HashMap<>();
        //所有角色
        result.put("allRolesList",roles);
        //用户分配角色列表
        result.put("assignRoles",assignRoleList);
        return result;
    }


    /**
     * 分配用户角色
     * @param adminId
     * @param roleIds
     */
    @Override
    public void saveAdminRole(Long adminId, Long[] roleIds) {
        //1.删除用户以及过时的角色数据
        //根据id删除admin——role表中数据
        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<AdminRole> eq = queryWrapper.eq(AdminRole::getAdminId, adminId);
        adminRoleService.remove(eq);
        //2. 重新分配
        //遍历多个角色id，得到每个角色的id，拿着每个角色的id和用户id添加用户角色关系表
        List<AdminRole> list = new ArrayList<>();
        for (Long roleId:roleIds){
            if (StringUtils.isEmpty(roleId)) continue;
            AdminRole adminRole = AdminRole.builder().adminId(adminId).roleId(roleId).build();
            list.add(adminRole);
        }
        adminRoleService.saveBatch(list);
    }
}
