package com.loto.ssm.service;

import com.loto.ssm.pojo.Account;

import java.util.List;

public interface AccountService {
    List<Account> queryAccountList() throws Exception;
}
