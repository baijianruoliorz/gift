package com.wizz.gift.controller;


import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;
import com.wizz.gift.annotation.PassToken;
import com.wizz.gift.commonUtils.R;
import com.wizz.gift.entity.Gift;
import com.wizz.gift.entity.User;
import com.wizz.gift.exceptionhandler.GuliException;
import com.wizz.gift.mapper.GiftMapper;
import com.wizz.gift.mapper.UserMapper;
import com.wizz.gift.service.GiftService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.Topic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import sun.net.www.content.image.gif;

import javax.servlet.http.HttpServletRequest;
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

    @PassToken
    @GetMapping("/selectById")
    public R selectById(){
        List<Gift> giftList = giftMapper.selectList(new QueryWrapper<Gift>().orderByAsc("id"));
        return R.ok().data("giftList",giftList);
    }
    @PassToken
    @ApiOperation(value = "普通分页")
    @GetMapping("getAllPages/{current}/{limit}")
    public R pageListGift(@PathVariable long current,
                          @PathVariable long limit){
        Page<Gift> giftPages = new Page<>(current, limit);
        giftService.page(giftPages,null);
        long total = giftPages.getTotal();
        List<Gift> records = giftPages.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
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




}

