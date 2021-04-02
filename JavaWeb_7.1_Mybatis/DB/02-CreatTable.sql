# 创建 user 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id       int(20)     NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    username VARCHAR(50) NULL DEFAULT NULL COMMENT '用户名',
    password VARCHAR(50) NULL DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (id)
) comment = 'Mybatis的user表';

#############################################################################

# 创建 mp_user 用户表
DROP TABLE IF EXISTS mp_user;
CREATE TABLE mp_user
(
    id        BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    name      VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age       INT(11)     NULL DEFAULT NULL COMMENT '年龄',
    email     VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    info_mark VARCHAR(50) NULL DEFAULT NULL COMMENT '个人备注',
    PRIMARY KEY (id)
) comment = 'MybatisPlus的user表';

# 新增 version 字段，默认为1
ALTER TABLE `mp_user`
    ADD COLUMN `version` int(10) NULL DEFAULT 1 AFTER `info_mark`;

# 新增 deleted 字段，默认为0
ALTER TABLE `mp_user`
    ADD COLUMN `deleted` int(1) NULL DEFAULT 0 COMMENT '1删除，0未删除' AFTER `version`;

