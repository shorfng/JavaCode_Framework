# 创建 account 转账表
create table account
(
    name   varchar(255) null comment '用户名',
    money  int(255)     null comment '账户金额',
    cardNo varchar(255) not null comment '银行卡号',
    constraint account_pk
        primary key (cardNo)
) comment '转账表' engine = innodb;
