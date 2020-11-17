package com.wizz.gift.controller;


import com.wizz.gift.commonUtils.R;
import com.wizz.gift.entity.Category;
import com.wizz.gift.exceptionhandler.GuliException;
import com.wizz.gift.mapper.CategoryMapper;
import com.wizz.gift.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-11-15
 */
@RestController
@RequestMapping("/gift/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/categories")
    @Cacheable(key = "'list'",value = "lists")
    public R list(){
        List<Category> list = categoryService.list(null);
        return R.ok().data("list",list);
    }
    @PostMapping("addCategory")
    public R addCategory(@RequestBody Category category){
        boolean save = categoryService.save(category);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("{categoryId}")
    public R deleteCategory(@PathVariable int categoryId){
        boolean b = categoryService.removeById(categoryId);
        if (!b){
            throw new GuliException(20001,"删除失败了,可能是空值.");
        }
        return R.ok();
    }
}

