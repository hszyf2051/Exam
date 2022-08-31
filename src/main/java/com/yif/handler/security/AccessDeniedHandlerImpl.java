package com.yif.handler.security;

import com.alibaba.fastjson.JSON;
import com.yif.utils.WebUtils;
import com.yif.vo.params.Result;
import com.yif.vo.params.ResultEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yif
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 异常信息打印
        accessDeniedException.printStackTrace();
        Result result = Result.fail(ResultEnum.NO_PERMISSION);
        // 响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
