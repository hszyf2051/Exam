package com.yif.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yif.entity.Project;
import com.yif.mapper.ProjectMapper;
import com.yif.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Page<Project> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Project> lqw = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(title)) {
            // 模糊查询题目字段
            lqw.like(Project::getTitle, title);
        } else {
            return projectMapper.selectPage(page, null);
        }
        Page<Project> pageList = projectMapper.selectPage(page, lqw);
        return pageList;
    }

    @Override
    public Boolean addProject(Project project) {
        QueryWrapper<Project> qw = new QueryWrapper<>();
        qw.eq("title",project.getTitle());
        Project project1 = projectMapper.selectOne(qw);
        // 新增的商品名称有重复 return false
        if (ObjectUtils.isNotEmpty(project1)) {
            log.info("商品名重复，请重新输入",project.getTitle());
            return false;
        } else {
            int flag = projectMapper.insert(project);
            if (flag == 1) {
                return true;
            } else {
                return false;
            }
        }

    }

    @Override
    public Boolean updateProjectById(Project project) {
        // 判断修改的商品名字是否在数据库中有存在 如果有 返回false
        QueryWrapper<Project> qw = new QueryWrapper<>();
        qw.eq("title",project.getTitle());
        List<Project> projects = projectMapper.selectList(qw);
        if (projects.size() > 1) {
            log.info("商品名重复，请重新输入",project.getTitle());
            return false;
        } else {
            // 修改商品
            projectMapper.updateById(project);
            return true;
        }
    }
}
