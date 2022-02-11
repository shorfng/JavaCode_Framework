package com.loto.metaobjecthandler.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author：蓝田_Loto
 * <p>Date：2020-01-03</p>
 * <p>PageName：User.java</p>
 * <p>Function：映射实体类</p>
 */

@Data                   // Lombok 插件自动生成 getter 和 setter
@NoArgsConstructor      // 生成无参构造
@AllArgsConstructor     // 生成全参构造
//@TableName("mp_user")   // 指定数据库表名（全局配置 tablePrefix 后，可省略 @TableName() 配置）
public class User {
    /**
     * 主键生成策略
     * */
    //@TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 查询的时候，返回该字段的值
     * */
    @TableField(select = true)
    private String name;

    /**
    * 查询的时候，不返回该字段的值
    * */
    @TableField(select = false)
    private Integer age;

    /**
     * 解决字段名不一致问题
     * */
    @TableField(value = "email")
    private String mail;

    /**
     * 该字段在数据库表中不存在
     * */
    @TableField(exist = false)
    private String address;

    /**
     * 该字段满足从经典数据库列名 A_COLUMN（下划线命名） 到经典 Java 属性名 aColumn（驼峰命名） 的类似映射
     * <p> info_mark -> infoMark
     * */
    private String infoMark;

	/**
	 * <p>DEFAULT 默认不处理</p>
	 * <p>INSERT 插入时填充字段</p>
	 * <p>UPDATE 更新时填充字段</p>
	 * INSERT_UPDATE 插入和更新时填充字段
	 */
	@Version
	@TableField(fill = FieldFill.INSERT)
	private Integer version;
}
