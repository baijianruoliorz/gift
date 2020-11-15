//package com.wizz.gift.utils;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.auth0.jwt.interfaces.JWTVerifier;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author liqiqi_tql
// * @date 2020/8/18 -13:05
// */
//public class JwtUtils {
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//    /**
//     * 密钥
//     */
//    private static final String SECRET = "my_secret";
//
//    /**
//     * 过期时间
//     **/
//    private static final long EXPIRATION = 1800L;//单位为秒
//
//    /**
//     * 生成用户token,设置token超时时间
//     */
//    public static String createToken(User user) {
//        //过期时间
//        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
//        Map<String, Object> map = new HashMap<>();
//        map.put("alg", "HS256");
//        map.put("typ", "JWT");
//        String token = JWT.create()
//                .withHeader(map)// 添加头部
//                //可以将基本信息放到claims中
//                .withClaim("id", user.getId())//userId
//                // .withClaim("userName", user.getUserName())//userName
//                .withClaim("name", user.getName())//name
//                .withExpiresAt(expireDate) //超时设置,设置过期的日期
//                .withIssuedAt(new Date()) //签发时间
//                .sign(Algorithm.HMAC256(SECRET)); //SECRET加密
//        return token;
//    }
//
//    /**
//     * 校验token并解析token
//     */
//    public static Map<String, Claim> verifyToken(String token) {
//        DecodedJWT jwt = null;
//        try {
//            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
//            jwt = verifier.verify(token);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            logger.error("token解码异常");
//            //解码异常则抛出异常
//            return null;
//        }
//        return jwt.getClaims();
//    }
//}
//
////https://juejin.im/post/6844903992657444878
