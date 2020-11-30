package com.wizz.gift.controller;

import com.wizz.gift.annotation.PassToken;
import com.wizz.gift.commonUtils.R;
import com.wizz.gift.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/gift/rank")
public class RankController {
    @Autowired
    RedisUtil redisUtil;

    @PassToken
    @GetMapping("/boy")
    public R boyrank(){
        Set<ZSetOperations.TypedTuple> boyrank=redisUtil.getZSetRank("boy_rank",0,9);
        List<Map<String,Object>> rankList=new ArrayList<>();
        for(ZSetOperations.TypedTuple typedTuple:boyrank){
            Map<String,Object> map=new HashMap<>();
            map.put("star",typedTuple.getScore());
            map.put("id",redisUtil.hget("rank_gift_"+typedTuple.getValue(),"gift:id"));
            map.put("title",redisUtil.hget("rank_gift_"+typedTuple.getValue(),"gift:title"));
            rankList.add(map);
        }
        return R.ok().data("boyRank",rankList);
    }

    @PassToken
    @GetMapping("/girl")
    public R girlrank(){
        Set<ZSetOperations.TypedTuple> girlrank=redisUtil.getZSetRank("girl_rank",0,9);
        List<Map<String,Object>> rankList=new ArrayList<>();
        for(ZSetOperations.TypedTuple typedTuple:girlrank){
            Map<String,Object> map=new HashMap<>();
            map.put("star",typedTuple.getScore());
            map.put("id",redisUtil.hget("rank_gift_"+typedTuple.getValue(),"gift:id"));
            map.put("title",redisUtil.hget("rank_gift_"+typedTuple.getValue(),"gift:title"));
            rankList.add(map);
        }
        return R.ok().data("girlRank",rankList);
    }

}
