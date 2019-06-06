package com.geek.life.service.serviceImp;

import com.geek.life.dao.UserDemoDAO;
import com.geek.life.model.pojo.UserDemo;
import com.geek.life.service.demoService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/6/4 11:05
 */
public class demoServiceImpl implements demoService {

    @Autowired
    UserDemoDAO userDemoDAO;

    @Override
    public void demo() {
        Example example=new Example(UserDemo.class);

        //排序那个字段
        example.setOrderByClause("id");
        Example.Criteria criteria = example.createCriteria();
        criteria.andLessThanOrEqualTo("val",1);

//        userDemoDAO.selectByExample(example);

//        userDemoDAO.insertList()

    }
}
