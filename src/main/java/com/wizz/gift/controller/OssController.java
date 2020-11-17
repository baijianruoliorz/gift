package com.wizz.gift.controller;


import com.wizz.gift.commonUtils.R;
import com.wizz.gift.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;
    //上传头像的方法
    @PostMapping
    public R uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }

}

//package com.atguigu.oss.controller;
//
//import com.atguigu.commonutils.R;
//import com.atguigu.oss.service.OssService;
//import io.swagger.annotations.Api;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/eduoss/fileoss")
//@CrossOrigin
//@Api(description = "传文件")
//public class OssController {
//
//    @Autowired
//    private OssService ossService;
//    //上传头像的方法   ,参数还是要记住的~ 目前的问题：同名会覆盖，所以要加随机值,2文件要进行分类存储
//    public R uploadOssFIle(MultipartFile file){
//        String url=ossService.uploadFileAvatar(file);
//
//        return R.ok().data("url",url);
//    }
//
//}