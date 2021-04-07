# 插入 - user 用户表
INSERT INTO user (id, username, password) VALUES (1, 'TD', '123');
INSERT INTO user (id, username, password) VALUES (2, 'CJ', '321');

# 插入 - orders 订单表
INSERT INTO `orders` VALUES ('1', '2020-12-12', '3000', '1');
INSERT INTO `orders` VALUES ('2', '2020-12-12', '4000', '1');
INSERT INTO `orders` VALUES ('3', '2020-12-12', '5000', '2');

# 插入 - roles 角色表
INSERT INTO `roles` VALUES ('1', 'CTO', 'CTO');
INSERT INTO `roles` VALUES ('2', 'CEO', 'CEO');

# 插入 - user_role 用户角色表
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '1');
INSERT INTO `user_role` VALUES ('1', '2');
INSERT INTO `user_role` VALUES ('2', '2');


# ==================================================================

# 插入 - mp_user
INSERT INTO mp_user (id, name, age, email)
VALUES (1, 'Jone', 18, 'test1@baomidou.com'),
    (2, 'Jack', 20, 'test2@baomidou.com'),
    (3, 'Tom', 28, 'test3@baomidou.com'),
    (4, 'Sandy', 21, 'test4@baomidou.com'),
    (5, 'Billie', 24, 'test5@baomidou.com');
