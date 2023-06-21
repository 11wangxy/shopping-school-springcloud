package com.shopping.activity.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.activity.ActivityInfo;
import com.shopping.vo.activity.ActivityRuleVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 活动表 服务类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-21
 */
public interface ActivityInfoService extends IService<ActivityInfo> {

    IPage<ActivityInfo> selectPage(Long page, Long limit);

    Map<String,Object> findActivityRuleList(Long id);

    void saveActivityRule(ActivityRuleVo activityRuleVo);

    List findSkuInfoByKeyword(String keyword);
}
