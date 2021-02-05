package com.loto.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author：蓝田_Loto
 * <p>Date：2020-01-03</p>
 * <p>PageName：UserModel.java</p>
 * <p>Function：映射实体类（ActiveRecord 使用）
 */

@Data                   // Lombok 插件自动生成 getter 和 setter
@NoArgsConstructor      // 生成无参构造
@AllArgsConstructor     // 生成全参构造
@TableName("mp_user")   // 指定数据库表名（全局配置 tablePrefix 后，可省略 @TableName() 配置）
public class UserModel extends Model<UserModel> {
	/**
	 * 主键生成策略
	 */
	//@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 查询的时候，返回该字段的值
	 */
	@TableField(select = true)
	private String name;

	/**
	 * 查询的时候，不返回该字段的值
	 */
	@TableField(select = true)
	private Integer age;

	/**
	 * 解决字段名不一致问题
	 */
	@TableField(value = "email")
	private String mail;

	/**
	 * 该字段在数据库表中不存在
	 */
	@TableField(exist = false)
	private String address;

	/**
	 * 该字段满足从经典数据库列名 A_COLUMN（下划线命名） 到经典 Java 属性名 aColumn（驼峰命名） 的类似映射
	 * <p> info_mark -> infoMark
	 */
	private String infoMark;
}
