package com.geek.life.demo.designModel.factory.simpleFactory;

import com.geek.life.demo.designModel.factory.AmdCpu;
import com.geek.life.demo.designModel.factory.Cpu;
import com.geek.life.demo.designModel.factory.IntelCpu;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/10/22 09:25
 */
public class CpuFactory {
    public static Cpu createCpu(int type){
        Cpu cpu = null;
        if(type == 1){
            cpu = new IntelCpu(755);
        }else if(type == 2){
            cpu = new AmdCpu(938);
        }
        return cpu;
    }
}
