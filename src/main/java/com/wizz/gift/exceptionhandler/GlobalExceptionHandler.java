package com.wizz.gift.exceptionhandler;




import com.wizz.gift.commonUtils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//统一异常处理类
@ControllerAdvice
@Slf4j  //配合logback，可以输出异常日志啦！
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法,@exceptionHandler:异常处理器，这里表示所有异常都执行
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据,因为controller有@restController，包含了@responseBody，所有能返回，这里也是一个道理
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理..");
    }

    //特定异常，机制：先看特定异常，再看全局异常.
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody //为了返回数据
    public R error(GuliException e) {
        //会写到文件和console哦！
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
