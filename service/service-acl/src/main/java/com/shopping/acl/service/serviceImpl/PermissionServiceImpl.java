package com.shopping.acl.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.acl.mapper.PermissionMapper;
import com.shopping.acl.service.PermissionService;
import com.shopping.acl.utils.PermissionHelp;
import com.shopping.model.acl.Permission;
import com.sun.javafx.tk.PermissionHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 0:26
 * @description: shopping-parent
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
    public List<Permission> queryAllPermission() {
        //1.查询所有菜单
        List<Permission> allPermissionList = baseMapper.selectList(null);

        //2. 转化成要求的格式
        List<Permission> result = PermissionHelp.buildPermission(allPermissionList);
        return result;
    }

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
