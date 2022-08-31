package com.yif.filter;

import com.alibaba.fastjson.JSON;
import com.yif.entity.LoginUser;
import com.yif.utils.JwtUtil;
import com.yif.utils.RedisCache;
import com.yif.utils.WebUtils;
import com.yif.vo.params.Result;
import com.yif.vo.params.ResultEnum;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author yif
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            // 说明该接口不需要登录  直接放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析获取userid
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            // token超时  token非法
            // 响应告诉前端需要重新登录
            Result result = Result.fail(ResultEnum.TOKEN_ILLEGAL);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userId = claims.getSubject();
        // 从redis中获取用户信息
        Object cacheObject = redisCache.getCacheObject("loginUser:" + userId);
        // 对象 转JSON
        String jsonString = JSON.toJSONString(cacheObject);
        // JSON 转对象
        LoginUser loginUser = JSON.parseObject(jsonString, LoginUser.class);
        // 如果获取不到
        if(Objects.isNull(loginUser)){
            // 说明登录过期  提示重新登录
            Result result = Result.fail(ResultEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        // 存入SecurityContextHolder
        // 认证过状态
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }


}