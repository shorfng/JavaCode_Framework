package com.loto.mybatis.mapper;

import com.loto.mybatis.pojo.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.caches.redis.RedisCache;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-02 16:02</p>
 * <p>PageName：UserMapper.java</p>
 * Function：
 */

// 开启二级缓存
//@CacheNamespace
@CacheNamespace(implementation = RedisCache.class)
public interface IUserMapper {
    /**
     * 根据 id 更新用户
     *
     * @param user
     */
    @Update("update user set username = #{username} where id = #{id}")
    public void updateUser(User user);

    /**
     * 根据 id 查询用户
     *
     * @param id 用户id
     * @return
     */
    // userCache：⽤来设置本次查询是否禁⽤⼆级缓存，默认为true
    @Options(useCache = true)
    @Select({"select * from user where id = #{id}"})
    public User findUserById(Integer id);
}
