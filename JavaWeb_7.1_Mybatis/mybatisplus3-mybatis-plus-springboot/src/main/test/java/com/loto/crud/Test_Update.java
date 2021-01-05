package com.loto.crud;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.loto.mapper.UserMapper;
import com.loto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-01-05 23:34</p>
 * <p>PageName：Test_Update.java</p>
 * <p>Function：使用 MybatisPlus 进行 Update 更新操作</p>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_Update {
    @Autowired
    private UserMapper userMapper;

    /**
     * <p>方式1：根据 ID 进行修改</p>
     * 效果：将 id 为 4 的数据中的 age 修改为 30
     */
    @Test
    public void test_UpdateById() {
        User user = new User();
        user.setId(4L);
        user.setAge(30);

        int i = userMapper.updateById(user);
        System.out.println("受影响的数据的行数：" + i);
    }

    /**
     * <p>方式2：根据条件进行修改（使用 QueryWrapper）</p>
     * 效果：将 name 为 TD 的数据中的 age 修改为 28
     */
    @Test
    public void test_UpdateByCondition_QueryWrapper() {
        // 1. 要更新的字段
        User user = new User();
        user.setAge(28);

        // 2.更新的条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "TD");

        // 参数1：要更新的字段  参数2：更新的条件
        int i = userMapper.update(user, queryWrapper);
        System.out.println("受影响的数据的行数：" + i);
    }

    /**
     * <p>方式3：根据条件进行修改（使用 UpdateWrapper）</p>
     * 效果：将 id 为 5 的数据中的 age 修改为 40
     */
    @Test
    public void test_UpdateByCondition_UpdateWrapper() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 5).set("age", 40);

        int i = userMapper.update(null, updateWrapper);
        System.out.println("受影响的数据的行数：" + i);
    }

}
