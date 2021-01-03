package com.loto.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author：蓝田_Loto
 * <p>Date：2020-01-03</p>
 * <p>PageName：User.java</p>
 * <p>Function：映射实体类</p>
 */
@Data                   // Lombok 插件自动生成 getter 和 setter
@NoArgsConstructor      // 生成无参构造
@AllArgsConstructor     // 生成全参构造
@TableName("mp_user")   // 指定数据库表名
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
