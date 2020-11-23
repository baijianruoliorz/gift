package com.wizz.gift.entity;

import lombok.Data;

/**
 * @author liqiqi_tql
 * @date 2020/11/23 -9:17
 */
@Data
public class MessageBody {
    /** 消息内容 */
    private String content;
    /** 广播转发的目标地址（告知 STOMP 代理转发到哪个地方） */
    private String destination;
}
