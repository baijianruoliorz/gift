package com.wizz.gift.schedules;

import com.wizz.gift.service.RankService;
import com.wizz.gift.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RankSyncTask {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RankService rankService;

    @Scheduled(cron = "0/5 * * * * *")//每分钟同步
    public void task(){
        try{
            rankService.initRank();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("同步成功");
    }
}
