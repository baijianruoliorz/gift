package com.wizz.gift.controller;


import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wizz.gift.annotation.PassToken;
import com.wizz.gift.commonUtils.R;
import com.wizz.gift.entity.Gift;
import com.wizz.gift.entity.User;
import com.wizz.gift.exceptionhandler.GuliException;
import com.wizz.gift.mapper.GiftMapper;
import com.wizz.gift.mapper.UserMapper;
import com.wizz.gift.service.GiftService;
import com.wizz.gift.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
@RequestMapping("/gift/gift")
public class GiftController {
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private GiftService giftService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    @PassToken
    @GetMapping("/selectById")
    @Cacheable
    public R selectById(){
        List<Gift> giftList = giftMapper.selectList(new QueryWrapper<Gift>().orderByAsc("id"));

        return R.ok().data("giftList",giftList);
    }
    @Cacheable
    @PassToken
    @ApiOperation(value = "根据分类来查询礼物,不需要token")
    @GetMapping("selectByCid/{cid}")
    public R selectByCid(@PathVariable int cid){
        List<Gift> cid1 = giftMapper.selectList(new QueryWrapper<Gift>()
                .eq("cid", cid));
        return R.ok().data("giftList",cid1);

    }

    @Cacheable
    @PassToken
    @ApiOperation(value = "根据分类来分页,不需要token")
    @GetMapping("getAllPages/{current}/{limit}/{cid}")
    public R pageListGift(@PathVariable long current,
                          @PathVariable long limit,
                          @PathVariable long cid){
        Page<Gift> giftPages = new Page<>(current, limit);
        giftService.page(giftPages,new QueryWrapper<Gift>().eq("cid",cid));
        long total = giftPages.getTotal();
        List<Gift> records = giftPages.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
    @PassToken
    @ApiOperation(value = "普通的分页,不需要token")
    @GetMapping("getAllPages/{current}/{limit}")
    public R pageListGifts(@PathVariable long current,
                          @PathVariable long limit
                         ){
        Page<Gift> giftPages = new Page<>(current, limit);
        giftService.page(giftPages,null);
        long total = giftPages.getTotal();
        List<Gift> records = giftPages.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }




    @CacheEvict
    @PostMapping("addGift")
    public R addGift(@RequestBody Gift gift, HttpServletRequest request){
//        获取header里的token
        String token = request.getHeader("token");
//        获取token里的userID
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        User userByUserId = userMapper.findUserByUserId(userId);
        if (userByUserId==null){
            throw new GuliException(20000,"用户未登录或者未保存至数据库!");
        }
        gift.setUid(userByUserId.getId());
        giftService.save(gift);
        return R.ok().message("添加成功!");
    }

    @CacheEvict
    @DeleteMapping("deleteGift/{id}")
    public R deleteGift(@PathVariable int id,HttpServletRequest request) {
        //        获取header里的token
        String token = request.getHeader("token");
//        获取token里的userID
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        User userByUserId = userMapper.findUserByUserId(userId);
        if (userByUserId == null) {
            throw new GuliException(20000, "用户未登录或者未保存至数据库!");
        }
        Gift byId = giftService.getById(id);
        if (byId.getUid().equals(userByUserId.getId())){
            giftService.removeById(id);
            return R.ok().message("删除成功");
        }
        return R.error().message("只能删除自己的礼物哦~");
    }

    @PostMapping("updateGift/{id}")
    public R updateGift(@PathVariable int id,@RequestBody Gift gift,HttpServletRequest request){
        //        获取header里的token
        String token = request.getHeader("token");
//        获取token里的userID
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        User userByUserId = userMapper.findUserByUserId(userId);
        if (userByUserId == null) {
            throw new GuliException(20000, "用户未登录或者未保存至数据库!");
        }
        Gift byId = giftService.getById(id);
        if (byId.getUid().equals(userByUserId.getId())) {
         giftService.updateById(gift);
        }else {
            return R.error().message("只能修改自己的礼物哦");
        }
        return R.ok();
        }

    @PutMapping("boylike/{id}")
    public R boylike(@PathVariable int id,HttpServletRequest request){
        String token = request.getHeader("token");
//        获取token里的userID
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        User userByUserId = userMapper.findUserByUserId(userId);
        if (userByUserId == null) {
            throw new GuliException(20000, "用户未登录或者未保存至数据库!");
        }
        Gift byId = giftService.getById(id);
        byId.setBoylike(byId.getBoylike()+1);
        int progressDemo = 0;
        if (byId.getBoylike()+byId.getGirllike()!=0) {
            progressDemo = byId.getBoylike() * 100 / (byId.getBoylike() + byId.getGirllike());
            byId.setProgress(progressDemo);
        }else {
            byId.setProgress(0);
        }
        giftService.updateById(byId);
        return R.ok().message("男孩喜欢了这个礼物!现在的Progress是:"+progressDemo);
    }
    @PutMapping("girllike/{id}")
    public R girllike(@PathVariable int id,HttpServletRequest request){
        String token = request.getHeader("token");
//        获取token里的userID
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        User userByUserId = userMapper.findUserByUserId(userId);
        if (userByUserId == null) {
            throw new GuliException(20000, "用户未登录或者未保存至数据库!");
        }
        Gift byId = giftService.getById(id);
        byId.setGirllike(byId.getGirllike()+1);
        int progressDemo=0;
        if (byId.getBoylike()+byId.getGirllike()!=0) {
            progressDemo = byId.getBoylike() * 100 / (byId.getBoylike() + byId.getGirllike());
            byId.setProgress(progressDemo);
        }else {
            byId.setProgress(0);
        }
        giftService.updateById(byId);
        return R.ok().message("女孩喜欢了这个礼物!现在的Process是:"+progressDemo);
    }

//    收藏功能的实现
    @GetMapping("/collectGift/{id}")
    public R collectGift(@PathVariable int id,HttpServletRequest request){
        String token = request.getHeader("token");
//        获取token里的userID
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        User userByUserId = userMapper.findUserByUserId(userId);
        if (userByUserId == null) {
            throw new GuliException(20000, "用户未登录或者未保存至数据库!");
        }
        String s = String.valueOf(id);
        String pid = userByUserId.getPid();
        if (pid==null){
            pid="0";
        }
        if (pid.contains(s)){
            return R.error().message("已经收藏过了,不能重复收藏");
        }
        pid=pid+","+s;
        userByUserId.setPid(pid);
        userService.updateById(userByUserId);
        return R.ok().message("收藏成功!");
    }
    @GetMapping("/selectCollectGift")
    public R selectCollection(HttpServletRequest request)throws ConcurrentModificationException {
        String token = request.getHeader("token");
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        User userByUserId = userMapper.findUserByUserId(userId);
        if (userByUserId==null){
            throw new GuliException(20000,"用户未登录或未保存至数据库!");
        }
//        不加这个会报并发错误
        if (userByUserId.getGiftList()!=null){
            userByUserId.setGiftList(null);
        }
        List<Gift> giftList = giftMapper.selectList(null);
        for (Gift gift : giftList) {
            String s = String.valueOf(gift.getId());
            String pid = userByUserId.getPid();
            if (pid==null){
                pid="0";
            }
            if (pid.contains(s)){
                List<Gift> giftList1 = userByUserId.getGiftList();
                if (giftList1==null){
                    giftList1=new ArrayList<Gift>();
                }
                giftList1.add(gift);
                userByUserId.setGiftList(giftList1);
            }
        }
        return R.ok().data("user",userByUserId);
    }

    }






