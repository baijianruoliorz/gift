package com.wizz.gift.service.impl;

import com.wizz.gift.Info.TokenPO;
import com.wizz.gift.Info.WxResponseInfo;
import com.wizz.gift.entity.User;
import com.wizz.gift.entity.model.LoginInfoDo;
import com.wizz.gift.entity.model.MessageRecordDo;
import com.wizz.gift.mapper.LoginInfoMapper;
import com.wizz.gift.mapper.MessageRecordMapper;
import com.wizz.gift.mapper.TokenMapper;
import com.wizz.gift.mapper.UserMapper;
import com.wizz.gift.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.gift.utils.Enum.ResponseResultEnum;
import com.wizz.gift.utils.Tokenutil;
import com.wizz.gift.utils.WeChatUtil;
import com.wizz.gift.utils.response.UniversalResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-11-15
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Resource
    private WeChatUtil weChatUtil;
    @Resource
    private Tokenutil tokenutil;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TokenMapper tokenMapper;
    @Autowired
    private MessageRecordMapper messageRecordMapper;
    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @Override
    public UniversalResponseBody<TokenPO> userWxLogin(String code){
        WxResponseInfo wxResponseInfo = weChatUtil.getWeChatResponseBody(code);

        //Code无效
        if (wxResponseInfo.getErrcode()!=null){
            //将微信返回错误代码及结果记录到日志中
            log.error("微信登录出错"+code+wxResponseInfo.getErrcode()+"\t"+ wxResponseInfo.getErrmsg());
            return new UniversalResponseBody(ResponseResultEnum.CODE_IS_INVALID.getCode(),ResponseResultEnum.CODE_IS_INVALID.getMsg());
        }
        String token = null;
        TokenPO tokenPO = null;
        //该用户在数据库中的数据
        log.info(wxResponseInfo.getOpenid());
        User user = userMapper.findUserByOpenId(wxResponseInfo.getOpenid());
        //数据库中已经存在该用户
        if(user!=null) {
            token = tokenMapper.findTokenByUserId(user.getId());
            log.info(user.toString());
            log.info("token为"+token+"\t");
            log.info("sada");
            token = tokenutil.TokenByUserId(user.getId());
            user.setToken(token);

            tokenPO = new TokenPO(user, token);
            return new UniversalResponseBody<TokenPO>(ResponseResultEnum.USER_HAVE_EXIST.getCode(),ResponseResultEnum.USER_HAVE_EXIST.getMsg(),tokenPO);
        }else {
            //插入用户
            user = new User(wxResponseInfo.getOpenid());
            userMapper.InsertUser(user.getOpenid());
            //根据userId生成token
            token = tokenutil.TokenByUserId(user.getId());
            user.setToken(token);
            tokenPO = new TokenPO(user, token);

        }
        return new UniversalResponseBody<TokenPO>(ResponseResultEnum.USER_LOGIN_SECCESS.getCode(),ResponseResultEnum.USER_LOGIN_SECCESS.getMsg(),tokenPO);
    }


    @Override
    public User validateUserPassword(String name, String password) {
        return userMapper.queryUser(name, password);
    }

    @Override
    public boolean isExistUser(String name) {
        User user = userMapper.queryUserByName(name);
        return user != null;
    }

    @Override
    public void insertUser(String name, String password) {
        userMapper.insertUser(name, password);
    }

    @Override
    public void addUserLoginInfo(LoginInfoDo loginInfoDo) {
        loginInfoMapper.addLoginInfo(loginInfoDo);
    }

    @Override
    public void addUserMessageRecord(MessageRecordDo messageRecordDo) {
        messageRecordMapper.addMessageRecord(messageRecordDo);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

}
