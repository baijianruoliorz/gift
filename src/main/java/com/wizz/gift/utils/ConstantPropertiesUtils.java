package com.wizz.gift.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
* author liqiqiorz
* date 2020.05.28
* */
//当项目已启动，spring接口，spring加载之后，执行接口一个方法  初始化bean
@Component
public class ConstantPropertiesUtils implements InitializingBean {

  //读取项目配置文件内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;
    //这个方法是当spring把bean初始化之后执行的
    //定义一些静态常量
    public static String END_POIND;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POIND=endpoint;
        ACCESS_KEY_ID=keyId;
        ACCESS_KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;

    }
}

