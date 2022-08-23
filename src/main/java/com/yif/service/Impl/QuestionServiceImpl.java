package com.yif.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yif.entity.Question;
import com.yif.mapper.QuestionMapper;
import com.yif.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yif
 */
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public List<Question> selectList(String content) {
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(content)) {
            // 模糊查询题目字段
            lqw.like(Question::getContent,content);
        }
        List<Question> questionList = questionMapper.selectList(lqw);
        return questionList;
    }

    @Override
    public Page<Question> findQuestionByContent(Integer pageNo, Integer pageSize, String content) {
        Page<Question> page = new Page<>(pageNo,pageSize);
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(content)) {
            // 模糊查询题目字段
            lqw.like(Question::getContent,content);
        } else {
            return questionMapper.selectPage(page,null);
        }
        Page<Question> pageList = questionMapper.selectPage(page, lqw);
        return pageList;
    }
}
