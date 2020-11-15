package com.wizz.gift.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * author:liqiqiorz
 * data:2020.05.28
 * */
//该异常系统不会自动抛出，需要手动抛出
@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class GuliException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
