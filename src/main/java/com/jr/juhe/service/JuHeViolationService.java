//package com.jr.juhe.service;
//
//import com.jr.juhe.entity.IllegalInquiry;
//import com.jr.juhe.entity.IllegalInquiryList;
//import com.jr.juhe.entity.LicensePlateCity;
//import com.jr.juhe.entity.LicensePlateInformation;
//import com.jr.juhe.util.JhHttpUtil;
//import com.jr.juhe.util.ObjectJsonUtil;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JuHeViolationService
//{
//    private String reqMsg;
//    private String respMsg;
//    private LicensePlateInformation licensePlateInformation;
//    private LicensePlateCity licensePlateCity;
//    private IllegalInquiry illegalInquiry;
//    private IllegalInquiryList illegalInquiryList;
//
//    public void driverViolation(String url, String dtype, String callback, String key, String city, String hpzl, String engineno, String classno, String hphm)
//            throws Exception
//    {
//        Map<String, Object> map = new HashMap();
//        map.put("dtype", dtype);
//        map.put("callback", callback);
//        map.put("key", key);
//        map.put("city", city);
//        map.put("hphm", hphm);
//        map.put("hpzl", hpzl);
//        map.put("engineno", engineno);
//        map.put("classno", classno);
//        this.reqMsg = JhHttpUtil.net(url, map, "POST");
//        if ((this.reqMsg == null) || (this.reqMsg.isEmpty())) {
//            this.illegalInquiry = null;
//        } else {
//            this.illegalInquiry = ((IllegalInquiry)ObjectJsonUtil.jsonToObj(this.reqMsg, IllegalInquiry.class));
//        }
//        if ((this.illegalInquiry != null) &&
//                (this.illegalInquiry.getResult() != null)) {
//            this.illegalInquiryList =
//                    ((IllegalInquiryList)ObjectJsonUtil.jsonToObj(ObjectJsonUtil.objToJson(this.illegalInquiry.getResult()), IllegalInquiryList.class));
//        }
//    }
//
//    public void SupportTheCity(String url, String key, String hphm, int isNer)
//            throws Exception
//    {
//        Map<String, Object> map = new HashMap();
//        map.put("key", key);
//        map.put("hphm", hphm);
//        map.put("isNer", Integer.valueOf(isNer));
//
//        this.reqMsg = JhHttpUtil.sendNet(url, map, "GET");
//        this.licensePlateInformation = ((LicensePlateInformation)ObjectJsonUtil.jsonToObj(this.reqMsg, LicensePlateInformation.class));
//    }
//
//    public String getReqMsg()
//    {
//        return this.reqMsg;
//    }
//
//    public void setReqMsg(String reqMsg)
//    {
//        this.reqMsg = reqMsg;
//    }
//
//    public String getRespMsg()
//    {
//        return this.respMsg;
//    }
//
//    public void setRespMsg(String respMsg)
//    {
//        this.respMsg = respMsg;
//    }
//
//    public LicensePlateInformation getLicensePlateInformation()
//    {
//        return this.licensePlateInformation;
//    }
//
//    public void setLicensePlateInformation(LicensePlateInformation licensePlateInformation)
//    {
//        this.licensePlateInformation = licensePlateInformation;
//    }
//
//    public LicensePlateCity getLicensePlateCity()
//    {
//        return this.licensePlateCity;
//    }
//
//    public void setLicensePlateCity(LicensePlateCity licensePlateCity)
//    {
//        this.licensePlateCity = licensePlateCity;
//    }
//
//    public IllegalInquiry getIllegalInquiry()
//    {
//        return this.illegalInquiry;
//    }
//
//    public void setIllegalInquiry(IllegalInquiry illegalInquiry)
//    {
//        this.illegalInquiry = illegalInquiry;
//    }
//
//    public IllegalInquiryList getIllegalInquiryList()
//    {
//        return this.illegalInquiryList;
//    }
//
//    public void setIllegalInquiryList(IllegalInquiryList illegalInquiryList)
//    {
//        this.illegalInquiryList = illegalInquiryList;
//    }
//}
