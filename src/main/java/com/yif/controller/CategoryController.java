package com.yif.controller;

import com.yif.entity.Category;
import com.yif.service.CategoryService;
import com.yif.vo.params.Result;
import com.yif.vo.params.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yif
 */
@Api(tags = "商品类别")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "根据id查询类别")
    @GetMapping("/findCategoryById")
    public Result findCategoryById(@RequestParam Integer id) {
        List<Category> categoryList = categoryService.findCategoryById(id);
        if (categoryList.isEmpty()) {
            return Result.fail(ResultEnum.CATEGORY_NOT_EXIST);
        } else {
            return Result.success(categoryList);
        }
    }
}
