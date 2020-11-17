package com.wizz.gift.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liqiqi_tql
 * @date 2020/9/21 -22:10
 */
@RestController("/")
public class Introduction {

    @GetMapping("/")
    public String introduction(){
        return  "Hello guy,I'm a developer who is exploitting this project.\n" +
                "You can find me in the github:https://github.com/baijianruoliorz.\n"+
                "I am a student from xidian university,China.\n"+
                "And i am looking for a full-time job for my internship experience.\n"+
                "How to touch me:QQ:1099462011.";
    }
}
