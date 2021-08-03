package com.loto.aop.test;

import com.loto.aop.service.TransferService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-03 20:35</p>
 * <p>PageName：AopTest.java</p>
 * <p>Function：</p>
 */

public class AopTest {
    @Test
    public void testXmlAndAnnoAop() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TransferService transferService = applicationContext.getBean(TransferService.class);
        transferService.transfer("6029621011000", "6029621011001", 100);
    }
}
