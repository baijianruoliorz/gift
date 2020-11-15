package com.wizz.gift.service;

import com.wizz.gift.Info.TokenPO;
import com.wizz.gift.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.gift.utils.response.UniversalResponseBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-11-15
 */
public interface UserService extends IService<User> {
//wx小程序登录接口
    UniversalResponseBody<TokenPO> userWxLogin(String code);
}
