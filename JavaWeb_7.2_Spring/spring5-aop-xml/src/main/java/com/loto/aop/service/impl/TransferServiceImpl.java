package com.loto.aop.service.impl;

import com.loto.aop.dao.AccountDao;
import com.loto.aop.pojo.Account;
import com.loto.aop.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TransferServiceImpl implements TransferService {
    // @Autowired 按照类型注入，如果按照类型无法唯一锁定对象，可以结合 @Qualifier 指定具体的id
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        System.out.println("执行转账业务逻辑");
        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);

        accountDao.updateAccountByCardNo(to);
        //int c = 1 / 0;
        accountDao.updateAccountByCardNo(from);
    }
}
