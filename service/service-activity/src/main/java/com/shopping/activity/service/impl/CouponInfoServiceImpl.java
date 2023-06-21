package com.shopping.activity.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shopping.activity.mapper.CouponInfoMapper;
import com.shopping.activity.mapper.CouponRangeMapper;
import com.shopping.activity.mapper.CouponUseMapper;
import com.shopping.activity.service.CouponInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.client.product.ProductFeignClient;
import com.shopping.enums.CouponRangeType;
import com.shopping.model.activity.ActivityInfo;
import com.shopping.model.activity.CouponInfo;
import com.shopping.model.activity.CouponRange;
import com.shopping.model.product.Category;
import com.shopping.model.product.SkuInfo;
import com.shopping.vo.activity.CouponRuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.RequestWrapper;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-21
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Autowired
    private CouponRangeMapper couponRangeMapper;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private CouponUseMapper couponUseMapper;
    @Override
    public IPage selectPage(Long page, Long limit) {
        Page<CouponInfo> param = new Page<>(page,limit);
        IPage<CouponInfo> page1 = baseMapper.selectPage(param, null);
        List<CouponInfo> records = page1.getRecords();
        records.forEach(item->{
            item.setCouponTypeString(item.getCouponType().getComment());
            CouponRangeType rangeType = item.getRangeType();
            if (rangeType!=null){
                item.setRangeTypeString(rangeType.getComment());
            }
        });
        return page1;
    }

    @Override
    public CouponInfo getCouponInfo(String id) {
        CouponInfo couponInfo = baseMapper.selectById(id);
        couponInfo.setCouponTypeString(couponInfo.getRangeType().getComment());
        if (couponInfo.getRangeType()!=null){
            couponInfo.setRangeTypeString(couponInfo.getRangeType().getComment());
        }
        return couponInfo;
    }

    @Override
    public Map<String, Object> findCouponRuleList(Long id) {
        Map<String,Object> map = new HashMap<>();
        CouponInfo couponInfo = baseMapper.selectById(id);
        List<CouponRange> couponRangeList = couponRangeMapper.selectList(
                new LambdaQueryWrapper<CouponRange>().eq(CouponRange::getCouponId, id)
        );
        List<Long> collect = couponRangeList.stream().map(CouponRange::getRangeId).collect(Collectors.toList());
        if (collect.size()!=0){
            if (couponInfo.getRangeType()==CouponRangeType.SKU){
                List<SkuInfo> skuInfoList = productFeignClient.findSkuInfoList(collect);
                map.put("skuInfoList",skuInfoList);
            }else if (couponInfo.getRangeType()==CouponRangeType.CATEGORY){
                List<Category> categoryList =productFeignClient.findCategoryList(collect);
                map.put("categoryList",categoryList);
            }
        } else {
            map.put("skuInfoList",null);
            map.put("categoryList",null);
        }
        return map;
    }

    @Override
    public void saveCouponRule(CouponRuleVo couponRuleVo) {
        //先删除
        Long couponId = couponRuleVo.getCouponId();
        couponRangeMapper.deleteById(couponId);
        //添加值
        CouponInfo couponInfo = baseMapper.selectById(couponId);
        couponInfo.setRangeType(couponRuleVo.getRangeType()).setConditionAmount(couponRuleVo.getConditionAmount())
                .setAmount(couponRuleVo.getAmount()).setConditionAmount(couponRuleVo.getConditionAmount())
                        .setRangeDesc(couponRuleVo.getRangeDesc());
        //更新
        baseMapper.updateById(couponInfo);
        //添加规则数据
        List<CouponRange> couponRangeList = couponRuleVo.getCouponRangeList();
        couponRangeList.forEach(item->{
            item.setCouponId(couponRuleVo.getCouponId());
            couponRangeMapper.insert(item);
        });
    }

    @Override
    public List findCouponByKeyword(String keyword) {
        return null;
    }
}
