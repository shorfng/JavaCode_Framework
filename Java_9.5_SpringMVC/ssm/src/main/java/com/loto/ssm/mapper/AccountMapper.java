package com.loto.ssm.mapper;

import com.loto.ssm.pojo.Account;

import java.util.List;

public interface AccountMapper {
    // 定义dao层接口方法（查询account表所有数据）
    List<Account> queryAccountList() throws Exception;
}
