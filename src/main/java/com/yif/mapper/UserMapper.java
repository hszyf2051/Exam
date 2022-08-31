package com.yif.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yif.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yif
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
