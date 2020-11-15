package com.wizz.gift.utils.response;

/**
 * @author 郭树耸
 * @version 1.0
 * @date 2020/2/10 12:40
 */
public class ResponseException extends RuntimeException {
    /**
     * 错误代码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;

    public Integer getCode() {
        return code;
    }



    public String getMsg() {
        return msg;
    }

    public ResponseException(String message, Integer code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public ResponseException(String message) {
        super(message);
    }

}
