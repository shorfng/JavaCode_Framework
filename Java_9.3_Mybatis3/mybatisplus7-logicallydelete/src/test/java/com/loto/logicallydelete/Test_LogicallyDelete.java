package com.loto.logicallydelete;

import com.loto.logicallydelete.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-03-08 11:12</p>
 * <p>PageName：Test_LogicallyDelete.java</p>
 * <p>Function：测试 - 逻辑删除 </p>
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test_LogicallyDelete {
    @Autowired
    private UserMapper userMapper;

    /**
     * <p>方式1：根据 ID 进行删除</p>
     * 效果：删除 id 为 9 的数据
     */
    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(2L);
        System.out.println("受影响的数据的行数：" + i);
    }
}
