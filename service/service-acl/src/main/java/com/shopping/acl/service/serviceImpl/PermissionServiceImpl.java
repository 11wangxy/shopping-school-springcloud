package com.shopping.acl.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.acl.mapper.PermissionMapper;
import com.shopping.acl.service.PermissionService;
import com.shopping.acl.service.RolePermissionService;
import com.shopping.acl.utils.PermissionHelp;
import com.shopping.model.acl.AdminRole;
import com.shopping.model.acl.Permission;
import com.shopping.model.acl.Role;
import com.shopping.model.acl.RolePermission;
import com.sun.javafx.tk.PermissionHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 0:26
 * @description: shopping-parent
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Permission> queryAllPermission() {
        //1.查询所有菜单
        List<Permission> allPermissionList = baseMapper.selectList(null);

        //2. 转化成要求的格式
        List<Permission> result = PermissionHelp.buildPermission(allPermissionList);
        return result;
    }

    /**
     * 递归删除所有子菜单的id
     * @param id
     */
    @Override
    public void removeChildById(Long id) {
        //创建集合封装所有菜单id
        List<Long> idList = new ArrayList<>();
        //根据当前菜单id获取当前菜单下的子菜单，不断递归
        this.getAllPermissionId(id,idList);
        //设置当前菜单id也需要删除
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    /**
     * 查询所有菜单和查询角色已经分配过的菜单
     * @param roleId
     * @return
     */
    @Override
    public Map<String, Object> getAssign(Long roleId) {
        //查询所有菜单，直接封装进map
        List<Permission> permissions = baseMapper.selectList(null);
        //去role_permission表中查询roleId等于传递进来的id值，封装进list集合中
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, roleId);
        List<RolePermission> rolePermissionList = rolePermissionService.list(queryWrapper);
        //把得到的rolepermission集合中得到roleId值，封装进新的列表中list<long>
        List<Long> collect = rolePermissionList.stream().
                map(RolePermission::getRoleId).collect(Collectors.toList());
        List<Permission> permissionList = new ArrayList<>();
        //遍历每个菜单，如果刚刚的集合list包含了这个菜单的id，则封装进一个集合中去
        for (Permission permission: permissions){
            if (collect.contains(permission.getId()));
            permissionList.add(permission);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("allPermissions",permissions);
        map.put("assignPermissions",permissionList);
        return map;
    }

    @Override
    public void setAssign(Long roleId, Long[] permissionId) {
        //1.删除角色分配过的菜单
        //根据id删除role_permission表中数据
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId,roleId);
        rolePermissionService.remove(queryWrapper);
        //2. 重新分配
        //遍历多个角色id，得到每个角色的id，拿着每个角色的id和用户id添加用户角色关系表
        List<RolePermission> list = new ArrayList<>();
        for (Long permissionsId:permissionId){
            if (StringUtils.isEmpty(permissionsId)) continue;
            RolePermission build = RolePermission.builder().permissionId(permissionsId).roleId(roleId).build();
            list.add(build);
        }
        rolePermissionService.saveBatch(list);
    }

    /**
     * 查询所有菜单的id，递归查询
     * @param id
     * @param idList
     */
    public void getAllPermissionId(Long id,List<Long> idList){
        //根据当前的id查询下面的id
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getPid,id);
        List<Permission> ChildList = baseMapper.selectList(queryWrapper);
        //递归查询是否具有子菜单
        ChildList.stream().forEach(item->{
            idList.add(item.getId());
            this.getAllPermissionId(item.getId(),idList);
        });
    }
}
