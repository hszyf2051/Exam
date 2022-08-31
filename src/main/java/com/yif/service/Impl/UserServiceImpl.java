package com.yif.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yif.entity.LoginUser;
import com.yif.entity.User;
import com.yif.mapper.UserMapper;
import com.yif.service.UserService;
import com.yif.utils.BeanCopyUtils;
import com.yif.utils.JwtUtil;
import com.yif.utils.RedisCache;
import com.yif.vo.LoginUserVo;
import com.yif.vo.UserInfoVo;
import com.yif.vo.params.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.SystemException;
import java.util.Objects;

/**
 * @author Yif
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginUserVo login(User user) {
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
            redisCache.setCacheObject("loginUser:" + userId, loginUser);
            // 把token和userInfo封装 返回
            // 把User转化为UserInfo对象
            UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
            LoginUserVo loginUserVo = new LoginUserVo(jwt, userInfoVo);
            return loginUserVo;
        }
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean register(User user) throws SystemException {
        // 对数据进行非空判断
        if (StringUtils.isEmpty(user.getUsername())) {
            log.info(ResultEnum.ACCOUNT_USERNAME_NOT_EXIST.getMsg());
            return false;
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            log.info(ResultEnum.ACCOUNT_PWD_NOT_EXIST.getMsg());
            return false;
        }
        // 对数据进行是否存在的判断
        if (userNameExist(user.getUsername())) {
            log.info(ResultEnum.ACCOUNT_EXIST.getMsg());
            return false;
        }
        // 对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.insert(user);
        // 存入数据库
        return true;
    }

    private boolean userNameExist(String username) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, username);
        // 查询到数据
        return count(lqw) > 0;
    }
}
