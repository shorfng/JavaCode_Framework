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
 * <p>Date：2021-08-09 22:40</p>
 * <p>PageName：c_sql.java</p>
 * <p>Function：引入原生的sql语句</p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class c_sql {
    @Autowired
    private ResumeDao resumeDao;

    /**
     * select * from tb_resume where name like ? and address like ?
     */
    @Test
    public void testSql() {
        List<Resume> list = resumeDao.findBySql("李%", "上海%");
        for (int i = 0; i < list.size(); i++) {
            Resume resume = list.get(i);
            System.out.println(resume);
        }
    }
}
