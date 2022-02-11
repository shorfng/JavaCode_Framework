package com.loto.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id; // 用户ID

    @TableField
    private String username;//用户名

    @TableField
    private String password; //用户密码

    @TableField
    private Integer status; // 用户状态1-启用 0-不启用
}
