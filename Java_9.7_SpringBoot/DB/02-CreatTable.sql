# 创建 user 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id       int(20)     NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    username VARCHAR(50) NULL DEFAULT NULL COMMENT '用户名',
    password VARCHAR(50) NULL DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (id)
) comment = 'Mybatis 的 user表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
