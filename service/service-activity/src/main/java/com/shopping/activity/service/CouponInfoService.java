package com.shopping.activity.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.model.activity.CouponInfo;
import com.shopping.vo.activity.CouponRuleVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-21
 */
public interface CouponInfoService extends IService<CouponInfo> {

    IPage<CouponInfo> selectPage(Long page, Long limit);

    CouponInfo getCouponInfo(String id);

    Map<String, Object> findCouponRuleList(Long id);

    void saveCouponRule(CouponRuleVo couponRuleVo);

    List findCouponByKeyword(String keyword);
}
