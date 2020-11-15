package com.wizz.gift.config;

import com.github.dreamyoung.mprelation.AutoMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liqiqi_tql
 * @date 2020/8/9 -13:34
 */
@Configuration
public class AutoMapperConfig {
    @Bean
    public AutoMapper autoMapper() {
        return new AutoMapper(new String[] { "com.wizz.gift.entity"}); //配置实体类所在目录（可多个,暂时不支持通过符*号配置）
    }
}