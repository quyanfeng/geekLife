package com.geek.life.demo.designModel.factory.abstractFactory;

import com.geek.life.demo.designModel.factory.Cpu;
import com.geek.life.demo.designModel.factory.Mainboard;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/10/22 10:24
 */
public interface AbstractFactory {
    /**
     * 创建CPU对象
     *
     * @return CPU对象
     */
    Cpu createCpu();

    /**
     * 创建主板对象
     *
     * @return 主板对象
     */
    Mainboard createMainboard();
}
