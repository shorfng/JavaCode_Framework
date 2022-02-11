package com.loto.jpa;

import com.loto.jpa.dao.ResumeDao;
import com.loto.jpa.pojo.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-10 22:39</p>
 * <p>PageName：g_page.java</p>
 * <p>Function：分页 </p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class g_page {
    @Autowired
    private ResumeDao resumeDao;

    /**
     * select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ limit ?  <p>
     * select count(resume0_.id) as col_0_0_ from tb_resume resume0_ Page 1 of 2 containing com.loto.jpa.pojo.Resume instances  <p>
     * 第一个参数：当前查询的页数，从0开始 <p>
     * 第二个参数：每页查询的数量
     */
    @Test
    public void testPage() {
        Pageable pageable = PageRequest.of(0, 2);
        // Pageable pageable = new PageRequest(0,2);

        Page<Resume> all = resumeDao.findAll(pageable);
        System.out.println(all);
    }
}
