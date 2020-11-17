package com.wizz.gift.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/3 15:56
 */
@Repository
@Mapper
public interface TokenMapper {

    /**
     * 将生成的token插入数据库
     *
     * @param userId
     * @param token
     * @return
     */
    int insertToken(Integer userId, String token);

    /**
     * 根据userId查找token
     * @param userId
     * @return
     */
    String findTokenByUserId(Integer userId);

}
