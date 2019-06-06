package com.geek.life.support.tkmapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description: 统一mpper接口
 * @Author: 屈艳锋
 * @Date: 2019/6/4 10:10
 */
public interface TkMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
