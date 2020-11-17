package com.wizz.gift.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import com.wizz.gift.commonUtils.R;
import com.wizz.gift.exceptionhandler.GuliException;
import com.wizz.gift.service.VodService;
import com.wizz.gift.utils.ConstantVodUtils;
import com.wizz.gift.utils.InitVodCilent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author liqiqi_tql
 * @date 2020/5/30 -00:03
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云，上传必用post
    @PostMapping("uploadAlyiVideo") //得到上传文件
    public R uploadAlyiVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return R.ok().data("videoId",videoId);
    }
    //根据视频ID删除阿里云中的视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id){
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        }catch(Exception e) {
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }
    }
    //删除阿里云视频的方法：删除多个视频
    @DeleteMapping("delete-batch")              //需指定泛型
    public R deleteBatch(@RequestParam("videoIdList")List<String> videoIdList){//这个注解不加也可以
        vodService.removeMOreAlyVideo(videoIdList);


        return R.ok();
    }
    //根据视频id获取视频凭证
//    @GetMapping("getPlayAuth/{id}")
//    public R getPlayAuth(@PathVariable String id){
//        try {
//            //创建初始化对象
//            DefaultAcsClient client =
//                    InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
//            //创建获取凭证request和response对象
//            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
//            //向request设置视频id
//            request.setVideoId(id);
//            //调用方法得到凭证
//            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
//            String playAuth = response.getPlayAuth();
//            return R.ok().data("playAuth",playAuth);
//        }catch(Exception e) {
//            throw new GuliException(20001,"获取凭证失败");
//        }
//
//    }
    @GetMapping("getPlayauth/{id}")
    public R getPlayAuth(@PathVariable String id){
        try{
//            创建初始化对象
            DefaultAcsClient client=InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET);
//            创建获取凭证request和response对象
            GetVideoPlayAuthRequest request=new GetVideoPlayAuthRequest();
//            向request设置视屏id
            request.setVideoId(id);
//            调用方法得到凭证
            GetVideoPlayAuthResponse response=client.getAcsResponse(request);
            String playAuth=response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        }catch (Exception e){
            throw new GuliException(20001,"获取凭证失败");
        }
    }

}
