package com.shopping.acl.utils;

import com.shopping.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-17 1:06
 * @description: shopping-parent
 */
public class PermissionHelp {
    public static List<Permission> buildPermission(List<Permission> allList){
        List<Permission> trees = new ArrayList<>();
        //遍历菜单list集合，得到第一层数据
        for (Permission permission:allList){
            //pid=0的数据为第一层数据
            if (permission.getPid()==0){
                permission.setLevel(1);
                trees.add(findChildren(permission,allList));
            }
        }
        return trees;
    }

    //递归查找
    //permission为上层数据，从此开始查找
    private static Permission findChildren(Permission permission, List<Permission> allList) {
        permission.setChildren(new ArrayList<Permission>());
        //遍历alllist菜单数据
        //判断pid=id是否一样，递归往下找
        for (Permission it:allList){
            if (permission.getId().longValue()==it.getPid().longValue()){
                int level = permission.getLevel() + 1;
                it.setLevel(level);
                if (permission.getChildren()==null){
                    permission.setChildren(new ArrayList<>());
                }
                permission.getChildren().add(findChildren(it,allList));
            }
        }
        return permission;
    }
}
