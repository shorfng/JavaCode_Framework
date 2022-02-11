package com.loto.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 权限表
 */
@Data
@TableName("t_permission")
public class Permission {

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;//权限ID

    @TableField
    private String permissionName;// 权限名称

    @TableField
    private String permissionTag;// 权限名称

    @TableField
    private String permissionUrl;// 权限url

}
