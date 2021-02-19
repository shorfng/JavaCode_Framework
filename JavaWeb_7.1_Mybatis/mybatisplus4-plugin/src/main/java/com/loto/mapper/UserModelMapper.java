package com.loto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loto.pojo.UserModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-01-04 20:45</p>
 * <p>PageName：UserModelMapper.java</p>
 * <p>Function：映射配置文件</p>
 */

public interface UserModelMapper extends BaseMapper<UserModel> {
	/**
	 * 注解式：自定义 sql
	 * <p>查询所有用户
	 */
	@Select("SELECT * FROM mp_user")
	public List<UserModel> findAll();
}
