package com.wizz.gift.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liqiqi_tql
 * @date 2020/8/1 -11:54
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    //swagger插件
    @Bean
    public Docket webApiConfig() {
        //这行代码表示类型是swagger
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                //引入下面的APIinfo方法
//                就是一些描述
                .apiInfo(webApiInfo())
//                配置扫描路径 不然全部扫描
                .select()
//                指定扫描的包 new
                .apis(RequestHandlerSelectors.basePackage("com.wizz.gift.controller"))
                //not表示没有，即接口路径中包含admin和error就不进行显示了  先注释掉，因为如果包含admin,就不会显示，而acl正好包含admin
                //.paths(Predicates.not(PathSelectors.regex("/admin/.*")))
//                这个是过滤什么路径
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }
//    可以配置多个分组
    //@Bean
   // public  Docket docket1(){
  //      return new Docket(DocumentationType.SWAGGER_2).groupName("A-group");
  //  }

    private ApiInfo webApiInfo() {
//        作者信息
        //  Contact contact=new Contact("","","");
        //这里是在线文档中的一些信息
//        另一种格式:
//        return new ApiInfo
//         title:""
        return new ApiInfoBuilder()
                .title("wizz-gift的后台管理")
                .description("本文档描述了wizz-gift项目接口定义")
                .version("1.0")
                .termsOfServiceUrl("https:yangxiangrui.site")
//                这就是作者信息 也可以写成 Contact,
                .contact(new Contact("liqiqiorz", "https:yangxiangrui.site", "1099462011@qq.com"))
                .build();
    }

}
