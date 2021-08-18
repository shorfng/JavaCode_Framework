SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`
(
    `username`  varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `series`    varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `token`     varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
    `last_used` timestamp                                       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('admin', 'zaPEuwaT/bdMeJNmVtgY8g==', 'ZkVyYVK/o1RJMt8nOO2k0A==', '2020-10-31 10:39:10');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`
(
    `ID`              int(11)                                                 NOT NULL AUTO_INCREMENT COMMENT '编号',
    `permission_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '权限名称',
    `permission_tag`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '权限标签',
    `permission_url`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限地址',
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '查询所有用户', 'user:findAll', '/user/findAll');
INSERT INTO `t_permission` VALUES (2, '用户添加或修改', 'user:saveOrUpdate', '/user/saveOrUpadate');
INSERT INTO `t_permission` VALUES (3, '用户删除', 'user:delete', '/delete/{id}');
INSERT INTO `t_permission` VALUES (4, '根据ID查询用户', 'user:getById', '/user/{id}');
INSERT INTO `t_permission` VALUES (5, '查询所有商品', 'product:findAll', '/product/findAll');
INSERT INTO `t_permission` VALUES (6, '商品添加或修改', 'product:saveOrUpdate', '/product/saveOrUpadate');
INSERT INTO `t_permission` VALUES (7, '商品删除', 'product:delete', '/product//delete/{id}');
INSERT INTO `t_permission` VALUES (8, '商品是否显示', 'product:show', '/product/show/{id}/{isShow}');

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`
(
    `id`          int(11)                                         NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品名称',
    `price`       decimal(10, 2)                                  NULL DEFAULT NULL COMMENT '商品价格',
    `stock`       int(11)                                         NULL DEFAULT NULL COMMENT '库存',
    `is_show`     tinyint(4)                                      NULL DEFAULT NULL COMMENT '是否展示',
    `create_time` datetime                                        NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES (1, '华为mate30', 4500.00, 1001, 0, '2020-10-24 13:53:25');
INSERT INTO `t_product` VALUES (2, '红米10', 3500.00, 100, 1, '2020-10-24 13:53:52');
INSERT INTO `t_product` VALUES (3, '苹果12', 6000.00, 100, 1, '2020-10-24 13:54:24');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`
(
    `ID`        int(11)                                                NOT NULL AUTO_INCREMENT COMMENT '编号',
    `ROLE_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
    `ROLE_DESC` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'ADMIN', '超级管理员');
INSERT INTO `t_role` VALUES (2, 'USER', '用户管理');
INSERT INTO `t_role` VALUES (3, 'PRODUCT', '商品管理员');
INSERT INTO `t_role` VALUES (4, 'PRODUCT_INPUT', '商品录入员');
INSERT INTO `t_role` VALUES (5, 'PRODUCT_SHOW', '商品审核员');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`
(
    `RID` int(11) NOT NULL COMMENT '角色编号',
    `PID` int(11) NOT NULL COMMENT '权限编号',
    PRIMARY KEY (`RID`, `PID`) USING BTREE,
    INDEX `FK_Reference_12` (`PID`) USING BTREE,
    CONSTRAINT `FK_Reference_11` FOREIGN KEY (`RID`) REFERENCES `t_role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Reference_12` FOREIGN KEY (`PID`) REFERENCES `t_permission` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1);
INSERT INTO `t_role_permission` VALUES (2, 1);
INSERT INTO `t_role_permission` VALUES (1, 2);
INSERT INTO `t_role_permission` VALUES (2, 2);
INSERT INTO `t_role_permission` VALUES (1, 3);
INSERT INTO `t_role_permission` VALUES (2, 3);
INSERT INTO `t_role_permission` VALUES (1, 4);
INSERT INTO `t_role_permission` VALUES (2, 4);
INSERT INTO `t_role_permission` VALUES (1, 5);
INSERT INTO `t_role_permission` VALUES (3, 5);
INSERT INTO `t_role_permission` VALUES (4, 5);
INSERT INTO `t_role_permission` VALUES (5, 5);
INSERT INTO `t_role_permission` VALUES (1, 6);
INSERT INTO `t_role_permission` VALUES (3, 6);
INSERT INTO `t_role_permission` VALUES (4, 6);
INSERT INTO `t_role_permission` VALUES (1, 7);
INSERT INTO `t_role_permission` VALUES (3, 7);
INSERT INTO `t_role_permission` VALUES (4, 7);
INSERT INTO `t_role_permission` VALUES (1, 8);
INSERT INTO `t_role_permission` VALUES (3, 8);
INSERT INTO `t_role_permission` VALUES (5, 8);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`       int(11)                                          NOT NULL AUTO_INCREMENT,
    `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin  NULL DEFAULT NULL,
    `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `status`   int(1)                                           NULL DEFAULT NULL COMMENT '用户状态1-启用 0-关闭',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '$2a$10$m8WqgTzr0TO.XG.aR91.jegJJmDnGSvWs69aMWPR.WNvCzemHpLum', 1);
INSERT INTO `t_user` VALUES (2, 'zhaoyang', '$2a$10$m8WqgTzr0TO.XG.aR91.jegJJmDnGSvWs69aMWPR.WNvCzemHpLum', 1);
INSERT INTO `t_user` VALUES (3, 'user1', '$2a$10$m8WqgTzr0TO.XG.aR91.jegJJmDnGSvWs69aMWPR.WNvCzemHpLum', 1);
INSERT INTO `t_user` VALUES (4, 'user2', '$2a$10$m8WqgTzr0TO.XG.aR91.jegJJmDnGSvWs69aMWPR.WNvCzemHpLum', 1);
INSERT INTO `t_user` VALUES (5, 'user3', '$2a$10$Wk1jWJPoMQ5s7UIp0S/tu.WTcUZUspUUQH6K3BQpa8uHXWRUQc3/a', 1);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`
(
    `UID` int(11) NOT NULL COMMENT '用户编号',
    `RID` int(11) NOT NULL COMMENT '角色编号',
    PRIMARY KEY (`UID`, `RID`) USING BTREE,
    INDEX `FK_Reference_10` (`RID`) USING BTREE,
    CONSTRAINT `FK_Reference_10` FOREIGN KEY (`RID`) REFERENCES `t_role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `FK_Reference_9` FOREIGN KEY (`UID`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1);
INSERT INTO `t_user_role` VALUES (2, 2);
INSERT INTO `t_user_role` VALUES (3, 4);
INSERT INTO `t_user_role` VALUES (4, 5);

SET FOREIGN_KEY_CHECKS = 1;
