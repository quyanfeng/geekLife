package com.jr.juhe.entity;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @Description
 * @Author Djq
 * @Date 2019-12-10 09:48
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IllegalInquiryListtwoFor369 {
    private String date;
    private String wzsj;
    private String wzdd;
    private String wzxw;
    private String wzjf;
    private String wzfk;
    private String csmc;
    private String csdm;
    private String wfdm;
    private String cjjg;

    private String jkbh;
    private String jdsbh;
    private String clbj;
    private String clbh;
    private String zxcl;

    public String getJdsbh() {
        return jdsbh;
    }

    public void setJdsbh(String jdsbh) {
        this.jdsbh = jdsbh;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWzsj() {
        return wzsj;
    }

    public void setWzsj(String wzsj) {
        this.wzsj = wzsj;
    }

    public String getWzdd() {
        return wzdd;
    }

    public void setWzdd(String wzdd) {
        this.wzdd = wzdd;
    }

    public String getWzxw() {
        return wzxw;
    }

    public void setWzxw(String wzxw) {
        this.wzxw = wzxw;
    }

    public String getWzjf() {
        return wzjf;
    }

    public void setWzjf(String wzjf) {
        this.wzjf = wzjf;
    }

    public String getWzfk() {
        return wzfk;
    }

    public void setWzfk(String wzfk) {
        this.wzfk = wzfk;
    }

    public String getCsmc() {
        return csmc;
    }

    public void setCsmc(String csmc) {
        this.csmc = csmc;
    }

    public String getCsdm() {
        return csdm;
    }

    public void setCsdm(String csdm) {
        this.csdm = csdm;
    }

    public String getWfdm() {
        return wfdm;
    }

    public void setWfdm(String wfdm) {
        this.wfdm = wfdm;
    }

    public String getCjjg() {
        return cjjg;
    }

    public void setCjjg(String cjjg) {
        this.cjjg = cjjg;
    }

    public String getJkbh() {
        return jkbh;
    }

    public void setJkbh(String jkbh) {
        this.jkbh = jkbh;
    }

    public String getClbj() {
        if(clbj==null||clbj.equals("")){
            clbj="1";//未处理未缴费
        }
        return  clbj;
    }

    public void setClbj(String clbj) {
        this.clbj = clbj;
    }

    public String getClbh() {
        return clbh;
    }

    public void setClbh(String clbh) {
        this.clbh = clbh;
    }

    public String getZxcl() {
        if(zxcl==null||zxcl.equals("")){
            zxcl="2";//不支持处理
        }
        return zxcl;
    }

    public void setZxcl(String zxcl) {
        this.zxcl = zxcl;
    }

    @Override
    public String toString() {
        return "IllegalInquiryListtwoFor369{" +
                "date='" + date + '\'' +
                ", wzsj='" + wzsj + '\'' +
                ", wzdd='" + wzdd + '\'' +
                ", wzxw='" + wzxw + '\'' +
                ", wzjf='" + wzjf + '\'' +
                ", wzfk='" + wzfk + '\'' +
                ", csmc='" + csmc + '\'' +
                ", csdm='" + csdm + '\'' +
                ", wfdm='" + wfdm + '\'' +
                ", cjjg='" + cjjg + '\'' +
                ", jkbh='" + jkbh + '\'' +
                ", clbj='" + clbj + '\'' +
                ", clbh='" + clbh + '\'' +
                ", zxcl='" + zxcl + '\'' +
                '}';
    }
}
