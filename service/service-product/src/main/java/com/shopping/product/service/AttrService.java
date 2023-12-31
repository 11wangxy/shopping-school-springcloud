package com.shopping.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.product.Attr;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
public interface AttrService extends IService<Attr> {
    //根据属性分组id 获取属性列表
    List<Attr> findByAttrGroupId(Long attrGroupId);
}
