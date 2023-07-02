package com.shopping.common.auth;

import cn.hutool.core.bean.BeanUtil;
import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManager;
import com.shopping.JwtUtils;
import com.shopping.common.constant.RedisConst;
import com.shopping.vo.user.UserLoginVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-22 22:29
 * @description: shopping-parent
 */
public class UserLoginInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public UserLoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public boolean preHandler(HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            response.setStatus(401);
            return false;
        }
        Long userId = JwtUtils.getUserId(token);
        Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(RedisConst.USER_LOGIN_KEY_PREFIX + userId);
        if (map.isEmpty()) {
            response.setStatus(401);
            return false;
        }
        UserLoginVo userLoginVo = BeanUtil.fillBeanWithMap(map, UserLoginVo.builder().build(), false);
        AuthContextHolder.setUserId(userLoginVo.getUserId());
        AuthContextHolder.setWareId(userLoginVo.getWareId());
        AuthContextHolder.setUserLoginVo(userLoginVo);
        return true;
    }
}




