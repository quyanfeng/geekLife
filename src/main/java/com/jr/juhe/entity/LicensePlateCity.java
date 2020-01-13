package com.jr.juhe.entity;










public class LicensePlateCity
{
    private String province;
    private String city_code;
    private String city_name;
    private String abbr;
    private String engine;
    private String engineno;
    private String classa;
    private String classno;

    public String getProvince() { return this.province; }


    public void setProvince(String province) { this.province = province; }


    public String getCity_code() { return this.city_code; }


    public void setCity_code(String city_code) { this.city_code = city_code; }


    public String getCity_name() { return this.city_name; }


    public void setCity_name(String city_name) { this.city_name = city_name; }


    public String getAbbr() { return this.abbr; }


    public void setAbbr(String abbr) { this.abbr = abbr; }


    public String getEngine() { return this.engine; }


    public void setEngine(String engine) { this.engine = engine; }


    public String getEngineno() { return this.engineno; }


    public void setEngineno(String engineno) { this.engineno = engineno; }


    public String getClassa() { return this.classa; }


    public void setClassa(String classa) { this.classa = classa; }


    public String getClassno() { return this.classno; }


    public void setClassno(String classno) { this.classno = classno; }


    public String toString() {
        return "LicensePlateCity [province=" + this.province + ", city_code=" + this.city_code + ", city_name=" + this.city_name +
                ", abbr=" + this.abbr + ", engine=" + this.engine + ", engineno=" + this.engineno + ", classa=" + this.classa +
                ", classno=" + this.classno + "]";
    }
}
