package com.yif.controller;

import com.yif.entity.User;
import com.yif.exception.SystemException;
import com.yif.service.UserService;
import com.yif.vo.LoginUserVo;
import com.yif.vo.params.Result;
import com.yif.vo.params.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yif
 */
@Api(tags = "登录管理")
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录功能")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            // 提示必须要用户名
            throw new SystemException(ResultEnum.REQUIRE_USERNAME);
        }
        LoginUserVo loginUserVo = userService.login(user);
        return Result.success(200, "登录成功！", loginUserVo);
    }

    @ApiOperation(value = "注册功能")
    @PostMapping("/register")
    public Result register(@RequestBody User user) throws RuntimeException {
        boolean register = userService.register(user);
        if (register == true) {
            return Result.success(200, "注册成功", true);
        } else {
            return Result.fail(ResultEnum.REGISTER_FAILED);
        }
    }
}
