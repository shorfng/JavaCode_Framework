package com.loto.springboot.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 将当前注入属性值的 Person 类对象作为 Bean 组件放到 Spring IOC 容器中，只有这样才能被 @ConfigurationProperties 注解进行赋值
@Component
// 将配置文件中以 person 开头的属性值通过 setXX() 方法注入到实体类对应属性中，实现批量注入
@ConfigurationProperties(prefix = "person")
public class Person {
    private int id;           // id
    private String name;      // 名称
    private List hobby;       // 爱好
    private String[] family;  //家庭成员
    private Map map;
    private Pet pet;          // 宠物

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getHobby() {
        return hobby;
    }

    public void setHobby(List hobby) {
        this.hobby = hobby;
    }

    public String[] getFamily() {
        return family;
    }

    public void setFamily(String[] family) {
        this.family = family;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hobby=" + hobby +
                ", family=" + Arrays.toString(family) +
                ", map=" + map +
                ", pet=" + pet +
                '}';
    }
}
