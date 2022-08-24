package com.yif.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yif.entity.Question;
import com.yif.service.QuestionService;
import com.yif.vo.params.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yif
 */
@Api(tags = "问题测试")
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @ApiOperation(value = "查询所有问题")
    @GetMapping("/findQuestion")
    public Result findQuestion(Question question,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page page = new Page<>(pageNo, pageSize);
        Page pageData = questionService.page(page);
        return Result.success(pageData);
    }

    /**
     * 根据输入的字段查询所有问题
     *
     * @param content
     * @return
     */
    @ApiOperation(value = "根据输入的字段查询所有问题")
    @GetMapping("/findQuestionByContent")
    public Result<Page<Question>> findQuestionByContent(@RequestParam String content,
                                                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<Question> page = new Page<>(pageNo, pageSize);
        Page<Question> questionByContent = questionService.findQuestionByContent(pageNo, pageSize, content);
        return Result.success(questionByContent);
    }
}
