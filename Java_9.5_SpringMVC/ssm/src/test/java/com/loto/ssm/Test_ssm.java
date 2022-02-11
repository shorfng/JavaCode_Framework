package com.loto.ssm;

import com.loto.ssm.pojo.Account;
import com.loto.ssm.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:application*.xml"})
public class Test_ssm {
    // 希望测试ioc容器中的哪个对象注入即可
    @Autowired
    private AccountService accountService;

    @Test
    public void testMybatisSpring() throws Exception {
        List<Account> accounts = accountService.queryAccountList();
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println(account);
        }
    }
}
