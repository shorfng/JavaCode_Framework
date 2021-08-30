package com.loto.springboot.controller;

import com.loto.springboot.pojo.Employee;
import com.loto.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        Employee emp = employeeService.getEmpById(id);
        return emp;
    }

    @RequestMapping("/updateEmp/{id}")
    public Employee updateEmp(@PathVariable("id") Integer id) {
        Employee emp = employeeService.getEmpById(id);

        // 手动设置更新内容
        emp.setLastName("应颠");
        Employee employee = employeeService.updateEmp(emp);

        return employee;
    }

    @RequestMapping("/delEmp/{id}")
    public Employee delEmp(@PathVariable("id") Integer id) {
        employeeService.delEmp(id);
        return null;
    }
}
