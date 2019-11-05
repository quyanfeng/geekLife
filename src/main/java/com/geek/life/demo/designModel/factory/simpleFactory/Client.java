package com.geek.life.demo.designModel.factory.simpleFactory;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/10/22 09:26
 */
public class Client {
    public static void main(String[]args){
        ComputerEngineer cf = new ComputerEngineer();
        cf.makeComputer(1,1);
    }
}
