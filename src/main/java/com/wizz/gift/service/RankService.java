package com.wizz.gift.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.gift.entity.Gift;

public interface RankService extends IService<Gift> {
    void initRank();
    void putViewCount();
}
