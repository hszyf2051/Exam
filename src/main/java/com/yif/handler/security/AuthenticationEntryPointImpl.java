package com.yif.handler.security;

import com.alibaba.fastjson.JSON;
import com.yif.utils.WebUtils;
import com.yif.vo.params.Result;
import com.yif.vo.params.ResultEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yif
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 异常信息打印
        authException.printStackTrace();
        // BadCredentialsException 用户名或密码错误
        // InsufficientAuthenticationException 身份验证权限不足
        Result result = null;
        if(authException instanceof BadCredentialsException) {
            result  = Result.fail(ResultEnum.LOGIN_ERROR);
        } else if (authException instanceof InsufficientAuthenticationException) {
            // 需要Token
            result  = Result.fail(ResultEnum.NEED_LOGIN);
        } else {
            result  = Result.fail(ResultEnum.AUTHENTICATE_FAILED);
        }

        // 响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
