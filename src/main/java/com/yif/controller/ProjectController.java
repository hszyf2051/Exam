package com.yif.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yif.entity.Project;
import com.yif.entity.Question;
import com.yif.service.ProjectService;
import com.yif.utils.ResultUtil;
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
@Api(tags = "商品")
@RestController
@RequestMapping("/product")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @ApiOperation(value = "查询所有物品")
    @GetMapping("/findProject")
    public Result findProject(Project project,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page page = new Page<>(pageNo, pageSize);
        Page pageData = projectService.page(page);
        return ResultUtil.success(pageData);
    }


    /**
     * 根据输入的字段查询所有问题
     * @param title
     * @return
     */
    @ApiOperation(value = "根据输入的字段查询所有问题")
    @GetMapping("/findProjectByTitle")
    public Result<Page<Project>> findProjectByTitle(@RequestParam String title,
                                                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<Project> page = new Page<>(pageNo,pageSize);
        Page<Project> projectByTitle = projectService.findProjectByTitle(pageNo, pageSize, title);
        return ResultUtil.success(projectByTitle);
    }
}
