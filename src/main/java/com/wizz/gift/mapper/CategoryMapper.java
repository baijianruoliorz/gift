package com.wizz.gift.mapper;

import com.wizz.gift.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.mail.MailSessionDefinition;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-11-15
 */
@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
