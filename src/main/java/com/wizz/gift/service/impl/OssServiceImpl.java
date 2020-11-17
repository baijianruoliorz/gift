package com.wizz.gift.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import com.wizz.gift.service.OssService;
import com.wizz.gift.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    //不用记。。阿里爸爸已经准备好了，在简单上传的上传文件流里面找,uuid可以让文件生成唯一值哦~
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POIND;
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String originalFilename = file.getOriginalFilename();
            //添加UUID
            //UUID最开始带----的 比如112-223-23这样,可以替换掉
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            originalFilename=uuid+originalFilename;
            //2  把文件按照日期分类
            //例如：2020/05.28/01/jpg 会自动创建文件夹

            //获取当前日期,使用加进来的日期工具类
            String datePath = new DateTime().toString("yyyy/MM/dd");

            //拼接一下,就可以了~~
            originalFilename=datePath+"/"+originalFilename;


            //第一个参数：你的bucketname名称，
            // 第二个是你的文件路径和名称,  如果是aa/bb/1.jpg的话，会自动创建文件夹
            // 第三个参数，输入流
            ossClient.putObject(bucketName, originalFilename, inputStream);

// 关闭OSSClient。
            ossClient.shutdown();
//把文件路径给返回，得手动拼接出
// https://edu-1014.oss-cn-beijing.aliyuncs.com/TIM%E5%9B%BE%E7%89%8720200524202419.jpg
            String url="https://"+bucketName+"."+endpoint+"/"+originalFilename;
            return url;
        } catch (Exception e) {

        }
        return null;
    }
}
