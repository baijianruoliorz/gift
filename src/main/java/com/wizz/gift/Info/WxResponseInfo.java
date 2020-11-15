package com.wizz.gift.Info;

import lombok.Data;

/**
 * 微信返回数据对象
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/2 13:16
 */
@Data
public class WxResponseInfo {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String session_key;

    private String errcode;

    private String errmsg;
}
