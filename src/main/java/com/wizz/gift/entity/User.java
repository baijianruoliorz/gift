package com.wizz.gift.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date loginDate;
    private Date logoutDate;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String openid;

    private Integer status;
    @TableField(exist = false)
    private Integer userId=id;

    private String name;
    public User(String openid){
        this.openid = openid;
    }
    @TableField(exist = false)
    private String token;

    private String pid;

    @TableField(exist = false)
    private List<Gift> giftList;

    @TableField(exist = false)
    private String password;



}
