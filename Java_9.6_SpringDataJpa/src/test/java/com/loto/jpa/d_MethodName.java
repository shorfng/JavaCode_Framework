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
 * <p>Date：2021-08-09 22:42</p>
 * <p>PageName：d_MethodName.java</p>
 * <p>Function：按照方法命名规则查询 </p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class d_MethodName {
    @Autowired
    private ResumeDao resumeDao;

    /**
     * select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ where (resume0_.name like ? escape ?) and resume0_.address=?
     */
    @Test
    public void testMethodName() {
        List<Resume> list = resumeDao.findByNameLikeAndAddress("李%", "上海");
        for (int i = 0; i < list.size(); i++) {
            Resume resume = list.get(i);
            System.out.println(resume);
        }
    }
}
