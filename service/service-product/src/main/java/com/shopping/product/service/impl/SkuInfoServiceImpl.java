package com.shopping.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shopping.model.product.SkuAttrValue;
import com.shopping.model.product.SkuImage;
import com.shopping.model.product.SkuInfo;
import com.shopping.model.product.SkuPoster;
import com.shopping.mq.constant.MqConst;
import com.shopping.mq.service.RabbitService;
import com.shopping.product.mapper.SkuInfoMapper;
import com.shopping.product.service.SkuAttrValueService;
import com.shopping.product.service.SkuImageService;
import com.shopping.product.service.SkuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.product.service.SkuPosterService;
import com.shopping.vo.product.SkuInfoQueryVo;
import com.shopping.vo.product.SkuInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * sku信息 服务实现类
 * </p>
 *
 * @author Wang Xiaoyi
 * @since 2023-06-17
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {
    @Resource
    private SkuImageService skuImagesService;
    @Resource
    private SkuAttrValueService skuAttrValueService;
    @Resource
    private SkuPosterService skuPosterService;
    @Resource
    private RabbitService rabbitService;
    @Resource
    private SkuInfoMapper skuInfoMapper;

    /**
     * 分页+条件查询
     * @param page
     * @param limit
     * @param skuInfoQueryVo
     * @return
     */
    @Override
    public IPage<SkuInfo> selectPage(Long page, Long limit, SkuInfoQueryVo skuInfoQueryVo) {
        Page<SkuInfo> pageParam = new Page<>(page, limit);
        //获取条件值
        String keyword = skuInfoQueryVo.getKeyword();
        String skuType = skuInfoQueryVo.getSkuType();
        Long categoryId = skuInfoQueryVo.getCategoryId();
        //封装条件
        LambdaQueryWrapper<SkuInfo> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(SkuInfo::getSkuName, keyword);
        }
        if (!StringUtils.isEmpty(skuType)) {
            wrapper.eq(SkuInfo::getSkuType, skuType);
        }
        if (!StringUtils.isEmpty(categoryId)) {
            wrapper.eq(SkuInfo::getCategoryId, categoryId);
        }
        //调用方法查询
        IPage<SkuInfo> skuInfoPage = baseMapper.selectPage(pageParam, wrapper);
        return skuInfoPage;
    }

    /**
     * 添加sku，分为三张表数据库分别保存
     * @param skuInfoVo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSkuInfo(SkuInfoVo skuInfoVo) {
        //保存sku信息

        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo, skuInfo);
        save(skuInfo);

        //保存sku海报
        List<SkuPoster> skuPosterList = skuInfoVo.getSkuPosterList();
        if (!CollectionUtils.isEmpty(skuPosterList)) {
            for (SkuPoster skuPoster : skuPosterList) {
                skuPoster.setSkuId(skuInfo.getId());
            }
            skuPosterService.saveBatch(skuPosterList);
        }

        //保存sku图片
        List<SkuImage> skuImagesList = skuInfoVo.getSkuImagesList();
        if (!CollectionUtils.isEmpty(skuImagesList)) {

            for (SkuImage skuImages : skuImagesList) {
                skuImages.setSkuId(skuInfo.getId());

            }
            skuImagesService.saveBatch(skuImagesList);
        }

        //保存sku平台属性
        List<SkuAttrValue> skuAttrValueList = skuInfoVo.getSkuAttrValueList();
        if (!CollectionUtils.isEmpty(skuAttrValueList)) {

            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(skuInfo.getId());

            }
            skuAttrValueService.saveBatch(skuAttrValueList);
        }
    }

    @Override
    public SkuInfoVo getSkuInfoVo(Long skuId) {
        return getSkuInfoDB(skuId);
    }

    /**
     * 更新商品，先删除原来的数据，再进行添加，这个操作也是分成对三张表分别操作
     * @param skuInfoVo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSkuInfo(SkuInfoVo skuInfoVo) {
        Long id = skuInfoVo.getId();
        //更新sku信息
        this.updateById(skuInfoVo);
        //删除sku海报
        skuPosterService.remove(new LambdaQueryWrapper<SkuPoster>().eq(SkuPoster::getSkuId, id));
        //保存sku海报
        List<SkuPoster> skuPosterList = skuInfoVo.getSkuPosterList();
        if (!CollectionUtils.isEmpty(skuPosterList)) {
            for (SkuPoster skuPoster : skuPosterList) {
                skuPoster.setSkuId(id);
            }
            skuPosterService.saveBatch(skuPosterList);
        }
        //删除sku图片
        skuImagesService.remove(new LambdaQueryWrapper<SkuImage>().eq(SkuImage::getSkuId, id));
        //保存sku图片
        List<SkuImage> skuImagesList = skuInfoVo.getSkuImagesList();
        if (!CollectionUtils.isEmpty(skuImagesList)) {
            for (SkuImage skuImages : skuImagesList) {
                skuImages.setSkuId(id);
            }
            skuImagesService.saveBatch(skuImagesList);
        }
        //删除sku平台属性
        skuAttrValueService.remove(new LambdaQueryWrapper<SkuAttrValue>().eq(SkuAttrValue::getSkuId, id));
        //保存sku平台属性
        List<SkuAttrValue> skuAttrValueList = skuInfoVo.getSkuAttrValueList();
        if (!CollectionUtils.isEmpty(skuAttrValueList)) {
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(id);
            }
            skuAttrValueService.saveBatch(skuAttrValueList);
        }
    }

    /**
     * 更新发布状态
     * @param skuId
     * @param status
     */
    @Override
    public void check(Long skuId, Integer status) {
        // 更改发布状态
        SkuInfo skuInfo = baseMapper.selectById(skuId);
        skuInfo.setCheckStatus(status);
        baseMapper.updateById(skuInfo);
    }

    /**
     * 发布商品，通过消息队列rabbitmq发送消息到es
     * @param skuId
     * @param status
     */
    @Override
    public void publish(Long skuId, Integer status) {
        if (status == 1) {
            SkuInfo skuInfo = baseMapper.selectById(skuId);
            skuInfo.setPublishStatus(1);
            baseMapper.updateById(skuInfo);
            //商品上架 后续会完善：发送mq消息更新es数据
            rabbitService.sendMsg(MqConst.EXCHANGE_GOODS_DIRECT, MqConst.ROUTING_GOODS_UPPER, skuId);
        } else {
            SkuInfo skuInfo = baseMapper.selectById(skuId);
            skuInfo.setPublishStatus(0);
            baseMapper.updateById(skuInfo);
            // 商品下架 后续会完善：发送mq消息更新es数据
            rabbitService.sendMsg(MqConst.EXCHANGE_GOODS_DIRECT, MqConst.ROUTING_GOODS_LOWER, skuId);
        }
    }

    @Override
    public void isNewPerson(Long skuId, Integer status) {
        SkuInfo skuInfo = baseMapper.selectById(skuId);
        skuInfo.setIsNewPerson(status);
        baseMapper.updateById(skuInfo);
    }

    /**
     * 删除数据库时同时删除ES中的数据
     *
     * @param id
     * @return
     */
    @Override
    public boolean removeWithES(Long id) {
        Integer publishStatus = baseMapper.selectById(id).getPublishStatus();
        if (publishStatus==0){
            baseMapper.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<SkuInfo> findSkuList(List<Long> skuIdList) {
        return baseMapper.selectBatchIds(skuIdList);
    }

    @Override
    public List<SkuInfo> selectIsNewPersonSkuList() {
        LambdaQueryWrapper<SkuInfo>  queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SkuInfo::getIsNewPerson, 1);
        queryWrapper.eq(SkuInfo::getPublishStatus,1);
        queryWrapper.orderByDesc(SkuInfo::getStock);
        //只展示库存前三个商品
        List<SkuInfo> skuInfoList = baseMapper.selectList(queryWrapper).subList(0,3);
        return skuInfoList;
    }

    @Override
    public List<SkuInfo> findByKeyword(String keyword) {
        return baseMapper.selectList(
                new LambdaQueryWrapper<SkuInfo>().like(SkuInfo::getSkuName, keyword)
        );
    }


    /**
     * 更新时进行数据库回显
     * @param skuId
     * @return
     */
    private SkuInfoVo getSkuInfoDB(Long skuId) {
        SkuInfoVo skuInfoVo = new SkuInfoVo();

        //根据id查询基本信息
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        //skuImagesService  skuPosterService  skuAttrValueService分别添加方法
        //根据id查询图片商品列表
        List<SkuImage> skuImageList = skuImagesService.findBySkuId(skuId);
        //根据id查询商品海报
        List<SkuPoster> skuPosterList = skuPosterService.findBySkuId(skuId);
        //根据id查询商品属性信息列表
        List<SkuAttrValue> skuAttrValueList = skuAttrValueService.findBySkuId(skuId);

        BeanUtils.copyProperties(skuInfo, skuInfoVo);
        skuInfoVo.setSkuImagesList(skuImageList).setSkuPosterList(skuPosterList).setSkuAttrValueList(skuAttrValueList);
        return skuInfoVo;
    }
}

