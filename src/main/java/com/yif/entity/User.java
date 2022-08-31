package com.yif.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (User)实体类
 *
 * @author yif
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 主键@TableId
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}

