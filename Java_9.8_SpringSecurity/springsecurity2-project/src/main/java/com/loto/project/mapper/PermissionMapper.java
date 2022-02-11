package com.loto.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loto.project.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *  动态查询数据库中用户对应的权限
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据用户ID查询权限
     *
     * @param id
     * @return
     */
    @Select("SELECT p.*  FROM t_permission p,t_role_permission rp,t_role r,t_user_role ur,t_user u " +
            "WHERE p.id = rp.PID AND rp.RID = r.id AND r.id = ur.RID AND ur.UID = u.id AND u.id =#{id}")
    List<Permission> findByUserId(Integer id);
}
