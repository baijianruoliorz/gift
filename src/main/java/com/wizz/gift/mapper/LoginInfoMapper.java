package com.wizz.gift.mapper;

import com.wizz.gift.entity.model.LoginInfoDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author liqiqi_tql
 * @date 2020/11/23 -11:34
 */
@Mapper
@Repository
public interface LoginInfoMapper {
    int addLoginInfo(LoginInfoDo loginInfoDo);
}
