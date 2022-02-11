package com.loto.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品实体类
 */
@Data
@TableName("t_product")
public class Product {
    @TableId(value = "ID",type = IdType.AUTO)
    private Long id;//商品ID
    @TableField
    private String name;//商品名称
    @TableField
    private BigDecimal price;//商品价格
    @TableField
    private Long stock;//库存
    @TableField
    private Integer isShow = 0;// 是否展示 默认-0 不显示
    @TableField
    private Date createTime;//创建时间

}
