package com.wizz.gift.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author liqiqi_tql
 * @date 2020/5/29 -23:45
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    void removeMOreAlyVideo(List videoIdList);
}
