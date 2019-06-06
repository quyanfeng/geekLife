//package com.geek.life;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.math.BigDecimal;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
//public class LifeApplicationTests {
//
//    @Autowired
//    UserDemoDAO userDemoDAO;
//
//    @Test
//    public void contextLoads() {
//        UserDemo userDemo=new UserDemo();
//        userDemo.setType(1);
//        userDemo.setUserName(1);
//        userDemo.setVal(new BigDecimal(1));
//        userDemoDAO.insertSelective(userDemo);
//
//        log.info(userDemo.getId()+"");
//    }
//
//}
