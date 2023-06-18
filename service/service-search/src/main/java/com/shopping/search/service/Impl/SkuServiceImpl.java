package com.shopping.search.service.Impl;

import com.shopping.client.product.ProductFeignClient;
import com.shopping.enums.SkuType;
import com.shopping.model.product.Category;
import com.shopping.model.product.SkuInfo;
import com.shopping.model.search.SkuEs;
import com.shopping.search.repositry.SkuRepositry;
import com.shopping.search.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-19 1:12
 * @description: shopping-parent
 */
@Service
@Slf4j
public class SkuServiceImpl implements SkuService{

    @Resource
    private SkuRepositry skuRepositry;

    @Resource
    private ProductFeignClient productFeignClient;

    /**
     * 把商品信息添加到ES
     * @param skuId
     */
    @Override
    public void upperSku(Long skuId) {
        //1.通过远程调用根据skuId获取相关信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        if (skuInfo==null){
            return;
        }
        Category category = productFeignClient.getCategory(skuInfo.getCategoryId());
        SkuEs skuEs = SkuEs.builder().build();
        //2.获取数据封装进对象
        if (category!=null){
            skuEs.setCategoryId(category.getId()).setCategoryName(category.getName());
        }
        skuEs.setId(skuInfo.getId()).setKeyword(skuInfo.getSkuName()+","+skuEs.getCategoryName()).
                setWareId(skuInfo.getWareId()).setIsNewPerson(skuInfo.getIsNewPerson()).
                setImgUrl(skuInfo.getImgUrl()).setTitle(skuInfo.getSkuName());
        if (skuInfo.getSkuType() == SkuType.COMMON.getCode()){
            skuEs.setSkuType(0).setPrice(skuInfo.getPrice().doubleValue()).
                    setStock(skuInfo.getStock()).setSale(skuInfo.getSale()).
                    setPerLimit(skuInfo.getPerLimit());
        }else {
            //TODO 待完善-秒杀商品
        }
        //3.调用方法添加到ES
        skuRepositry.save(skuEs);
    }

    @Override
    public void lower(Long skuId) {
        skuRepositry.deleteById(skuId);
    }
}
