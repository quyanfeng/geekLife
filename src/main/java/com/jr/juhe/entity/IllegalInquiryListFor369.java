package com.jr.juhe.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * @Description
 * @Author Djq
 * @Date 2019-12-10 09:48
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IllegalInquiryListFor369 {
    private String cph;
    private String wzts;
    private String wzfkhj;
    private String wzjfhj;
    private IllegalInquiryListtwoFor369[] data;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getWzts() {
        return wzts;
    }

    public void setWzts(String wzts) {
        this.wzts = wzts;
    }

    public String getWzfkhj() {
        return wzfkhj;
    }

    public void setWzfkhj(String wzfkhj) {
        this.wzfkhj = wzfkhj;
    }

    public String getWzjfhj() {
        return wzjfhj;
    }

    public void setWzjfhj(String wzjfhj) {
        this.wzjfhj = wzjfhj;
    }

    public IllegalInquiryListtwoFor369[] getData() {
        return data;
    }

    public void setData(IllegalInquiryListtwoFor369[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IllegalInquiryListFor369{" +
                "cph='" + cph + '\'' +
                ", wzts='" + wzts + '\'' +
                ", wzfkhj='" + wzfkhj + '\'' +
                ", wzjfhj='" + wzjfhj + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
