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
public class LoginUserVo {

    private String token;

    private UserInfoVo userInfoVo;
}
