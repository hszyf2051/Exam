package com.yif.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yif.entity.LoginUser;
import com.yif.entity.User;
import com.yif.mapper.UserMapper;
import com.yif.service.LoginService;
import com.yif.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

/**
 * @author Yif
 */
@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @PostMapping("/login")
    public String login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        } else {
            // 获取userId 生成token
            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            String userId = loginUser.getUser().getId().toString();
            String jwt = JwtUtil.createJWT(userId);
            // 把用户信息存入redis

            // 把token和userInfo封装 返回
        }
        return null;
    }
}
