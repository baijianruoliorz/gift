package com.wizz.gift;

import com.wizz.gift.commonUtils.ResultCode;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching  //启动缓存
@EnableScheduling
@MapperScan("com/wizz/gift/mapper")
public class GiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftApplication.class, args);

    }

}
