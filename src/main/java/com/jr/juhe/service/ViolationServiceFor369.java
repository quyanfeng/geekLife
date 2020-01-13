package com.jr.juhe.service;

import com.jr.juhe.entity.*;
import com.jr.juhe.util.JhHttpUtil;
import com.jr.juhe.util.ObjectJsonUtil;

import java.util.HashMap;
import java.util.Map;

public class ViolationServiceFor369
{
    private String reqMsg;
    private String respMsg;
    private LicensePlateInformationFor369 licensePlateInformation;
    private IllegalInquiryFor369 illegalInquiry;
    private IllegalInquiryListFor369 illegalInquiryList;
    private LicensePlateCityFor369 licensePlateCity;

    /**
     * 查询违章
     * @Author qyf 
     * @Date 2019/12/11 14:59
     * @return java.lang.String
     **/
    public void driverViolation(String url, String module, String key, String cx, String cph, String cjh, String fdj)
    {
        try {
            Map<String, Object> map = new HashMap();
            map.put("module", module);
            map.put("key", key);
            map.put("cx", cx);
            map.put("cph", cph);
            map.put("cjh", cjh);
            map.put("fdj", fdj);
            this.reqMsg = JhHttpUtil.net(url, map, "POST");
            System.out.println("reqMsg:"+reqMsg);
            Map<String, Object> resultMap = (Map)ObjectJsonUtil.jsonToObj(this.reqMsg, Map.class);
            if (!"200".equals(String.valueOf(resultMap.get("resultcode")))) {
                resultMap.put("result",null);
                this.reqMsg=ObjectJsonUtil.objToJson(resultMap);
            }
             this.illegalInquiry = ((IllegalInquiryFor369)ObjectJsonUtil.jsonToObj(this.reqMsg, IllegalInquiryFor369.class));
            if ((this.illegalInquiry != null) &&
                    (this.illegalInquiry.getResult() != null)) {
                this.illegalInquiryList = ((IllegalInquiryListFor369)ObjectJsonUtil.jsonToObj(ObjectJsonUtil.objToJson(this.illegalInquiry.getResult()), IllegalInquiryListFor369.class));
            }
        }catch (Exception e){
            e.printStackTrace();
            this.illegalInquiry=null;
            this.illegalInquiryList=null;
        }
    }

    /**
     * 查询支持城市
     * @Author qyf 
     * @Date 2019/12/11 14:59
     * @param url 地址
     * @param module query
     * @param key key
     * @param licensePrefix 车牌首位字母
     * @return void
     **/
    public void SupportTheCity(String url,String module,String key,String licensePrefix)
            throws Exception
    {
        Map<String, Object> map = new HashMap();
        map.put("key", key);
        map.put("module", module);

        //请求接口
        this.reqMsg = JhHttpUtil.sendNet(url, map, "POST");
        //解析返回结果
        Map<String, Object> resultMap = (Map)ObjectJsonUtil.jsonToObj(this.reqMsg, Map.class);
        if ("200".equals(String.valueOf(resultMap.get("resultcode")))) {
            this.licensePlateInformation = ((LicensePlateInformationFor369)ObjectJsonUtil.jsonToObj(this.reqMsg, LicensePlateInformationFor369.class));
        }

        //获取当前查询城市
        if(licensePlateInformation!=null&&licensePlateInformation.getResult()!=null){
           Map<String,LicensePlateCityFor369> result=licensePlateInformation.getResult();
           for(LicensePlateCityFor369 val:result.values()){
                if(licensePrefix.equals(val.getPrefix())){
                    this.licensePlateCity=val;
                    break;
                }
           }
        }
    }

    public String getReqMsg()
    {
        return this.reqMsg;
    }

    public void setReqMsg(String reqMsg)
    {
        this.reqMsg = reqMsg;
    }

    public String getRespMsg()
    {
        return this.respMsg;
    }

    public void setRespMsg(String respMsg)
    {
        this.respMsg = respMsg;
    }

    public IllegalInquiryFor369 getIllegalInquiry()
    {
        return this.illegalInquiry;
    }

    public void setIllegalInquiry(IllegalInquiryFor369 illegalInquiry)
    {
        this.illegalInquiry = illegalInquiry;
    }

    public IllegalInquiryListFor369 getIllegalInquiryList()
    {
        return this.illegalInquiryList;
    }

    public void setIllegalInquiryList(IllegalInquiryListFor369 illegalInquiryList)
    {
        this.illegalInquiryList = illegalInquiryList;
    }

    public LicensePlateInformationFor369 getLicensePlateInformation() {
        return licensePlateInformation;
    }

    public void setLicensePlateInformation(LicensePlateInformationFor369 licensePlateInformation) {
        this.licensePlateInformation = licensePlateInformation;
    }

    public LicensePlateCityFor369 getLicensePlateCity() {
        return licensePlateCity;
    }

    public void setLicensePlateCity(LicensePlateCityFor369 licensePlateCity) {
        this.licensePlateCity = licensePlateCity;
    }

}
