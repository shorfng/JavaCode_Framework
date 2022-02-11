package com.loto.jpa;

import com.loto.jpa.dao.ResumeDao;
import com.loto.jpa.pojo.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-10 22:37</p>
 * <p>PageName：f_sort.java</p>
 * <p>Function：排序 </p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class f_sort {
    @Autowired
    private ResumeDao resumeDao;

    /**
     * select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ order by resume0_.id desc
     */
    @Test
    public void testSort() {
        // 倒序：Sort.Direction.DESC
        Sort sort = new Sort(Sort.Direction.DESC, "id");

        List<Resume> list = resumeDao.findAll(sort);
        for (int i = 0; i < list.size(); i++) {
            Resume resume = list.get(i);
            System.out.println(resume);
        }
    }
}
