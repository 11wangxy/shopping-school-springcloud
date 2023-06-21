package com.shopping.activity.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shopping.activity.mapper.ActivityInfoMapper;
import com.shopping.activity.mapper.ActivityRuleMapper;
import com.shopping.activity.mapper.ActivitySkuMapper;
import com.shopping.activity.service.ActivityInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.client.product.ProductFeignClient;
import com.shopping.model.activity.ActivityInfo;
import com.shopping.model.activity.ActivityRule;
import com.shopping.model.activity.ActivitySku;
import com.shopping.model.product.SkuInfo;
import com.shopping.vo.activity.ActivityRuleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 活动表 服务实现类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-21
 */
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfo> implements ActivityInfoService {

    @Resource
    private ActivityRuleMapper activityRuleMapper;
    @Resource
    private ActivitySkuMapper activitySkuMapper;
    @Resource
    private ProductFeignClient productFeignClient;

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @Override
    public IPage<ActivityInfo> selectPage(Long page, Long limit) {
        Page<ActivityInfo> param = new Page<>(page,limit);
        IPage<ActivityInfo> page1 = baseMapper.selectPage(param, null);
        List<ActivityInfo> records = page1.getRecords();
        records.stream().forEach(item->{
            item.setActivityTypeString(item.getActivityType().getComment());
        });
        return page1;
    }

    /**
     * 查询活动规则列表
     * @param id
     * @return
     */
    @Override
    public Map findActivityRuleList(Long id) {
        Map<String,Object> result = new HashMap<>();
        //1.查询活动id，把规则返回，查询规则表activity——rule
        LambdaQueryWrapper<ActivityRule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ActivityRule::getActivityId,id);
        List<ActivityRule> activityRules = activityRuleMapper.selectList(queryWrapper);
        if (activityRules.size()==0){
            result.put("activityRuleList", null);
        }
        result.put("activityRuleList", activityRules);
        //2.根据sku id查询规则商品列表activity_sku,需要远程调用
        List<ActivitySku> activitySkuList = activitySkuMapper.selectList(
                new LambdaQueryWrapper<ActivitySku>().eq(ActivitySku::getActivityId, id)
        );
        //获取所有sku id
        List<Long> collect = activitySkuList.stream().map(ActivitySku::getSkuId).collect(Collectors.toList());
        if (collect.size()==0){
            result.put("skuInfoList", null);
        }else {
            List<SkuInfo> skuInfoList = productFeignClient.findSkuInfoList(collect);
            result.put("skuInfoList", skuInfoList);
        }
        return result;
    }

    /**
     * 保存活动规则，先删除原来保存的活动规则
     * @param activityRuleVo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveActivityRule(ActivityRuleVo activityRuleVo) {
        //分别从两张表中删除之前的两条数据
        activityRuleMapper.delete(new QueryWrapper<ActivityRule>().eq("activity_id",activityRuleVo.getActivityId()));
        activitySkuMapper.delete(new QueryWrapper<ActivitySku>().eq("activity_id",activityRuleVo.getActivityId()));
        //获取规则列表中的数据
        List<ActivityRule> activityRuleList = activityRuleVo.getActivityRuleList();
        ActivityInfo activityInfo = baseMapper.selectById(activityRuleVo.getActivityId());
        activityRuleList.forEach(item->{
            item.setActivityId(activityRuleVo.getActivityId());
            item.setActivityType(activityInfo.getActivityType());
            activityRuleMapper.insert(item);
        });
        //获取规则范围数据
        List<ActivitySku> activitySkuList = activityRuleVo.getActivitySkuList();
        activitySkuList.forEach(item->{
            item.setActivityId(activityRuleVo.getActivityId());
            activitySkuMapper.insert(item);
        });
    }


    @Override
    public List findSkuInfoByKeyword(String keyword) {
        //1 根据输入的关键字查询sku匹配的内容列表，远程调用
        //1.1 service-product创建查询接口，根据关键字查询匹配内容列表
        //1.2 service-activity远程调用得到sku内容列表
        List<SkuInfo> skuInfoList = productFeignClient.findByKeyword(keyword);
        if (skuInfoList.size()==0){
            return skuInfoList;
        }
        //2判断商品是否已经参加之前的活动，如果参加了，或者活动过期则排除
        //2.1 判断activity info 和activity sku
        List<Long> skuIdList = skuInfoList.stream().map(SkuInfo::getId).collect(Collectors.toList());
        List<Long> existSkuIdList = baseMapper.selectSkuIdListExist(skuIdList);
        //2.2判断逻辑处理
        List<SkuInfo> finalSkuList = new ArrayList<>();
        skuInfoList.forEach(item->{
            if (!existSkuIdList.contains(item.getId())){
                finalSkuList.add(item);
            }
        });
        return finalSkuList;
    }
}
