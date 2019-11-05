package com.geek.life.demo.designModel.factory.simpleFactory;

import com.geek.life.demo.designModel.factory.AmdMainboard;
import com.geek.life.demo.designModel.factory.IntelMainboard;
import com.geek.life.demo.designModel.factory.Mainboard;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/10/22 09:26
 */
public class MainboardFactory {
    public static Mainboard createMainboard(int type){
        Mainboard mainboard = null;
        if(type == 1){
            mainboard = new IntelMainboard(755);
        }else if(type == 2){
            mainboard = new AmdMainboard(938);
        }
        return mainboard;
    }
}
