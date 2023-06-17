package com.shopping.product.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.product.AttrGroup;
import com.shopping.vo.product.AttrGroupQueryVo;

import java.util.List;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
public interface AttrGroupService extends IService<AttrGroup> {

    IPage pages(Long page, Long limit, AttrGroupQueryVo attrGroupQueryVo);

    List findAllList();
}
