package com.wizz.gift.service;

import com.wizz.gift.Info.TokenPO;
import com.wizz.gift.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.gift.entity.model.LoginInfoDo;
import com.wizz.gift.entity.model.MessageRecordDo;
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

    /**
     * 验证用户密码
     * @param name
     * @param password
     * @return 正确返回该用户对象，否则返回空
     */
    public User validateUserPassword(String name, String password);

    /**
     * 该用户是否已经注册
     * @param name
     * @return
     */
    public boolean isExistUser(String name);

    /**
     * 插入一名用户
     * @param name
     * @param password
     */
    public void insertUser(String name, String password);

    public void addUserLoginInfo(LoginInfoDo loginInfoDo);

    public void addUserMessageRecord(MessageRecordDo messageRecordDo);

    public User getUserByName(String name);
}
