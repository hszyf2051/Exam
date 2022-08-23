package com.yif.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yif.entity.Project;

/**
 * @author yif
 */
public interface ProjectService extends IService<Project> {

    Page<Project> findProjectByTitle(Integer pageNo, Integer pageSize, String title);
}
