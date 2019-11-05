package com.geek.life.demo.designModel.abstractMethod;

/**
 * @Description: 模板方法演示
 * @Author: 屈艳锋
 * @Date: 2019/10/21 10:37
 */
public abstract class AbstractPay {

    /**
     * 基本方法又可以分为三种：抽象方法(Abstract Method)、具体方法(Concrete Method)和钩子方法(Hook Method)。
     *
     * ● 抽象方法：一个抽象方法由抽象类声明，由具体子类实现。在Java语言里抽象方法以abstract关键字标示。
     *
     * ● 具体方法：一个具体方法由抽象类声明并实现，而子类并不实现或置换。
     *
     * ● 钩子方法：一个钩子方法由抽象类声明并实现，而子类会加以扩展。通常抽象类给出的实现是一个空实现，作为方法的默认实现。
     *
     **/


    /************************************************抽象方法**************************************************/
    /**
     * 支付
     *
     * @param
     * @return void
     * @Author qyf
     * @Date 2019/10/21 10:41
     **/
    public final void pay() {

        //1.预支付
        prePay();

        //2.支付
        paying();

        //3.支付回调
        postPay();
    }


    /************************************************具体方法**************************************************/
    /**
     * 预支付
     *
     * @param
     * @return void
     * @Author qyf
     * @Date 2019/10/21 10:40
     **/
    public abstract void prePay();

    /**
     * 支付
     *
     * @param
     * @return void
     * @Author qyf
     * @Date 2019/10/21 10:40
     **/
    public abstract void paying();

    /**
     * 支付回调
     *
     * @param
     * @return void
     * @Author qyf
     * @Date 2019/10/21 10:40
     **/
    public abstract void postPay();


    /************************************************钩子方法**************************************************/
    protected void hook(){

    }

}
