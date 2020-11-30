package com.wizz.gift.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author czr
 * @since 2020-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Subcategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    private String name;


}
