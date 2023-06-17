package com.shopping.product.service.impl;


import com.shopping.model.product.SkuAttrValue;
import com.shopping.product.mapper.SkuAttrValueMapper;
import com.shopping.product.service.SkuAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * spu属性值 服务实现类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValue> implements SkuAttrValueService {

    @Override
    public List<SkuAttrValue> findBySkuId(Long skuId) {
        return query().eq("sku_id",skuId).list();
    }
}
