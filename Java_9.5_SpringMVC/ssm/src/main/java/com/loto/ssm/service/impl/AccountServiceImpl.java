package com.loto.ssm.service.impl;

import com.loto.ssm.mapper.AccountMapper;
import com.loto.ssm.pojo.Account;
import com.loto.ssm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> queryAccountList() throws Exception {
        return accountMapper.queryAccountList();
    }
}
