package com.shopping.home.service.Impl;

import com.shopping.client.product.ProductFeignClient;
import com.shopping.client.user.UserFeignClient;
import com.shopping.home.service.HomeService;
import com.shopping.model.product.Category;
import com.shopping.model.product.SkuInfo;
import com.shopping.model.search.SkuEs;
import com.shopping.vo.user.LeaderAddressVo;
import com.shoppinh.client.search.SkuFeignClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-23 1:25
 * @description: shopping-parent
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private UserFeignClient userFeignClient;
    @Resource
    private ProductFeignClient productFeignClient;
    @Resource
    private SkuFeignClient skuFeignClient;

    @Override
    public Map<String, Object> homeData(Long userId) {
        Map<String,Object> map =new HashMap<>();
        //1.根据远程调用service-user通过userid获取当前的登录语句提货地址信息
        LeaderAddressVo leaderAddressVo = userFeignClient.getUserAddressByUserId(userId);
        map.put("leaderAddressVo",leaderAddressVo);
        //2.通过service-product得到所有分类
        List<Category> categoryList = productFeignClient.findAllCategoryList();
        map.put("categoryList",categoryList);
        //3.通过调用service-product得到新人专享商品
        List<SkuInfo> newPersonSkuInfoList =productFeignClient.findIsNewPersonSkuList();
        map.put("newPersonSkuInfoList",newPersonSkuInfoList);
        //4.远程调用service-search获取商家的爆款商品,热门评分降序
        List<SkuEs> hotSkuList = skuFeignClient.findHotSkuList();
        map.put("hotSkuList",hotSkuList);
        //5.封装数据
        return map;
    }
}
