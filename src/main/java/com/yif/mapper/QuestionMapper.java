package com.yif.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yif.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yif
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
