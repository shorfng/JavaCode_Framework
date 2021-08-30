package com.loto.springboot.service;

import com.loto.springboot.mapper.EmployeeMapper;
import com.loto.springboot.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"emp"})
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /*
     * 	@Cacheable : 缓存查询：会将该方法的返回值存到缓存中
     *		value/cacheNames：指定缓存的名称 ，cacheManager是管理多个cache,以名称进行区分
     * 		key：缓存数据时指定key值 （key,value） ,默认是方法的参数值，也可以使用spEL来计算key的值
     * 		keyGenerator：key的生成策略，和key进行二选一 ，自定义keyGenerator
     * 		cacheManager：指定缓存管理器  redis:emp   ehcache:emp
     * 		cacheResolver: 功能跟cacheManager相同，二选一即可
     * 		condition：条件属性，满足这个条件才会进行缓存
     * 		unless: 否定条件：满足这个条件，不进行缓存
     * 		sync: 是否使用异步模式进行缓存 true
     *			(1) condition/unless 同时满足，不缓存
     * 			(2) sync的值为true的时候，unless不被支持
     */

    @Cacheable(key = "#id", condition = "#id > 0", unless = "#result == null")
    public Employee getEmpById(Integer id) {
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    @CachePut(key = "#employee.id")
    public Employee updateEmp(Employee employee) {
        employeeMapper.updateEmp(employee);
        return employee;
    }

    @CacheEvict(key = "#id", beforeInvocation = true)
    public void delEmp(Integer id) {
        employeeMapper.deleteEmpById(id);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
}
