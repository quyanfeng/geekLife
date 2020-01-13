package com.jr.juhe.entity;

import java.util.Map;

/**
 * @Description:支持城市回参
 * @Author: 屈艳锋
 * @Date: 2019/12/11 14:06
 */
public class LicensePlateInformationFor369 {
    private String resultcode;
    private String reason;
    private String error_code;
    private Map<String,LicensePlateCityFor369> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public Map<String, LicensePlateCityFor369> getResult() {
        return result;
    }

    public void setResult(Map<String, LicensePlateCityFor369> result) {
        this.result = result;
    }
}
