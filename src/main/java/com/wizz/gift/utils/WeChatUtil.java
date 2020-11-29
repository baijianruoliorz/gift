package com.wizz.gift.utils;


import com.alibaba.fastjson.JSON;

import com.wizz.gift.Info.WxResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author liqiqi_tql
 * @date 2020/11/29 -11:20
 */
@Component
@Slf4j
public class WeChatUtil {

    @Value("${wx.url}")
    private String WECHAT_OPENID_URL;

    private static RestTemplate restTemplate = new RestTemplate();

    public WxResponseInfo getWeChatResponseBody(String code) {

        String url = WECHAT_OPENID_URL + code;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity.getStatusCodeValue() != 200) {
           log.error("Connect weChat failed");
        }

        WxResponseInfo wxResponseInfo = JSON.parseObject(responseEntity.getBody(), WxResponseInfo.class);

        return wxResponseInfo;
    }

}
