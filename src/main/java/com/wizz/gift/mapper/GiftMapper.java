package com.wizz.gift.mapper;

import com.wizz.gift.entity.Gift;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-11-15
 */
@Repository
@Mapper
public interface GiftMapper extends BaseMapper<Gift> {

    int getTotalNum();
}
