package com.wizz.gift.mapper;

import com.wizz.gift.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     * @param openid
     * @return
     */
    int InsertUser(String openid);



    /**
     * 以用户名和密码查询用户
     * @param name
     * @param password
     * @return 存在返回该用户对象，不存在返回null
     */
    User queryUser(@Param("name") String name, @Param("password") String password);

    /**
     * 以用户名查询用户
     * @param name
     * @return 存在该用户名返回用户对象，否则返回null
     */
    User queryUserByName(String name);

    /**
     * 插入一位用户
     * @param name
     * @param password
     */
    void insertUser(@Param("name") String name,@Param("password") String password);

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    User getUserByName(@Param("name") String name);
}
