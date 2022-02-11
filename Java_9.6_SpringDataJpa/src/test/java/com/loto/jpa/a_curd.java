package com.loto.jpa;

import com.loto.jpa.dao.ResumeDao;
import com.loto.jpa.pojo.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-09 20:54</p>
 * <p>PageName：a_curd.java</p>
 * <p>Function：调用继承的接口中的方法 </p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class a_curd {
    @Autowired
    private ResumeDao resumeDao;

    /**
     * select resume0_.id as id1_0_0_, resume0_.address as address2_0_0_, resume0_.name as name3_0_0_, resume0_.phone as phone4_0_0_ from tb_resume resume0_ where resume0_.id=?
     */
    @Test
    public void testFindById() {
        // 早期的版本
        // dao.findOne(id);

        Optional<Resume> optional = resumeDao.findById(1L);
        Resume resume = optional.get();

        System.out.println(resume);
    }

    /**
     * select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ where resume0_.name=? and resume0_.id=1
     */
    @Test
    public void testFindOne() {
        Resume resume = new Resume();
        resume.setId(1L);
        resume.setName("张三");

        Example<Resume> example = Example.of(resume);
        Optional<Resume> one = resumeDao.findOne(example);
        Resume resume1 = one.get();

        System.out.println(resume1);
    }

    /**
     * select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_
     */
    @Test
    public void testFindAll() {
        List<Resume> list = resumeDao.findAll();

        for (int i = 0; i < list.size(); i++) {
            Resume resume = list.get(i);
            System.out.println(resume);
        }
    }

    /**
     * insert into tb_resume (address, name, phone) values (?, ?, ?)
     */
    @Test
    public void testSave() {
        // 新增和更新都使用save方法，通过传入的对象的主键有无来区分，没有主键信息那就是新增，有主键信息就是更新
        Resume resume = new Resume();

        resume.setId(5L);
        resume.setName("TD");
        resume.setAddress("北京");
        resume.setPhone("132000000");

        Resume save = resumeDao.save(resume);

        System.out.println(save);
    }

    /**
     * delete from tb_resume where id=?
     */
    @Test
    public void testDelete() {
        resumeDao.deleteById(6L);
    }
}
