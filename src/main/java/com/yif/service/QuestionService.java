package com.yif.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yif.entity.Question;

import java.util.List;

/**
 * @author Yif
 */
public interface QuestionService extends IService<Question> {
    List<Question> selectList(String content);

    Page<Question> findQuestionByContent(Integer pageNo, Integer pageSize,String content);
}
