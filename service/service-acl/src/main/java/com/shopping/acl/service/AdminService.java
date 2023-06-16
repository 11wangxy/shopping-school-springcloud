package com.shopping.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.acl.Admin;
import com.shopping.vo.acl.AdminQueryVo;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 18:37
 * @description: shopping-parent
 */
public interface AdminService extends IService<Admin> {
    IPage pages(Long current, Long limit, AdminQueryVo adminQueryVo);
}
