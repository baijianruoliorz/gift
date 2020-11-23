package com.wizz.gift.mapper;

import com.wizz.gift.entity.model.MessageRecordDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author liqiqi_tql
 * @date 2020/11/23 -11:36
 */
@Mapper
@Repository
public interface MessageRecordMapper {
    int addMessageRecord(MessageRecordDo messageRecordDo);
}
