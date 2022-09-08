package com.yif.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yif.entity.User;
import com.yif.vo.LoginUserVo;
import com.yif.vo.params.Result;


/**
 * @author Yif
 */
public interface UserService extends IService<User> {
    /**
     * 登录
     *
     * @param user
     * @return
     */
    LoginUserVo login(User user);

    /**
     * 注册
     *
     * @param user
     * @return
     * @throws RuntimeException
     */
    boolean register(User user) throws RuntimeException;

    /**
     * 登出 并删除用户信息
     *
     * @return
     */
    Result logout();
}
