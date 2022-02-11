package com.loto.jpa;

import com.loto.jpa.dao.ResumeDao;
import com.loto.jpa.pojo.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-09 22:49</p>
 * <p>PageName：e_Specification.java</p>
 * <p>Function：动态查询</p>
 */

// service层传入dao层的条件不确定，把service拿到条件封装成一个对象传递给Dao层，这个对象就叫做Specification（对条件的一个封装）
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class e_Specification {
    @Autowired
    private ResumeDao resumeDao;

    // 根据条件查询单个对象
    // Optional<T> findOne(@Nullable Specification<T> var1);

    // 根据条件查询所有
    // List<T> findAll(@Nullable Specification<T> var1);

    // 根据条件查询并进行分页
    // Page<T> findAll(@Nullable Specification<T> var1, Pageable var2);

    // 根据条件查询并进行排序
    // List<T> findAll(@Nullable Specification<T> var1, Sort var2);

    // 根据条件统计
    // long count(@Nullable Specification<T> var1);


    /**
     * 动态查询（查询单个对象）<p>
     * select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ where resume0_.name=?
     */
    @Test
    public void testSpecification() {
        Specification<Resume> specification = new Specification<Resume>() {

            // 匿名内部类：toPredicate（动态组装查询条件，用来封装查询条件）
            // Root 根属性（获取需要查询的对象属性，查询所需要的任何属性都可以从根对象中获取)
            // CriteriaQuery 自定义查询方式 用不上
            // CriteriaBuilder 查询构造器，封装了很多的查询条件（模糊查询，精准查询）
            // interface Specification<T> toPredicate(Root<T> var1, CriteriaQuery<?> var2, CriteriaBuilder var3);
            @Override
            public Predicate toPredicate(Root<Resume> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 获取到name属性
                Path<Object> name = root.get("name");

                // 使用CriteriaBuilder针对name属性构建条件（精准查询）
                Predicate predicate = criteriaBuilder.equal(name, "张三");
                return predicate;
            }
        };

        Optional<Resume> optional = resumeDao.findOne(specification);
        Resume resume = optional.get();
        System.out.println(resume);
    }
    
    /**
     * 动态查询（查询多个对象，多条件封装）<p>
     * select resume0_.id as id1_0_, resume0_.address as address2_0_, resume0_.name as name3_0_, resume0_.phone as phone4_0_ from tb_resume resume0_ where resume0_.name=? and (resume0_.address like ?)
     */
    @Test
    public void testSpecificationMultiCon() {
        Specification<Resume> specification = new Specification<Resume>() {
            @Override
            public Predicate toPredicate(Root<Resume> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 获取到name属性
                Path<Object> name = root.get("name");
                Path<Object> address = root.get("address");

                // 条件1：使用CriteriaBuilder针对name属性构建条件（精准查询）
                Predicate predicate1 = criteriaBuilder.equal(name, "张三");

                // 条件2：address 以"北"开头（模糊匹配）
                Predicate predicate2 = criteriaBuilder.like(address.as(String.class), "北%");

                // 组合两个条件
                Predicate and = criteriaBuilder.and(predicate1, predicate2);
                return and;
            }
        };

        Optional<Resume> optional = resumeDao.findOne(specification);
        Resume resume = optional.get();
        System.out.println(resume);
    }
}
