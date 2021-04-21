package com.loto.mapper;

import com.loto.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-02 16:02</p>
 * <p>PageName：UserMapper.java</p>
 * Function：
 */

public interface IUserMapper extends Mapper<User> {
	/**
	 * 添加一条用户数据
	 * @param user 用户对象<p>
	 * keyProperty：表示对象中的成员变量<p>
	 * keyColumn：表示数据库中的列名
	 */
	@Insert("insert into user (username, password) values (#{username},#{password})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public int addUser(User user);

	/**
	 * 添加多条用户数据
	 */
	@Insert("<script>" +
			"insert into user (username, password) values " +
			"<foreach collection=\"list\" item=\"item1\" index=\"index\"  separator=\",\">" +
			"(#{item1.username},#{item1.password})" +
			"</foreach>" +
			"</script>")
	int batchAddUser(@Param("list") List<User> users);

	/**
	 * 根据 id 更新用户
	 * @param user
	 */
	@Update("update user set username = #{username} where id = #{id}")
	public void updateUser(User user);

	/**
	 * 查询用户
	 *
	 * @return
	 */
	@Select("select * from user")
	public List<User> selectAllUser();

	/**
	 * 根据 id 查询用户
	 * @param id 用户id
	 * @return
	 */
	@Select({"select * from user where id = #{id}"})
	public User findUserById(Integer id);

	/**
	 * 一对多（查询所有用户、同时查询每个用户关联的订单信息）
	 */
	@Select("select * from user")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "username", column = "username"),
			@Result(property = "orderList", column = "id", javaType = List.class,
					many = @Many(select = "com.loto.mapper.IOrderMapper.findOrderByUid"))
	})
	public List<User> findAll();

	/**
	 * 多对多（查询所有用户、同时查询每个用户关联的角色信息）
	 */
	@Select("select * from user")
	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "username", column = "username"),
			@Result(property = "roleList", column = "id", javaType = List.class,
					many = @Many(select = "com.loto.mapper.IRoleMapper.findRoleByUid"))
	})
	public List<User> findAllUserAndRole();

	/**
	 * 根据 id 删除用户
	 * @param id 用户id
	 */
	@Delete("delete from user where id = #{id}")
	public void deleteUser(Integer id);
}
