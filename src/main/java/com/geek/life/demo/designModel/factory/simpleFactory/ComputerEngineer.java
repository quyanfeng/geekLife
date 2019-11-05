package com.geek.life.demo.designModel.factory.simpleFactory;

import com.geek.life.demo.designModel.factory.Cpu;
import com.geek.life.demo.designModel.factory.Mainboard;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/10/22 09:26
 */
public class ComputerEngineer {
    /**
     * 定义组装机需要的CPU
     */
    private Cpu cpu = null;
    /**
     * 定义组装机需要的主板
     */
    private Mainboard mainboard = null;
    public void makeComputer(int cpuType , int mainboard){
        /**
         * 组装机器的基本步骤
         */
        //1:首先准备好装机所需要的配件
        prepareHardwares(cpuType, mainboard);
    }
    private void prepareHardwares(int cpuType , int mainboard){
        //这里要去准备CPU和主板的具体实现，为了示例简单，这里只准备这两个
        //可是，装机工程师并不知道如何去创建，怎么办呢？

        //直接找相应的工厂获取
        this.cpu = CpuFactory.createCpu(cpuType);
        this.mainboard = MainboardFactory.createMainboard(mainboard);

        //测试配件是否好用
        this.cpu.calculate();
        this.mainboard.installCPU();
    }
}
