package com.loto.crud;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loto.mapper.UserMapper;
import com.loto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-01-11 20:42</p>
 * <p>PageName：Test_Select.java</p>
 * <p>Function：使用 MybatisPlus 进行 Select 查询操作</p>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_Select {
    @Autowired
    private UserMapper userMapper;

    /**
     * 方式1：根据 ID 进行查询
     */
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(2L);
        System.out.println(user);
    }

    /**
     * 方式2：根据 ID 进行批量查询
     */
    @Test
    public void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(2L, 3L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 方式3：根据条件查询1条数据（selectOne方法）
     */
    @Test
    public void testSelectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Jone");

        // 根据条件查询一条记录，如果查询结果超过一条，会报错
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    /**
     * 方式4：根据 queryWrapper 匹配查询条件，查询总记录数（selectCount）
     */
    @Test
    public void testSelectCount() {
        // 查询年龄大于18的
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 18);

        // 根据条件查询数据条数
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    /**
     * 方式5：根据 queryWrapper 匹配查询条件，查询全部录数（selectList）
     */
    @Test
    public void testSelectList() {
        // 查询年龄大于18的
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 18);

        // 根据条件查询数据条数
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 方式6：分页查询
     */
    @Test
    public void testSelectPage() {
        // 第一个参数：当前页
        // 第二个参数：每页显示条数
        Page<User> page = new Page<>(2, 2);

        // 查询年龄大于18的
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 18);

        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("总条数：" + userIPage.getTotal());
        System.out.println("总页数：" + userIPage.getPages());
        System.out.println("分页数据：" + userIPage.getRecords());
    }

}
