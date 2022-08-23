package com.yif.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yif.entity.Project;
import com.yif.entity.Question;
import com.yif.mapper.ProjectMapper;
import com.yif.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yif
 */
@Service
@Slf4j
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public Page<Project> findProjectByTitle(Integer pageNo, Integer pageSize, String title) {
        Page<Project> page = new Page<>(pageNo,pageSize);
        LambdaQueryWrapper<Project> lqw = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(title)) {
            // 模糊查询题目字段
            lqw.like(Project::getTitle,title);
        } else {
            return projectMapper.selectPage(page,null);
        }
        Page<Project> pageList = projectMapper.selectPage(page, lqw);
        return pageList;
    }
}
