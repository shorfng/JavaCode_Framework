package com.loto.jpa;

import com.loto.jpa.dao.ResumeDao;
import com.loto.jpa.pojo.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-09 21:24</p>
 * <p>PageName：b_Jpql.java</p>
 * <p>Function：引入jpql（jpa查询语言）语句进行查询，sql操作的是数据表和字段，jpql操作的是对象和属性 </p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class b_Jpql {
    @Autowired
    private ResumeDao resumeDao;

    /**
     * select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ where resume0_.id=? and resume0_.name=?
     */
    @Test
    public void testJpql() {
        List<Resume> list = resumeDao.findByJpql(1L, "CJ");
        for (int i = 0; i < list.size(); i++) {
            Resume resume = list.get(i);
            System.out.println(resume);
        }
    }

}
