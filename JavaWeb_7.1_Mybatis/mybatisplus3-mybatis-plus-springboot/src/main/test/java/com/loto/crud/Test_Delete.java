package com.loto.crud;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.loto.mapper.UserMapper;
import com.loto.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-01-09 22:32</p>
 * <p>PageName：Test_Delete.java</p>
 * <p>Function：使用 MybatisPlus 进行 Delete 删除操作</p>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_Delete {
    @Autowired
    private UserMapper userMapper;

    /**
     * <p>方式1：根据 ID 进行删除</p>
     * 效果：删除 id 为 9 的数据
     */
    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(9L);
        System.out.println("受影响的数据的行数：" + i);
    }

    /**
     * <p>方式2：根据 columnMap 进行删除</p>
     * 效果：删除满足条件 name 为 CJ，age 为 18 的所有数据
     */
    @Test
    public void testDeleteByMap() {
        // 删除的条件，多个条件就在map中放入多个
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "CJ");
        map.put("age", 18);

        map.forEach((key, value) -> {
            System.out.println("name：" + key);
            System.out.println("age" + value);
        });

        // 将 columnMap 中的元素设置为删除的条件，多个条件是and的关系
        int i = userMapper.deleteByMap(map);
        System.out.println("受影响的数据的行数：" + i);
    }

    /**
     * <p>方式3：根据 delete 方法进行删除</p>
     * 效果：删除满足条件的所有数据
     */
    @Test
    public void testDelete() {
        // 删除的条件（方法1） - 推荐
        User user = new User();
        user.setName("CJ");
        user.setAge(18);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        // 删除的条件（方法2）
        // userQueryWrapper.eq("name","CJ").eq("age",18);

        int i = userMapper.delete(userQueryWrapper);
        System.out.println("受影响的数据的行数：" + i);
    }

    /**
     * <p>方式4：根据 deleteBatchIds 方法进行删除</p>
     * <p>效果：根据 ID 删除满足条件（id为10和11）的所有数据</p>
     * SQL：DELETE FROM mp_user WHERE id IN ( ? , ? )
     */
    @Test
    public void testDeleteBatchIds() {
        int i = userMapper.deleteBatchIds(Arrays.asList(10L, 11L));
        System.out.println("受影响的数据的行数：" + i);
    }
}
