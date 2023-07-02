package com.shopping.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.JwtUtils;
import com.shopping.common.constant.RedisConst;
import com.shopping.common.exception.shoppingException;
import com.shopping.common.result.ResultCodeEnum;
import com.shopping.enums.UserType;
import com.shopping.mapper.LeaderMapper;
import com.shopping.mapper.UserDeliveryMapper;
import com.shopping.mapper.UserMapper;
import com.shopping.model.user.Leader;
import com.shopping.model.user.User;
import com.shopping.model.user.UserDelivery;
import com.shopping.service.UserService;
import com.shopping.utils.HttpClientUtils;
import com.shopping.utils.WeChatProperties;
import com.shopping.vo.user.LeaderAddressVo;
import com.shopping.vo.user.UserLoginVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-22 19:28
 * @description: shopping-parent
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private WeChatProperties weChatProperties;
    @Resource
    private LeaderMapper leaderMapper;
    @Resource
    private UserDeliveryMapper userDeliveryMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Map<String, Object> login(String code) {
        //1.得到微信返回的code临时票据
        String weChatsAppId = weChatProperties.getApp_id();
        //2.拿着code+小程序id+小程序秘钥 请求微信接口
        String weChatAppSecret = weChatProperties.getApp_secret();
        StringBuffer url = new StringBuffer()
                .append("https://api.weixin.qq.com/sns/jscode2session")
                .append("?appid=%s")
                .append("&secret=%s")
                .append("&js_code=%s")
                .append("&grant_type=authorization_code");
        String tokenUrl = String.format(url.toString(), weChatsAppId, weChatAppSecret, code);
        String result = null;
        try {
            result = HttpClientUtils.get(tokenUrl);
        } catch (Exception e) {
            throw new shoppingException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }
        //3.请求微信接口服务，返回session——key和openId，openid是微信唯一标识
        //get请求，拼接地址和参数.
        JSONObject jsonObject = JSONObject.parseObject(result);
        String sessionKey = jsonObject.getString("session_key");
        String openid = jsonObject.getString("openid");
        //4.第一次使用微信授权登录，则添加用户信息到数据库
        if (userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getOpenId,openid)) == null) {
            User setuser = User.builder().openId(openid).nickName(openid).photoUrl("")
                    .userType(UserType.USER.getCode()).isNew(0).build();
            userMapper.insert(setuser);
        }
         User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getOpenId, openid));
        //5.根据userId查询提货点和团长信息
        LeaderAddressVo leaderAddressVo = this.getLeaderAddressByUserId(user.getId());
        //6.使用JWT工具生成字符串
        String token = JwtUtils.createToken(user.getId(), user.getNickName());
        //7.获取当前登录的用户信息，放到redis中，设置有效时间
        UserLoginVo userLoginVo = this.getUserLoginVo(user.getId());
        Map<String,Object> mapVo = BeanUtil.beanToMap(userLoginVo,new HashMap<>(),
                                                    CopyOptions.create().setIgnoreNullValue(true)
                                                            .setFieldValueEditor((name,value)->value.toString()));
        stringRedisTemplate.opsForHash().putAll(RedisConst.USER_LOGIN_KEY_PREFIX+user.getId(),mapVo);
        stringRedisTemplate.expire(RedisConst.USER_LOGIN_KEY_PREFIX+user.getId(),RedisConst.USERKEY_TIMEOUT, TimeUnit.DAYS);
        //8.把数据封装进行返回
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        map.put("token",token);
        map.put("leaderAddressVo",leaderAddressVo);
        return map;
    }

    private UserLoginVo getUserLoginVo(Long id) {
        User user = userMapper.selectById(id);
        UserLoginVo userLoginVo = UserLoginVo.builder().userId(id).nickName(user.getNickName())
                .photoUrl(user.getPhotoUrl()).isNew(user.getIsNew()).openId(user.getOpenId()).build();
        UserDelivery userDelivery = userDeliveryMapper.selectOne(
                new LambdaQueryWrapper<UserDelivery>().eq(UserDelivery::getUserId, id)
                        .eq(UserDelivery::getIsDefault,1)
        );
        if (userDelivery!=null){
            userLoginVo.setLeaderId(userDelivery.getLeaderId()).setWareId(userLoginVo.getWareId());
        }else {
            userLoginVo.setLeaderId(1L).setWareId(1L);
        }
        return userLoginVo;
    }

    @Override
    public LeaderAddressVo getLeaderAddressByUserId(Long userId){
        UserDelivery userDelivery = userDeliveryMapper.selectOne(
                new LambdaQueryWrapper<UserDelivery>().eq(UserDelivery::getUserId, userId)
                        .eq(UserDelivery::getIsDefault, 1)
        );
        if (userDelivery==null){
            return null;
        }
        Leader leader = leaderMapper.selectById(userDelivery.getLeaderId());
        LeaderAddressVo leaderAddressVo = LeaderAddressVo.builder().build();
        BeanUtils.copyProperties(leader, leaderAddressVo);
        //给vo赋值传回去
        leaderAddressVo.setUserId(userId).setLeaderId(leader.getId())
        .setLeaderName(leader.getName()).setLeaderPhone(leader.getPhone())
        .setWareId(userDelivery.getWareId()).setStorePath(leader.getStorePath());
        return leaderAddressVo;
    }
}
