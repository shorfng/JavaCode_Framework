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

# 创建 orders 订单表
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT COMMENT '订单主键id',
    `ordertime` varchar(255) DEFAULT NULL COMMENT '订单时间',
    `total`     double       DEFAULT NULL COMMENT '总数',
    `uid`       int(20)      DEFAULT NULL COMMENT '用户主键id',
    PRIMARY KEY (`id`),
    KEY `uid` (`uid`),
    CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) comment = 'Mybatis 的 orders 订单表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

# 创建 roles 角色表
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `rolename` varchar(255) DEFAULT NULL,
    `roleDesc` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) comment = 'Mybatis 的 roles 角色表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

# 创建 user_role 用户角色表
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `userid` int(11) NOT NULL,
    `roleid` int(11) NOT NULL,
    PRIMARY KEY (`userid`, `roleid`),
    KEY `roleid` (`roleid`),
    CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
    CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `roles` (`id`)
) comment = 'Mybatis 的 user_role 用户角色表'
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


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
    version   INT(10)     NULL DEFAULT 1 COMMENT '版本号',
    deleted   INT(1)      NULL DEFAULT 0 COMMENT '1删除，0未删除',
    PRIMARY KEY (id)
) comment = 'MybatisPlus的user表';

