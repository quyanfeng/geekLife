package com.jr.juhe.entity;

/**
 * @Description:支持城市回参
 * @Author: 屈艳锋
 * @Date: 2019/12/11 14:09
 */
public class LicensePlateCityFor369 {
    /**
     *城市名称
     **/
    private String city;

    /**
     *车牌前缀
     **/
    private String prefix;

    /**
     *车架号位数
     * 说明：0代表全位，-1不需要传，其他值表示后几位
     **/
    private String vin;

    /**
     *发动机位数
     * 说明：0代表全位，-1不需要传，其他值表示后几位
     **/
    private String engine;

    /**
     *支持车型
     * 说明：车型编号（多个逗号分隔），0非新能源车型，-1全部车型
     **/
    private String model;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
