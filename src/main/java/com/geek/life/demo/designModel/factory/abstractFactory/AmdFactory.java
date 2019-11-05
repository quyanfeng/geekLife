package com.geek.life.demo.designModel.factory.abstractFactory;

import com.geek.life.demo.designModel.factory.Cpu;
import com.geek.life.demo.designModel.factory.IntelCpu;
import com.geek.life.demo.designModel.factory.IntelMainboard;
import com.geek.life.demo.designModel.factory.Mainboard;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/10/22 10:25
 */
public class AmdFactory implements AbstractFactory {

    @Override
    public Cpu createCpu() {
        // TODO Auto-generated method stub
        return new IntelCpu(938);
    }

    @Override
    public Mainboard createMainboard() {
        // TODO Auto-generated method stub
        return new IntelMainboard(938);
    }

}
