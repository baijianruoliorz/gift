package com.wizz.gift.service.impl;

import com.wizz.gift.entity.Comment;
import com.wizz.gift.mapper.CommentMapper;
import com.wizz.gift.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-11-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
