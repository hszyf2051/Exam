package com.yif.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yif
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {

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
