package com.wizz.gift.controller;

import com.wizz.gift.Info.TokenPO;
import com.wizz.gift.annotation.PassToken;
import com.wizz.gift.service.UserService;
import com.wizz.gift.utils.response.UniversalResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liqiqi_tql
 * @date 2020/11/15 -11:37
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    /**
     * 用户微信小程序登录
     * @param code
     * @return
     */
    @PassToken
    @PostMapping("/wx")
    public UniversalResponseBody<TokenPO> userWxLogin(String code){
        return userService.userWxLogin(code);
    }


}
