package com.loto.mapper;

import com.loto.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-20 16:23</p>
 * <p>PageName：IRoleMapper.java</p>
 * Function：
 */

public interface IRoleMapper {
	@Select("select * from roles r,user_role ur where r.id = ur.roleid and ur.userid = #{uid}")
	public List<Role> findRoleByUid(Integer uid);
}
