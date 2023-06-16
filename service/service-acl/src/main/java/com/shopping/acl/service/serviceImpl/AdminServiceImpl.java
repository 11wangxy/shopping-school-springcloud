package com.shopping.acl.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.acl.mapper.AdminMapper;
import com.shopping.acl.service.AdminService;
import com.shopping.model.acl.Admin;
import com.shopping.vo.acl.AdminQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 18:37
 * @description: shopping-parent
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    /**
     * 分页查询
     * @param current
     * @param limit
     * @param adminQueryVo
     * @return
     */
    @Override
    public IPage pages(Long current, Long limit, AdminQueryVo adminQueryVo) {
        Page<Admin> pageParam = new Page<>(current,limit);
        //获取用户名称
        String username = adminQueryVo.getUsername();
        //获取称呼
        String name = adminQueryVo.getName();
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            queryWrapper.like(Admin::getUsername,username);
        }
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like(Admin::getName, name);
        }
        IPage<Admin> adminIPage = baseMapper.selectPage(pageParam,queryWrapper);
        return adminIPage;
    }
}
