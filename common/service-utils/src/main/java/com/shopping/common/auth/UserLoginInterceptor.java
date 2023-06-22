package com.shopping.common.auth;

import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManager;
import com.shopping.JwtUtils;
import com.shopping.common.constant.RedisConst;
import com.shopping.vo.user.UserLoginVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-22 22:29
 * @description: shopping-parent
 */
public class UserLoginInterceptor implements HandlerInterceptor {

    private RedisTemplate redisTemplate;

    public UserLoginInterceptor(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean preHandler(HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler){
        this.getUserLoginVo(request);
        return true;
    }

    private void getUserLoginVo(HttpServletRequest request) {
        //从请求中获取token值
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)){
            Long userId = JwtUtils.getUserId(token);
            UserLoginVo userLoginVo  = (UserLoginVo) redisTemplate.opsForValue().get(RedisConst.USER_LOGIN_KEY_PREFIX + userId);
            if (userLoginVo!=null){
                AuthContextHolder.setUserId(userLoginVo.getUserId());
                AuthContextHolder.setWareId(userLoginVo.getWareId());
                AuthContextHolder.setUserLoginVo(userLoginVo);
            }
        }
    }
}
