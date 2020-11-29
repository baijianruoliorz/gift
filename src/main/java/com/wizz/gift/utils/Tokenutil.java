package com.wizz.gift.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

/**
 * @author liqiqi_tql
 * @date 2020/11/29 -11:17
 */
@Component
public class Tokenutil {

    /**
     * 根据userId生成token
     * @param userId
     * @return
     */
    public String TokenByUserId(Integer userId){
        String token="";
        /**
         * Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
         * withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
         * 小程序登录没有密码
         */
        token= JWT.create().withAudience(String.valueOf(userId))
                .sign(Algorithm.HMAC256(String.valueOf(userId)));
        return token;
    }
}
