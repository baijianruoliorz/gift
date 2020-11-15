package com.wizz.gift.mapper;

import com.wizz.gift.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-11-15
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据UserId查找用户
     * @param userId
     * @return
     */
    User findUserByUserId(Integer userId);

    /**
     * 根据微信返回的openId查找用户
     * @param openid
     * @return
     */
    User findUserByOpenId(String openid);


    /**
     * 新增用户,并返回数据库递增生成的userid
     * @param user
     * @return
     */
    int InsertUser(User user);
}
