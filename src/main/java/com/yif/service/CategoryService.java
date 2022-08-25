package com.yif.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yif.entity.Category;

import java.util.List;

/**
 * @author Yif
 */
public interface CategoryService extends IService<Category> {
    List<Category> findCategoryById(Integer id);
}
