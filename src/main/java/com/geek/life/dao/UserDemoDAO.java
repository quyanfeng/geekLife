package com.geek.life.dao;

import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.Mapper;
import org.springframework.stereotype.Repository;
import com.geek.life.model.pojo.UserDemo;

@Repository
public interface UserDemoDAO extends MySqlMapper<UserDemo>, Mapper<UserDemo> {

	/**
     * 测试
     * @Author qyf 
     * @Date 2019/6/6 10:54
     * @param 
     * @return void
     **/
	void test();


}