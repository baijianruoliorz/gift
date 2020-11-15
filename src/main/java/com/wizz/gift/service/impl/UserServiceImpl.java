package com.wizz.gift.service.impl;

import com.wizz.gift.entity.User;
import com.wizz.gift.mapper.UserMapper;
import com.wizz.gift.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-11-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
