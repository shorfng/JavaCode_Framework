package com.loto.tx.dao;

import com.loto.tx.pojo.Account;

public interface AccountDao {
    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}
