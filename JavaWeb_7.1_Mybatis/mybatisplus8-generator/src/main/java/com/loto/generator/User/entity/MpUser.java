package com.loto.generator.User.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * MybatisPlus的user表
 * </p>
 *
 * @author 蓝田_Loto
 * @since 2021-03-08
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MpUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 个人备注
     */
    private String infoMark;

    private Integer version;

    /**
     * 1删除，0未删除
     */
    private Integer deleted;
}
