package com.jr.juhe.entity;


import lombok.Data;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @Description
 * @Author Djq
 * @Date 2019-12-10 09:47
 */
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class IllegalInquiryErrorFor369 {
    private String resultcode;
    private String reason;
    private String error_code;
    private String result;


    public String getReason() { return this.reason; }


    public void setReason(String reason) { this.reason = reason; }


    public String getResultcode() { return this.resultcode; }


    public void setResultcode(String resultcode) { this.resultcode = resultcode; }


    public String getError_code() { return this.error_code; }


    public void setError_code(String error_code) { this.error_code = error_code; }






    public String toString() {
        return "IllegalInquiryFor369 [resultcode=" + this.resultcode + ", reason=" + this.reason + ", error_code=" + this.error_code +
                ", result=" + this.result + "]";
    }
}
