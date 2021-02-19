-- 创建用户表
DROP TABLE IF EXISTS mp_user;
CREATE TABLE mp_user
(
    id        BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    name      VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age       INT(11)     NULL DEFAULT NULL COMMENT '年龄',
    email     VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    info_mark VARCHAR(50) NULL DEFAULT NULL COMMENT '个人备注',
    PRIMARY KEY (id)
) comment = 'MybatisPlus的user表';

-- 新增 version 字段，默认为1
ALTER TABLE `mp_user` ADD COLUMN `version` int (10) NULL AFTER `info_mark`;
UPDATE `mp_user` SET `version`='1';


