package com.yif.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yif.entity.User;

/**
 * @author Yif
 */
public interface LoginService extends IService<User> {
    String login(User user);
}
