package com.wizz.gift.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.gift.entity.Gift;
import com.wizz.gift.mapper.GiftMapper;
import com.wizz.gift.service.RankService;
import com.wizz.gift.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RankServiceImpl extends ServiceImpl<GiftMapper, Gift> implements RankService {
    @Autowired
    GiftMapper giftMapper;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public void initRank(){
        List<Gift> gifts=this.list(new QueryWrapper<Gift>().isNotNull("status"));
        for(Gift gift : gifts){
            String Boykey="boy_rank";
            String GirlKey="girl_rank";
            redisUtil.zSet(Boykey,gift.getId(),gift.getBoylike());
            redisUtil.zSet(GirlKey,gift.getId(),gift.getGirllike());
            this.hashCacheGiftIdAndTitle(gift);
        }
    }
    private  void hashCacheGiftIdAndTitle(Gift gift){
        boolean isExist=redisUtil.hasKey("rank_gift_"+gift.getId());
        if(!isExist){
            redisUtil.hset("rank_gift_"+gift.getId(),"gift:id",gift.getId());
            redisUtil.hset("rank_gift_"+gift.getId(),"gift:title",gift.getTitle());
            redisUtil.hset("rank_gift_"+gift.getId(),"gift:url",gift.getUrl());
        }
    }
    @Override
    public void putViewCount(){

    }
}
