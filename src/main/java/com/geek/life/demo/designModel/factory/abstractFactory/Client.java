package com.geek.life.demo.designModel.factory.abstractFactory;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/10/22 10:26
 */
public class Client {
    public static void main(String[]args){
        //创建装机工程师对象
        ComputerEngineer cf = new ComputerEngineer();
        //客户选择并创建需要使用的产品对象
        AbstractFactory af = new IntelFactory();
        //告诉装机工程师自己选择的产品，让装机工程师组装电脑
        cf.makeComputer(af);
    }
}