package com.wizz.gift.controller;


import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.gift.annotation.PassToken;
import com.wizz.gift.commonUtils.R;
import com.wizz.gift.entity.Category;
import com.wizz.gift.entity.Gift;
import com.wizz.gift.entity.Subcategory;
import com.wizz.gift.entity.User;
import com.wizz.gift.exceptionhandler.GuliException;
import com.wizz.gift.mapper.CategoryMapper;
import com.wizz.gift.mapper.GiftMapper;
import com.wizz.gift.mapper.SubcategoryMapper;
import com.wizz.gift.mapper.UserMapper;
import com.wizz.gift.service.CategoryService;
import com.wizz.gift.service.GiftService;
import com.wizz.gift.service.SubcategoryService;
import com.wizz.gift.service.UserService;
import com.wizz.gift.utils.RandomInitial;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author czr
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/sub")
public class SubcategoryController {
    @Autowired
    private SubcategoryMapper subcategoryMapper;
    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private GiftService giftService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PassToken
    @GetMapping("{categoryId}")
    // @Cacheable(key = "'list'",value = "lists")
    //    根据主分类id查找子分类
//    根据子分类cid查找礼物在giftcontroller
    public R selectById(@PathVariable int id) {
        List<Subcategory> subList = subcategoryMapper.selectList(new QueryWrapper<Subcategory>()
                .eq("id", id));
        return R.ok().data("subList", subList);
    }

//    获取所有的分类
    @PassToken
    @GetMapping("/allsub")
    // @Cacheable(key = "'list'",value = "lists")
    //    根据主分类查找子分类
    public R getAllcate() {
        List<Category> categoryList=categoryMapper.selectList(new QueryWrapper<Category>().orderByDesc("id"));
        List<Map<String,Object>> allList=new ArrayList<>();
        for(Category category:categoryList){
            Map<String,Object> categorymap=new HashMap<>();
            int id=category.getId();
            categorymap.put("id",id);
            categorymap.put("name",category.getName());
            List<Subcategory> subcategoryListList=subcategoryMapper.selectList(new QueryWrapper<Subcategory>().eq("id",id).orderByDesc("cid"));
            List<Map<String,Object>> subList=new ArrayList<>();
            for(Subcategory subcategory:subcategoryListList){
                Map<String,Object> submap=new HashMap<>();
                int cid=subcategory.getCid();
                submap.put("cid",cid);
                submap.put("name",subcategory.getName());
                List<Gift> giftList=giftMapper.selectList(new QueryWrapper<Gift>().eq("cid",cid));
                List<Map<String,Object>> itemList=new ArrayList<>();
                for (Gift gift:giftList){
                    Map<String,Object> giftmap=new HashMap<>();
                    giftmap.put("id",gift.getId());
                    giftmap.put("title",gift.getTitle());
                    giftmap.put("url",gift.getUrl());
                    itemList.add(giftmap);
                }
                submap.put("branch",itemList);
                subList.add(submap);
            }
            categorymap.put("branch",subList);
            allList.add(categorymap);
        }
        return R.ok().data("allList", allList);
    }


//    @GetMapping("/categories")
//    @Cacheable(key = "'list'",value = "lists")
//    public R list(){
//        List<Category> list = categoryService.list(null);
//        return R.ok().data("list",list);
//    }
    @PostMapping("addCategory")
    public R addSubCategory(@RequestBody Subcategory subcategory){
        boolean save = subcategoryService.save(subcategory);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("{categoryId}")
    public R deleteSubCategory(@PathVariable int categoryId){
        boolean b = subcategoryService.removeById(categoryId);
        if (!b){
            throw new GuliException(20001,"删除失败了,可能是空值.");
        }
        return R.ok();
    }
//    // @Cacheable(key = "'list'",value = "lists")
//    @PassToken
//    @ApiOperation(value = "根据分类来查询礼物,不需要token")
//    @GetMapping("selectByCid/{cid}")
//    public R selectByCid(@PathVariable int cid) {
//        List<Gift> cid1 = giftMapper.selectList(new QueryWrapper<Gift>()
//                .eq("cid", cid));
//        return R.ok().data("giftList", cid1);
//
//    }
//
//    //    @Cacheable(key="giftList")
//    @PassToken
//    @ApiOperation(value = "根据分类来分页,不需要token")
//    @GetMapping("getAllPages/{current}/{limit}/{cid}")
//    public R pageListGift(@PathVariable long current,
//                          @PathVariable long limit,
//                          @PathVariable long cid) {
//        Page<Gift> giftPages = new Page<>(current, limit);
//        giftService.page(giftPages, new QueryWrapper<Gift>().eq("cid", cid));
//        long total = giftPages.getTotal();
//        List<Gift> records = giftPages.getRecords();
//        return R.ok().data("total", total).data("rows", records);
//    }
//
//    @PassToken
//    @ApiOperation(value = "普通的分页,不需要token")
//    @GetMapping("getAllPages/{current}/{limit}")
//    public R pageListGifts(@PathVariable long current,
//                           @PathVariable long limit
//    ) {
//        Page<Gift> giftPages = new Page<>(current, limit);
//        giftService.page(giftPages, null);
//        long total = giftPages.getTotal();
//        List<Gift> records = giftPages.getRecords();
//        return R.ok().data("total", total).data("rows", records);
//    }
//
//
//    //@CacheEvict(allEntries=true)
//    @PostMapping("addGift")
//    public R addGift(@RequestBody Gift gift, HttpServletRequest request) {
////        获取header里的token
//        String token = request.getHeader("token");
////        获取token里的userID
//        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
//        User userByUserId = userMapper.findUserByUserId(userId);
//        if (userByUserId == null) {
//            throw new GuliException(20000, "用户未登录或者未保存至数据库!");
//        }
//        gift.setUid(userByUserId.getId());
//        giftService.save(gift);
//        return R.ok().message("添加成功!");
//    }
//
//    @PostMapping("updateGift/{id}")
//    public R updateGift(@PathVariable int id, @RequestBody Gift gift, HttpServletRequest request) {
////获取header里的token
//        String token = request.getHeader("token");
////        获取token里的userID
//        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
//        User userByUserId = userMapper.findUserByUserId(userId);
//        if (userByUserId == null) {
//            throw new GuliException(20000, "用户未登录或者未保存至数据库!");
//        }
//        Gift byId = giftService.getById(id);
//        if (byId.getUid().equals(userByUserId.getId())) {
//            giftService.updateById(gift);
//        } else {
//            return R.error().message("只能修改自己的礼物哦");
//        }
//        return R.ok();
//    }

}
