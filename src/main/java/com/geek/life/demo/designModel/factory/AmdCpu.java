package com.geek.life.demo.designModel.factory;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/10/22 09:24
 */
public class AmdCpu implements Cpu {
    /**
     * CPU的针脚数
     */
    private int pins = 0;
    public  AmdCpu(int pins){
        this.pins = pins;
    }
    @Override
    public void calculate() {
        // TODO Auto-generated method stub
        System.out.println("AMD CPU的针脚数：" + pins);
    }
}
