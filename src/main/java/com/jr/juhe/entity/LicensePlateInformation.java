package com.jr.juhe.entity;

public class LicensePlateInformation {
    private String reason;
    private String error_code;
    private LicensePlateCity result;

    public String getReason() { return this.reason; }


    public void setReason(String reason) { this.reason = reason; }


    public String getError_code() { return this.error_code; }


    public void setError_code(String error_code) { this.error_code = error_code; }


    public LicensePlateCity getResult() { return this.result; }


    public void setResult(LicensePlateCity result) { this.result = result; }



    public String toString() { return "LicensePlateInformation [reason=" + this.reason + ", error_code=" + this.error_code + ", result=" + this.result + "]"; }
}
